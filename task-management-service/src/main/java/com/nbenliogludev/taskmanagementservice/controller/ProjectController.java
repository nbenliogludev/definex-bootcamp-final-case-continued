package com.nbenliogludev.taskmanagementservice.controller;

import com.nbenliogludev.taskmanagementservice.dto.request.ProjectCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.ProjectUpdateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.ProjectCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.RestResponse;
import com.nbenliogludev.taskmanagementservice.service.ProjectService;
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
@Tag(name = "Project Controller", description = "Manage projects")
@RestController
@RequestMapping("/api/task-management/projects")
@Validated
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @Operation(summary = "Create a new project")
    @PostMapping("/v1")
    public ResponseEntity<RestResponse<ProjectCreateResponseDTO>> createProject(@RequestBody ProjectCreateRequestDTO projectDto) {
        return ResponseEntity.ok(RestResponse.of(projectService.createProject(projectDto)));
    }

    @Operation(summary = "Get all projects")
    @GetMapping("/v1")
    public ResponseEntity<RestResponse<List<ProjectCreateResponseDTO>>> getAllProjects() {
        return ResponseEntity.ok(RestResponse.of(projectService.getAllProjects()));
    }

    @Operation(summary = "Update a project")
    @PutMapping("/v1")
    public ResponseEntity<RestResponse<ProjectCreateResponseDTO>> updateProject(
            @RequestBody ProjectUpdateRequestDTO request) {
        return ResponseEntity.ok(RestResponse.of(projectService.updateProject(request)));
    }

    @Operation(summary = "Delete a project by ID")
    @DeleteMapping("/v1/{id}")
    public ResponseEntity<RestResponse<Void>> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(summary = "Add a member to a project")
    @PatchMapping("/v1/{projectId}/members/add")
    public ResponseEntity<Void> addMemberToProject(@PathVariable UUID projectId, @RequestParam UUID memberId) {
        projectService.addMember(projectId, memberId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove a member from a project")
    @PatchMapping("/v1/{projectId}/members/remove")
    public ResponseEntity<Void> removeMemberFromProject(@PathVariable UUID projectId, @RequestParam UUID memberId) {
        projectService.removeMember(projectId, memberId);
        return ResponseEntity.ok().build();
    }

}
