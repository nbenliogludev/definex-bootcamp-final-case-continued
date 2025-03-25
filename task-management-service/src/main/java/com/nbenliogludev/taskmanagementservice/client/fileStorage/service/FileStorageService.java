package com.nbenliogludev.taskmanagementservice.client.fileStorage.service;

import com.nbenliogludev.taskmanagementservice.client.fileStorage.FileStorageClient;
import com.nbenliogludev.taskmanagementservice.client.fileStorage.dto.FileValidateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileStorageClient fileStorageClient;

    public boolean isFileExists(UUID fileId) {
        ResponseEntity<Void> response = fileStorageClient.validateFile(fileId);
        return response.getStatusCode().is2xxSuccessful();
    }

    public List<UUID> getValidFileIds(List<UUID> fileIds) {
        FileValidateRequestDTO request = new FileValidateRequestDTO(fileIds);
        ResponseEntity<List<UUID>> response = fileStorageClient.validateFiles(request);
        return response.getBody();
    }
}
