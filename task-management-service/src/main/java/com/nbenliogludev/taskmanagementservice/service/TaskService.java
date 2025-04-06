package com.nbenliogludev.taskmanagementservice.service;

import com.nbenliogludev.taskmanagementservice.dto.request.TaskCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateDetailsRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdatePriorityRequestDTO;
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

    TaskCreateResponseDTO updateTaskDetails(UUID id, TaskUpdateDetailsRequestDTO request);

    TaskCreateResponseDTO updateTaskState(UUID id, TaskUpdateStateRequestDTO request);

    TaskCreateResponseDTO updateTaskAssignee(UUID taskId, UUID assigneeId);

    TaskCreateResponseDTO updateTaskPriority(UUID taskId, TaskUpdatePriorityRequestDTO request);

    List<TaskCreateResponseDTO> getAllByProjectId(UUID projectId);

    List<TaskCreateResponseDTO> getAllByAssigneeId(UUID assigneeId);

    void deleteTask(UUID taskId);
}
