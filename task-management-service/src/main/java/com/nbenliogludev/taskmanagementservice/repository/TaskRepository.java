package com.nbenliogludev.taskmanagementservice.repository;

import com.nbenliogludev.taskmanagementservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByProjectId(UUID projectId);

    List<Task> findAllByAssigneeId(UUID assigneeId);
}
