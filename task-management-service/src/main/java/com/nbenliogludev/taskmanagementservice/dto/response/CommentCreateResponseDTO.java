package com.nbenliogludev.taskmanagementservice.dto.response;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record CommentCreateResponseDTO(
        UUID id,
        UUID taskId,
        UUID userId,
        String content
) {}