package com.nbenliogludev.taskmanagementservice.mapper;

import com.nbenliogludev.taskmanagementservice.dto.request.DepartmentCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.DepartmentCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author nbenliogludev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(source = "name", target = "name")
    Department mapToDepartment(DepartmentCreateRequestDTO request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    DepartmentCreateResponseDTO mapToDepartmentResponse(Department department);
}
