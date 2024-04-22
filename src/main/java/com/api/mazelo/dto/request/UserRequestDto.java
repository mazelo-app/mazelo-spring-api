package com.api.mazelo.dto.request;

import com.api.mazelo.entity.RoleEntity;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private Set<RoleEntity> roles;
    private String email;
    private String password;
}

