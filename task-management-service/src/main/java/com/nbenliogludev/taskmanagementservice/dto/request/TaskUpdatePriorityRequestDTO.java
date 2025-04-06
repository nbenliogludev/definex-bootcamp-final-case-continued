package com.nbenliogludev.taskmanagementservice.dto.request;

import com.nbenliogludev.taskmanagementservice.enums.TaskPriority;
import com.nbenliogludev.taskmanagementservice.enums.TaskState;

/**
 * @author nbenliogludev
 */
public record TaskUpdatePriorityRequestDTO (
        TaskPriority priority
) {
}
