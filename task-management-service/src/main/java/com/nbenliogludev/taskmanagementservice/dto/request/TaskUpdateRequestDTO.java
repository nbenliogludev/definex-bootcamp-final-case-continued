package com.nbenliogludev.taskmanagementservice.dto.request;

import com.nbenliogludev.taskmanagementservice.enums.TaskPriority;
import com.nbenliogludev.taskmanagementservice.enums.TaskState;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record TaskUpdateRequestDTO(
        UUID id,
        String title,
        String description,
        TaskState state,
        TaskPriority priority,
        String reason,
        UUID projectId,
        UUID assigneeId,
        List<UUID> attachments
) {}
