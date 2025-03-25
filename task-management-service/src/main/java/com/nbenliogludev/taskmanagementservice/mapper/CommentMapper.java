package com.nbenliogludev.taskmanagementservice.mapper;

import com.nbenliogludev.taskmanagementservice.dto.response.CommentCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author nbenliogludev
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(source = "task.id", target = "taskId")
    CommentCreateResponseDTO mapToCommentResponse(Comment comment);
}
