package com.nbenliogludev.taskmanagementservice.service.impl;

import com.nbenliogludev.taskmanagementservice.client.fileStorage.service.FileStorageService;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateDetailsRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdatePriorityRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateStateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.TaskCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.entity.Project;
import com.nbenliogludev.taskmanagementservice.entity.Task;
import com.nbenliogludev.taskmanagementservice.enums.TaskState;
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
    public TaskCreateResponseDTO updateTaskDetails(UUID taskId, TaskUpdateDetailsRequestDTO request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.title());
        task.setDescription(request.description());

        Task updated = taskRepository.save(task);
        return taskMapper.mapToTaskResponse(updated);
    }

    @Override
    public TaskCreateResponseDTO updateTaskState(UUID id, TaskUpdateStateRequestDTO request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskState oldState = task.getState();
        TaskState newState = request.state();

        if (oldState == TaskState.COMPLETED) {
            throw new RuntimeException("Cannot change state from COMPLETED to anything else");
        }

        if ((newState == TaskState.BLOCKED || newState == TaskState.CANCELLED)
                && (request.reason() == null || request.reason().isBlank())) {
            throw new RuntimeException("Reason is required when assigning BLOCKED or CANCELLED");
        }

        if (newState == TaskState.BLOCKED || newState == TaskState.CANCELLED) {
            task.setReason(request.reason());
        }

        task.setState(newState);

        Task updated = taskRepository.save(task);

        return taskMapper.mapToTaskResponse(updated);
    }

    @Override
    public TaskCreateResponseDTO updateTaskAssignee(UUID taskId, UUID assigneeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setAssigneeId(assigneeId);

        Task updated = taskRepository.save(task);
        return taskMapper.mapToTaskResponse(updated);
    }

    @Override
    public TaskCreateResponseDTO updateTaskPriority(UUID taskId, TaskUpdatePriorityRequestDTO request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setPriority(request.priority());

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
