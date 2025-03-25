package com.nbenliogludev.filestorageservice.service;

import com.nbenliogludev.filestorageservice.entity.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageService {

    FileMetadata storeFile(MultipartFile file, UUID taskId);

    Resource loadFileAsResourceById(UUID fileId);

    void deleteFileById(UUID fileId);

    boolean existsById(UUID id);
}
