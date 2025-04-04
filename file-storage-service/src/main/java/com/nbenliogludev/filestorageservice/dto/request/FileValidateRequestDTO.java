package com.nbenliogludev.filestorageservice.dto.request;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record FileValidateRequestDTO(
        List<UUID> fileIds
) {}