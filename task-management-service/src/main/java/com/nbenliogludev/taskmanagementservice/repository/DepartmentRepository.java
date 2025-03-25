package com.nbenliogludev.taskmanagementservice.repository;

import com.nbenliogludev.taskmanagementservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}