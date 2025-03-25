package com.nbenliogludev.taskmanagementservice.client.fileStorage;

import com.nbenliogludev.taskmanagementservice.client.fileStorage.dto.FileValidateRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@FeignClient(name = "file-storage-service")
public interface FileStorageClient {

    @GetMapping("/api/files/validate/{id}")
    ResponseEntity<Void> validateFile(@PathVariable UUID id);

    @PostMapping("/api/files/validate/batch")
    ResponseEntity<List<UUID>> validateFiles(@RequestBody FileValidateRequestDTO request);
}
