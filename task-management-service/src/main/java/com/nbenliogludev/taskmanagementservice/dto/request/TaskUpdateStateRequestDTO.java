package com.nbenliogludev.taskmanagementservice.dto.request;

import com.nbenliogludev.taskmanagementservice.enums.TaskState;

/**
 * @author nbenliogludev
 */
public record TaskUpdateStateRequestDTO (
        TaskState state,
        String reason
) {
}