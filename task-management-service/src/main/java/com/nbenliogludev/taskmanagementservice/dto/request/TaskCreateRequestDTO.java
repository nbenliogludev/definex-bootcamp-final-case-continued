package com.nbenliogludev.taskmanagementservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;


/**
 * @author nbenliogludev
 */
public record TaskCreateRequestDTO (
        @NotBlank(message = "Task title is required.")
        String title,

        @NotBlank(message = "Task description is required.")
        String description,

        @NotBlank(message = "Priority is required.")
        String priority,

        @NotNull(message = "Assignee ID cannot be null.")
        UUID assigneeId,

        @NotNull(message = "Project ID cannot be null.")
        UUID projectId,

        List<UUID> attachments
) {}
