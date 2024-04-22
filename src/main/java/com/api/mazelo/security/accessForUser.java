package com.api.mazelo.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole(T(com.api.mazelo.type.UserRoleType).ADMIN)" +
        "|| hasRole(T(com.api.mazelo.type.UserRoleType).USER)")
public @interface accessForUser {
}
