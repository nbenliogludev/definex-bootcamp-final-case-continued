package com.nbenliogludev.taskmanagementservice.mapper;

import com.nbenliogludev.taskmanagementservice.dto.request.TaskCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.TaskCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.entity.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Task mapToTask(TaskCreateRequestDTO request);

    @Mapping(source = "id", target = "taskId")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(target = "assigneeId", expression = "java(task.getAssigneeId() != null ? task.getAssigneeId().toString() : null)")
    TaskCreateResponseDTO mapToTaskResponse(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "comments", ignore = true)
    void updateTaskFromDto(TaskUpdateRequestDTO request, @MappingTarget Task task);
}
