package com.nbenliogludev.taskmanagementservice.controller;

import com.nbenliogludev.taskmanagementservice.dto.InfoLogDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.DepartmentCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.DepartmentUpdateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.DepartmentCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.RestResponse;
import com.nbenliogludev.taskmanagementservice.kafka.producer.LogProducer;
import com.nbenliogludev.taskmanagementservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author nbenliogludev
 */
@Tag(name = "Department Controller", description = "Manage departments")
@RestController
@RequestMapping("/api/task-management/departments")
@Validated
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final LogProducer logProducer;

    @Operation(summary = "Create a new department")
    @PostMapping("/v1")
    public ResponseEntity<RestResponse<DepartmentCreateResponseDTO>> createDepartment(@RequestBody DepartmentCreateRequestDTO depaptmentDto) {
        DepartmentCreateResponseDTO response = departmentService.createDepartment(depaptmentDto);
        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Department created")
                .description("Department with name '" + depaptmentDto.name() + "' created successfully.")
                .build());
        return ResponseEntity.ok(RestResponse.of(response));
    }

    @Operation(summary = "Get all departments")
    @GetMapping("/v1")
    public ResponseEntity<RestResponse<List<DepartmentCreateResponseDTO>>> getAllDepartments() {
        List<DepartmentCreateResponseDTO> departments = departmentService.getAllDepartments();
        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Retrieved departments")
                .description("Fetched " + departments.size() + " departments.")
                .build());
        return ResponseEntity.ok(RestResponse.of(departments));
    }

    @Operation(summary = "Update a department")
    @PutMapping("/v1")
    public ResponseEntity<RestResponse<DepartmentCreateResponseDTO>> updateDepartment(@RequestBody DepartmentUpdateRequestDTO request) {
        DepartmentCreateResponseDTO response = departmentService.updateDepartment(request);
        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Department updated")
                .description("Updated department with ID: " + request.id())
                .build());
        return ResponseEntity.ok(RestResponse.of(response));
    }

    @Operation(summary = "Delete a department by ID")
    @DeleteMapping("/v1/{id}")
    public ResponseEntity<RestResponse<Void>> deleteDepartment(@PathVariable UUID id) {
        departmentService.deleteDepartment(id);
        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Department deleted")
                .description("Deleted department with ID: " + id)
                .build());
        return ResponseEntity.ok(RestResponse.empty());
    }
}
