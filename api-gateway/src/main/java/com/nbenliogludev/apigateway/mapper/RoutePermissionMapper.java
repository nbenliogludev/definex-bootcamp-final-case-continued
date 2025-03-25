package com.nbenliogludev.apigateway.mapper;

import org.springframework.http.HttpMethod;

import java.util.Map;

public class RoutePermissionMapper {

    private static final Map<String, String> ROUTE_PERMISSIONS = Map.ofEntries(

            Map.entry("GET_/api/departments/v1", "department:read"),
            Map.entry("POST_/api/departments/v1", "department:create"),
            Map.entry("PUT_/api/departments/v1", "department:update"),
            Map.entry("DELETE_/api/departments/v1/{id}", "department:delete"),

            Map.entry("GET_/api/task-management/projects/v1", "project:read"),
            Map.entry("POST_/api/task-management/projects/v1", "project:create"),
            Map.entry("PUT_/api/task-management/projects/v1", "project:update"),
            Map.entry("DELETE_/api/task-management/projects/v1/{id}", "project:delete"),

            Map.entry("GET_/api/task-management/tasks/v1/project/{projectId}", "task:read"),
            Map.entry("GET_/api/task-management/tasks/v1/assignee/{assigneeId}", "task:read"),
            Map.entry("POST_/api/task-management/tasks/v1", "task:update_details"),
            Map.entry("PUT_/api/task-management/tasks/v1", "task:update_details"),
            Map.entry("DELETE_/api/task-management/tasks/v1/{id}", "task:delete"),

            Map.entry("PATCH_/api/task-management/tasks/v1/state", "task:update_state"),
            Map.entry("PATCH_/api/task-management/tasks/v1/priority", "task:update_priority"),
            Map.entry("PATCH_/api/task-management/tasks/v1/attachments", "task:update_attachments"),
            Map.entry("PATCH_/api/task-management/tasks/v1/assign", "task:assign_member"),

            Map.entry("POST_/api/task-management/comments/v1", "comment:add"),

            Map.entry("POST_/api/files/v1/upload", "attachment:upload")
    );

    public static String getRequiredPermission(HttpMethod method, String path) {
        String key = method.name() + "_" + path;
        return ROUTE_PERMISSIONS.get(key);
    }
}
