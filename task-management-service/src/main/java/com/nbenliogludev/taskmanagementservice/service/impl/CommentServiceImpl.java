package com.nbenliogludev.taskmanagementservice.service.impl;

import com.nbenliogludev.taskmanagementservice.dto.request.CommentCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.CommentCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.entity.Comment;
import com.nbenliogludev.taskmanagementservice.entity.Task;
import com.nbenliogludev.taskmanagementservice.mapper.CommentMapper;
import com.nbenliogludev.taskmanagementservice.repository.CommentRepository;
import com.nbenliogludev.taskmanagementservice.repository.TaskRepository;
import com.nbenliogludev.taskmanagementservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentCreateResponseDTO createComment(CommentCreateRequestDTO request) {
        Task task = taskRepository.findById(request.taskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Comment comment = new Comment();
        comment.setTask(task);
        comment.setUserId(request.userId());
        comment.setContent(request.content());
        comment.setCreatedAt(LocalDateTime.now());

        Comment saved = commentRepository.save(comment);
        return commentMapper.mapToCommentResponse(saved);
    }

    @Override
    public List<CommentCreateResponseDTO> getCommentsByTaskId(UUID taskId) {
        List<Comment> comments = commentRepository.findAllByTaskId(taskId);
        return comments.stream()
                .map(commentMapper::mapToCommentResponse)
                .toList();
    }

    @Override
    public void deleteComment(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }
}
