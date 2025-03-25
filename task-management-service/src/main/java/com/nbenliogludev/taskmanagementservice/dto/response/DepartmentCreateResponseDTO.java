package com.nbenliogludev.taskmanagementservice.dto.response;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record DepartmentCreateResponseDTO(
        UUID id,
        String name
) {
}
