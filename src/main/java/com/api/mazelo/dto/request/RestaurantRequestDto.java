package com.api.mazelo.dto.request;

import lombok.Data;

@Data
public class RestaurantRequestDto {
    private String name;
    private String address;
    private String pinCode;
    private String contact;
    private String email;
    private String password;
}

