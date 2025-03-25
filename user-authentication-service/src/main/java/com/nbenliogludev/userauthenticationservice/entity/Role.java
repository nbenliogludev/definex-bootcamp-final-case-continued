package com.nbenliogludev.userauthenticationservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nbenliogludev.userauthenticationservice.entity.Permission.*;

/**
 * @author nbenliogludev
 */
@RequiredArgsConstructor
public enum Role {

    GROUP_PROJECT_MANAGER(Set.of(
            DEPARTMENT_READ,
            DEPARTMENT_CREATE,
            DEPARTMENT_UPDATE,
            DEPARTMENT_DELETE,
            PROJECT_READ,
            PROJECT_CREATE,
            PROJECT_UPDATE,
            PROJECT_DELETE,
            TASK_READ,
            TASK_UPDATE_DETAILS,
            TASK_UPDATE_STATE,
            TASK_UPDATE_ATTACHMENTS,
            TASK_UPDATE_PRIORITY,
            TASK_DELETE,
            COMMENT_ADD,
            ATTACHMENT_UPLOAD
    )),

    PROJECT_MANAGER(Set.of(
            PROJECT_READ,
            PROJECT_CREATE,
            PROJECT_UPDATE,
            PROJECT_DELETE,
            TASK_READ,
            TASK_UPDATE_DETAILS,
            TASK_UPDATE_STATE,
            TASK_UPDATE_ATTACHMENTS,
            TASK_UPDATE_PRIORITY,
            TASK_ASSIGN_MEMBER,
            TASK_DELETE,
            COMMENT_ADD,
            ATTACHMENT_UPLOAD
    )),

    TEAM_LEADER(Set.of(
            TASK_READ,
            TASK_UPDATE_DETAILS,
            TASK_UPDATE_STATE,
            TASK_UPDATE_ATTACHMENTS,
            TASK_UPDATE_PRIORITY,
            TASK_ASSIGN_MEMBER,
            TASK_DELETE,
            COMMENT_ADD,
            ATTACHMENT_UPLOAD
    )),

    TEAM_MEMBER(Set.of(
            TASK_READ,
            TASK_UPDATE_STATE,
            TASK_UPDATE_ATTACHMENTS,
            COMMENT_ADD,
            ATTACHMENT_UPLOAD
    ));


    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
