package com.nbenliogludev.filestorageservice.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public record FileUploadRequestDTO(
        @NotNull(message = "File cannot be null")
        MultipartFile file,

        @NotNull(message = "Task ID cannot be null")
        UUID taskId
) {}
