package com.nbenliogludev.filestorageservice.entity;

import com.nbenliogludev.filestorageservice.entity.common.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@Entity
@Getter
@Setter
@Table(name = "file_metadata")
public class FileMetadata extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID taskId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

}
