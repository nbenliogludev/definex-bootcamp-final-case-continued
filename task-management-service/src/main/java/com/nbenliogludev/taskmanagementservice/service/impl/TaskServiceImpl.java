package com.nbenliogludev.taskmanagementservice.service.impl;

import com.nbenliogludev.taskmanagementservice.client.fileStorage.service.FileStorageService;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.TaskCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.entity.Project;
import com.nbenliogludev.taskmanagementservice.entity.Task;
import com.nbenliogludev.taskmanagementservice.mapper.TaskMapper;
import com.nbenliogludev.taskmanagementservice.repository.ProjectRepository;
import com.nbenliogludev.taskmanagementservice.repository.TaskRepository;
import com.nbenliogludev.taskmanagementservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;
    private final FileStorageService fileStorageService;

    @Override
    public TaskCreateResponseDTO createTask(TaskCreateRequestDTO request) {
        Task task = taskMapper.mapToTask(request);

        Project project = projectRepository.findById(request.projectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        task.setProject(project);
        validateAndSetAttachments(request.attachments(), task);

        Task saved = taskRepository.save(task);
        return taskMapper.mapToTaskResponse(saved);
    }

    @Override
    public TaskCreateResponseDTO updateTask(UUID id, TaskUpdateRequestDTO request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskMapper.updateTaskFromDto(request, task);
        validateAndSetAttachments(request.attachments(), task);

        Task updated = taskRepository.save(task);
        return taskMapper.mapToTaskResponse(updated);
    }

    private void validateAndSetAttachments(List<UUID> attachments, Task task) {
        if (attachments != null && !attachments.isEmpty()) {
            List<UUID> validIds = fileStorageService.getValidFileIds(attachments);
            task.setAttachments(validIds);
        }
    }

    @Override
    public List<TaskCreateResponseDTO> getAllByProjectId(UUID projectId) {
        List<Task> tasks = taskRepository.findAllByProjectId(projectId);
        return tasks.stream()
                .map(taskMapper::mapToTaskResponse)
                .toList();
    }

    @Override
    public List<TaskCreateResponseDTO> getAllByAssigneeId(UUID assigneeId) {
        List<Task> tasks = taskRepository.findAllByAssigneeId(assigneeId);
        return tasks.stream()
                .map(taskMapper::mapToTaskResponse)
                .toList();
    }

    @Override
    public void deleteTask(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}
