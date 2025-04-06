package com.nbenliogludev.taskmanagementservice.dto.request;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record TaskUpdateDetailsRequestDTO(
        String title,
        String description
) {}
