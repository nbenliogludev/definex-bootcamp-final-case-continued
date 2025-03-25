package com.nbenliogludev.taskmanagementservice.service;

import com.nbenliogludev.taskmanagementservice.dto.request.CommentCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.CommentCreateResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface CommentService {

    CommentCreateResponseDTO createComment(CommentCreateRequestDTO request);

    List<CommentCreateResponseDTO> getCommentsByTaskId(UUID taskId);

    void deleteComment(UUID commentId);
}
