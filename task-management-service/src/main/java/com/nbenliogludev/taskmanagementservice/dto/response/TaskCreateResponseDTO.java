package com.nbenliogludev.taskmanagementservice.dto.response;

import com.nbenliogludev.taskmanagementservice.enums.TaskPriority;
import com.nbenliogludev.taskmanagementservice.enums.TaskState;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record TaskCreateResponseDTO (

    UUID taskId,
    String title,
    String description,
    TaskState state,
    TaskPriority priority,
    String assigneeId,
    UUID projectId,
    String reason,
    List<UUID> attachments
) {
}
