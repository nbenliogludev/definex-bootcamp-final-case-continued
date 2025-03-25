package com.nbenliogludev.filestorageservice.dto.response;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record FileUploadResponseDTO(
        UUID fileId,
        String fileName,
        String fileUrl
) {}