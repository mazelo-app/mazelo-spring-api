package com.api.mazelo.dto.response;

import com.api.mazelo.type.StatusType;
import lombok.Data;

@Data
public class RestaurantResponseDto {
    private String name;
    private String contact;
    private String email;
    private StatusType status;
    private Long pinCode;
    private String fullAddress;
    private Long state;
    private Long nearByArea;
}
