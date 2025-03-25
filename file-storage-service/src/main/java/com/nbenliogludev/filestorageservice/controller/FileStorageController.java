package com.nbenliogludev.filestorageservice.controller;

import com.nbenliogludev.filestorageservice.dto.request.FileUploadRequestDTO;
import com.nbenliogludev.filestorageservice.dto.response.FileUploadResponseDTO;
import com.nbenliogludev.filestorageservice.dto.response.RestResponse;
import com.nbenliogludev.filestorageservice.entity.FileMetadata;
import com.nbenliogludev.filestorageservice.service.FileStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@RestController
@RequestMapping("/api/files")
@Validated
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @PostMapping("/v1/upload")
    public ResponseEntity<RestResponse<FileUploadResponseDTO>> uploadFile(
            @ModelAttribute @Valid FileUploadRequestDTO request) throws IOException {

        FileMetadata storedFileMetadata = fileStorageService.storeFile(request.file(), request.taskId());
        String fileUrl = "/api/files/download/" + storedFileMetadata.getFileName();

        FileUploadResponseDTO responseDTO = new FileUploadResponseDTO(storedFileMetadata.getId(), storedFileMetadata.getFileName(), fileUrl);

        return ResponseEntity.ok(RestResponse.of(responseDTO));
    }


    @GetMapping("/v1/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable UUID fileId) throws IOException {
        Resource resource = fileStorageService.loadFileAsResourceById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/v1/delete/{fileId}")
    public ResponseEntity<RestResponse<String>> deleteFile(@PathVariable UUID fileId) {
        fileStorageService.deleteFileById(fileId);
        return ResponseEntity.ok(RestResponse.of("File marked as deleted. ID: " + fileId));
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<Void> validateFile(@PathVariable UUID id) {
        if (fileStorageService.existsById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
