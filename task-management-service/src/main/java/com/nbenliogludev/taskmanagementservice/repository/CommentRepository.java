package com.nbenliogludev.taskmanagementservice.repository;

import com.nbenliogludev.taskmanagementservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findAllByTaskId(UUID taskId);
}
