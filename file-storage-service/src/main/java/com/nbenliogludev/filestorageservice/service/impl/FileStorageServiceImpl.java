package com.nbenliogludev.filestorageservice.service.impl;

import com.nbenliogludev.filestorageservice.entity.FileMetadata;
import com.nbenliogludev.filestorageservice.exception.FileNotFoundException;
import com.nbenliogludev.filestorageservice.exception.FileStorageException;
import com.nbenliogludev.filestorageservice.repository.FileStorageRepository;
import com.nbenliogludev.filestorageservice.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageRepository fileStorageRepository;
    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
    private final Path deletedFilesLocation = Paths.get("uploads/deleted").toAbsolutePath().normalize();
    private static final List<String> ALLOWED_FILE_EXTENSIONS = List.of("pdf", "png", "jpg", "jpeg");

    public FileStorageServiceImpl(FileStorageRepository fileStorageRepository) throws IOException {
        this.fileStorageRepository = fileStorageRepository;
        Files.createDirectories(fileStorageLocation);
        Files.createDirectories(deletedFilesLocation);
    }

    @Override
    public FileMetadata storeFile(MultipartFile file, UUID taskId) {
        try {
            if (file.isEmpty()) {
                throw new FileStorageException("Uploaded file is empty.");
            }

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || !isAllowedFileType(originalFileName)) {
                throw new FileStorageException("Unsupported file format. Allowed: " + ALLOWED_FILE_EXTENSIONS);
            }

            String fileName = UUID.randomUUID() + "_" + originalFileName;
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            FileMetadata fileMetadata = new FileMetadata();
            fileMetadata.setTaskId(taskId);
            fileMetadata.setFileName(fileName);
            fileMetadata.setFilePath(targetLocation.toString());
            fileMetadata.setUploadedAt(LocalDateTime.now());
            fileMetadata.setDeleted(false);

            fileStorageRepository.save(fileMetadata);

            return fileMetadata;
        } catch (IOException ex) {
            throw new FileStorageException("File upload failed for: " + file.getOriginalFilename(), ex);
        }
    }

    @Override
    public Resource loadFileAsResourceById(UUID fileId) {
        FileMetadata metadata = fileStorageRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + fileId));

        if (metadata.isDeleted()) {
            throw new FileNotFoundException("File with ID " + fileId + " is deleted.");
        }

        try {
            Path filePath = Paths.get(metadata.getFilePath()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Cannot read file with ID: " + fileId);
            }
        } catch (MalformedURLException ex) {
            throw new FileStorageException("Malformed file path for ID: " + fileId, ex);
        }
    }

    @Override
    public void deleteFileById(UUID fileId) {
        FileMetadata fileMetadata = fileStorageRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + fileId));

        if (fileMetadata.isDeleted()) {
            return;
        }

        try {
            Path filePath = Paths.get(fileMetadata.getFilePath());
            Path targetLocation = deletedFilesLocation.resolve(fileMetadata.getFileName());
            Files.move(filePath, targetLocation, StandardCopyOption.REPLACE_EXISTING);

            fileMetadata.setDeleted(true);
            fileMetadata.setFilePath(targetLocation.toString());
            fileStorageRepository.save(fileMetadata);
        } catch (IOException ex) {
            throw new FileStorageException("Failed to delete file with ID: " + fileId, ex);
        }
    }

    private boolean isAllowedFileType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        return ALLOWED_FILE_EXTENSIONS.contains(fileExtension);
    }

    @Override
    public boolean existsById(UUID id) {
        return fileStorageRepository.existsById(id);
    }
}
