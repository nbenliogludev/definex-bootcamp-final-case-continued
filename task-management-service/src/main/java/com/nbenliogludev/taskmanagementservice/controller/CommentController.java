package com.nbenliogludev.taskmanagementservice.controller;

import com.nbenliogludev.taskmanagementservice.dto.request.CommentCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.CommentCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.RestResponse;
import com.nbenliogludev.taskmanagementservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@Tag(name = "Comment Controller", description = "Manage comments")
@RestController
@RequestMapping("/api/task-management/comments")
@Validated
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Add a new comment")
    @PostMapping("/v1")
    public ResponseEntity<RestResponse<CommentCreateResponseDTO>> createComment(@RequestBody CommentCreateRequestDTO commentDto) {
        return ResponseEntity.ok(RestResponse.of(commentService.createComment(commentDto)));
    }

    @Operation(summary = "Get all comments by task ID")
    @GetMapping("/v1/task/{taskId}")
    public ResponseEntity<RestResponse<List<CommentCreateResponseDTO>>> getCommentsByTaskId(@PathVariable UUID taskId) {
        return ResponseEntity.ok(RestResponse.of(commentService.getCommentsByTaskId(taskId)));
    }

    @Operation(summary = "Delete a comment by ID")
    @DeleteMapping("/v1/{id}")
    public ResponseEntity<RestResponse<Void>> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
