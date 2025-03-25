package com.nbenliogludev.filestorageservice.repository;

import com.nbenliogludev.filestorageservice.entity.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface FileStorageRepository extends JpaRepository<FileMetadata, UUID> {
    Optional<FileMetadata> findByFileNameAndDeletedFalse(String fileName);
}
