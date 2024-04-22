package com.api.mazelo.entity.auth;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
}