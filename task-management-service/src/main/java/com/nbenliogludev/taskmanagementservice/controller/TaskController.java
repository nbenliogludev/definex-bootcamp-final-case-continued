package com.nbenliogludev.taskmanagementservice.controller;

import com.nbenliogludev.taskmanagementservice.dto.InfoLogDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskCreateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateDetailsRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdatePriorityRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.request.TaskUpdateStateRequestDTO;
import com.nbenliogludev.taskmanagementservice.dto.response.RestResponse;
import com.nbenliogludev.taskmanagementservice.dto.response.TaskCreateResponseDTO;
import com.nbenliogludev.taskmanagementservice.kafka.producer.LogProducer;
import com.nbenliogludev.taskmanagementservice.service.TaskService;
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
@Tag(name = "Task Controller", description = "Manage tasks")
@RestController
@RequestMapping("/api/task-management/tasks")
@Validated
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final LogProducer logProducer;

    @Operation(summary = "Create a new task")
    @PostMapping("/v1")
    public ResponseEntity<RestResponse<TaskCreateResponseDTO>> createTask(@RequestBody TaskCreateRequestDTO taskDto) {
        var response = taskService.createTask(taskDto);
        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Task created")
                .description("Task titled '" + taskDto.title() + "' has been created.")
                .build());
        return ResponseEntity.ok(RestResponse.of(response));
    }

    @Operation(summary = "Update the title and description of a task")
    @PutMapping("/v1/{taskId}/details")
    public ResponseEntity<RestResponse<TaskCreateResponseDTO>> updateTaskDetails(
            @PathVariable UUID taskId,
            @RequestBody TaskUpdateDetailsRequestDTO request
    ) {
        var updatedTask = taskService.updateTaskDetails(taskId, request);

        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Task details updated")
                .description("Task with ID " + taskId
                        + " now has title='" + request.title()
                        + "', description='" + request.description() + "'")
                .build());

        return ResponseEntity.ok(RestResponse.of(updatedTask));
    }


    @Operation(summary = "Update the state of a task")
    @PutMapping("/v1/{taskId}/state")
    public ResponseEntity<RestResponse<TaskCreateResponseDTO>> updateTaskState(
            @PathVariable UUID taskId,
            @RequestBody TaskUpdateStateRequestDTO request
    ) {
        var updatedTask = taskService.updateTaskState(taskId, request);

        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Task state updated")
                .description("Task with ID " + taskId + " changed to state " + request.state())
                .build());

        return ResponseEntity.ok(RestResponse.of(updatedTask));
    }

    @Operation(summary = "Assign Task to a user")
    @PutMapping("/v1/{taskId}/assignee/{assigneeId}")
    public ResponseEntity<RestResponse<TaskCreateResponseDTO>> assignTaskToUser(
            @PathVariable UUID taskId,
            @PathVariable UUID assigneeId
    ) {

        var updatedTask = taskService.updateTaskAssignee(taskId, assigneeId);

        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Task assigned")
                .description("Task " + taskId + " assigned to user " + assigneeId)
                .build());

        return ResponseEntity.ok(RestResponse.of(updatedTask));
    }

    @Operation(summary = "Update the priority of a task")
    @PutMapping("/v1/{taskId}/priority")
    public ResponseEntity<RestResponse<TaskCreateResponseDTO>> updateTaskPriority(
            @PathVariable UUID taskId,
            @RequestBody TaskUpdatePriorityRequestDTO request
    ) {
        var updatedTask = taskService.updateTaskPriority(taskId, request);

        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Task priority updated")
                .description("Task with ID " + taskId + " changed priority to " + request.priority())
                .build());

        return ResponseEntity.ok(RestResponse.of(updatedTask));
    }

    @Operation(summary = "Get all tasks by project ID")
    @GetMapping("/v1/project/{projectId}")
    public ResponseEntity<RestResponse<List<TaskCreateResponseDTO>>> getAllByProjectId(@PathVariable UUID projectId) {
        return ResponseEntity.ok(RestResponse.of(taskService.getAllByProjectId(projectId)));
    }

    @Operation(summary = "Get all tasks by assignee ID")
    @GetMapping("/v1/assignee/{assigneeId}")
    public ResponseEntity<RestResponse<List<TaskCreateResponseDTO>>> getAllByAssigneeId(@PathVariable UUID assigneeId) {
        return ResponseEntity.ok(RestResponse.of(taskService.getAllByAssigneeId(assigneeId)));
    }

    @Operation(summary = "Delete a task by ID")
    @DeleteMapping("/v1/{id}")
    public ResponseEntity<RestResponse<Void>> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        logProducer.produceInfoLog(InfoLogDTO.builder()
                .service("task-management-service")
                .timestamp(LocalDateTime.now())
                .message("Task deleted")
                .description("Task with ID " + id + " has been deleted.")
                .build());
        return ResponseEntity.ok(RestResponse.empty());
    }
}
