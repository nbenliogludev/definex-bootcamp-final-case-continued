package com.nbenliogludev.taskmanagementservice.service;

import com.nbenliogludev.taskmanagementservice.dto.request.ProjectCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.ProjectUpdateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.ProjectCreateResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
public interface ProjectService {
    ProjectCreateResponseDTO createProject(ProjectCreateRequestDTO request);
    List<ProjectCreateResponseDTO> getAllProjects();
    ProjectCreateResponseDTO updateProject(ProjectUpdateRequestDTO request);
    void deleteProject(UUID id);
    void addMember(UUID projectId, UUID userId);
    void removeMember(UUID projectId, UUID userId);
}
