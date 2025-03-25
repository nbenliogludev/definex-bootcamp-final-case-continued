package com.nbenliogludev.userauthenticationservice.entity;

import lombok.Getter;

@Getter
public enum Permission {

    DEPARTMENT_READ("department:read"),
    DEPARTMENT_CREATE("department:create"),
    DEPARTMENT_UPDATE("department:update"),
    DEPARTMENT_DELETE("department:delete"),

    PROJECT_READ("project:read"),
    PROJECT_CREATE("project:create"),
    PROJECT_UPDATE("project:update"),
    PROJECT_DELETE("project:delete"),

    TASK_READ("task:read"),
    TASK_UPDATE_DETAILS("task:update_details"),
    TASK_UPDATE_STATE("task:update_state"),
    TASK_UPDATE_ATTACHMENTS("task:update_attachments"),
    TASK_UPDATE_PRIORITY("task:update_priority"),
    TASK_ASSIGN_MEMBER("task:assign_member"),
    TASK_DELETE("task:delete"),

    COMMENT_ADD("comment:add"),

    ATTACHMENT_UPLOAD("attachment:upload");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
