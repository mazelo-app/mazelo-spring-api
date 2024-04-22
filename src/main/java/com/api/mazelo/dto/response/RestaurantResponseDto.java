package com.api.mazelo.dto.response;

import com.api.mazelo.type.StatusType;
import lombok.Data;

@Data
public class RestaurantResponseDto {
    private String name;
    private String address;
    private String contact;
    private String email;
    private StatusType status;
}
