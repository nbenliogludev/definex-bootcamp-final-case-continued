package com.nbenliogludev.taskmanagementservice.dto.response;

import com.nbenliogludev.taskmanagementservice.enums.ProjectStatus;

import java.util.Set;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record ProjectCreateResponseDTO(
        UUID id,
        String title,
        String description,
        ProjectStatus status,
        UUID departmentId,
        Set<UUID> teamMemberIds
) {
}
