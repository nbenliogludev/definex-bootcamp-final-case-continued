package com.nbenliogludev.taskmanagementservice.dto.request;

import com.nbenliogludev.taskmanagementservice.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record ProjectUpdateRequestDTO(
        @NotNull UUID id,
        @NotBlank String title,
        String description,
        @NotNull ProjectStatus status,
        @NotNull UUID departmentId
) {}
