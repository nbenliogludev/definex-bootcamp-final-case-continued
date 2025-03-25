package com.nbenliogludev.taskmanagementservice.repository;

import com.nbenliogludev.taskmanagementservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
