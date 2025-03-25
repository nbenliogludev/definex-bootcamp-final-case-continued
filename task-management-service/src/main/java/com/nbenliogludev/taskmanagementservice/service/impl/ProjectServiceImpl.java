package com.nbenliogludev.taskmanagementservice.service.impl;

import com.nbenliogludev.taskmanagementservice.dto.request.ProjectCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.ProjectUpdateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.ProjectCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.entity.Department;
import com.nbenliogludev.taskmanagementservice.entity.Project;
import com.nbenliogludev.taskmanagementservice.mapper.ProjectMapper;
import com.nbenliogludev.taskmanagementservice.repository.DepartmentRepository;
import com.nbenliogludev.taskmanagementservice.repository.ProjectRepository;
import com.nbenliogludev.taskmanagementservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectCreateResponseDTO createProject(ProjectCreateRequestDTO request) {
        Project project = projectMapper.mapToProject(request);
        Department department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        project.setDepartment(department);
        Project saved = projectRepository.save(project);
        return projectMapper.mapToProjectResponse(saved);
    }

    @Override
    public List<ProjectCreateResponseDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(projectMapper::mapToProjectResponse)
                .toList();
    }

    @Override
    public ProjectCreateResponseDTO updateProject(ProjectUpdateRequestDTO request) {
        Project project = projectRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setStatus(request.status());

        Department department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        project.setDepartment(department);

        Project updated = projectRepository.save(project);
        return projectMapper.mapToProjectResponse(updated);
    }


    @Override
    public void deleteProject(UUID id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.delete(project);
    }

    @Override
    public void addMember(UUID projectId, UUID userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.getTeamMembersIds().add(userId);
        projectRepository.save(project);
    }

    @Override
    public void removeMember(UUID projectId, UUID userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.getTeamMembersIds().remove(userId);
        projectRepository.save(project);
    }

}