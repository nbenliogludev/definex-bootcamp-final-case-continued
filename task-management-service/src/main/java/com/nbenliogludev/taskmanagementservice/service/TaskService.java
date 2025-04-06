package com.nbenliogludev.taskmanagementservice.service;

import com.nbenliogludev.taskmanagementservice.dto.request.TaskCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateStateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.TaskCreateResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface TaskService {

    TaskCreateResponseDTO createTask(TaskCreateRequestDTO request);

    TaskCreateResponseDTO updateTask(UUID id, TaskUpdateRequestDTO request);

    TaskCreateResponseDTO updateTaskState(UUID id, TaskUpdateStateRequestDTO request);

    TaskCreateResponseDTO updateTaskAssignee(UUID taskId, UUID assigneeId);

    List<TaskCreateResponseDTO> getAllByProjectId(UUID projectId);

    List<TaskCreateResponseDTO> getAllByAssigneeId(UUID assigneeId);

    void deleteTask(UUID taskId);
}
