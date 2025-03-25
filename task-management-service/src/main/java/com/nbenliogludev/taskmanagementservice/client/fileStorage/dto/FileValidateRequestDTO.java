package com.nbenliogludev.taskmanagementservice.client.fileStorage.dto;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record FileValidateRequestDTO(
        List<UUID> fileIds
) {}