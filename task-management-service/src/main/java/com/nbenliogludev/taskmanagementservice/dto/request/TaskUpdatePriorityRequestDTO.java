package com.nbenliogludev.taskmanagementservice.dto.request;

import com.nbenliogludev.taskmanagementservice.enums.TaskPriority;

/**
 * @author nbenliogludev
 */
public record TaskUpdatePriorityRequestDTO (
        TaskPriority priority
) {
}
