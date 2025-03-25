package com.nbenliogludev.taskmanagementservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record CommentCreateRequestDTO(
        @NotNull UUID taskId,
        @NotNull UUID userId,
        @NotBlank String content
) {}
