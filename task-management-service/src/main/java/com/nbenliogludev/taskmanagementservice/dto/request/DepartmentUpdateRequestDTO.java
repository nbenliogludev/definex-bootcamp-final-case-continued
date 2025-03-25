package com.nbenliogludev.taskmanagementservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record DepartmentUpdateRequestDTO(
        @NotNull(message = "ID must not be null")
        UUID id,

        @NotBlank(message = "Department name must not be blank")
        String name
) {}