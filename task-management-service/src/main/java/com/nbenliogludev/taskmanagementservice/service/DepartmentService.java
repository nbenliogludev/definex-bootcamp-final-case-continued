package com.nbenliogludev.taskmanagementservice.service;

import com.nbenliogludev.taskmanagementservice.dto.request.DepartmentCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.DepartmentUpdateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.DepartmentCreateResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface DepartmentService {
    DepartmentCreateResponseDTO createDepartment(DepartmentCreateRequestDTO request);
    List<DepartmentCreateResponseDTO> getAllDepartments();
    DepartmentCreateResponseDTO updateDepartment(DepartmentUpdateRequestDTO request);
    void deleteDepartment(UUID id);
}
