package com.api.mazelo.dto.request;

import lombok.Data;

@Data
public class LocationRequestDto {
    private String fullAddress;
    private Long pinCode;
    private String state;
    private String nearByArea;
}
