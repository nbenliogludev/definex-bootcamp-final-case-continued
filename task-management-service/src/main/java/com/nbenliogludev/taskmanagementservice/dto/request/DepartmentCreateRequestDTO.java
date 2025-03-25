package com.nbenliogludev.taskmanagementservice.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * @author nbenliogludev
 */
public record DepartmentCreateRequestDTO(

        @NotBlank(message = "Department name must not be blank")
        String name

) {
}
