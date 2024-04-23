package com.api.mazelo.dto.response;

import com.api.mazelo.entity.RoleEntity;
import com.api.mazelo.type.StatusType;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<RoleEntity> roles;
    private String email;
    private StatusType status;

    private String fullAddress;
    private Long pinCode;
    private String state;
    private String nearByArea;
}
