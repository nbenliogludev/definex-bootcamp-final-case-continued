package com.nbenliogludev.taskmanagementservice.mapper;

import com.nbenliogludev.taskmanagementservice.dto.request.ProjectCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.ProjectCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author nbenliogludev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "department", ignore = true)
    Project mapToProject(ProjectCreateRequestDTO request);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "teamMembersIds", target = "teamMemberIds")
    ProjectCreateResponseDTO mapToProjectResponse(Project project);

}
