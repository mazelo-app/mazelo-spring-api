package com.api.mazelo.mapper;

import com.api.mazelo.dto.request.LocationRequestDto;
import com.api.mazelo.dto.request.UserRequestDto;
import com.api.mazelo.dto.response.UserResponseDto;
import com.api.mazelo.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponseDto mapToResponseDto(UserEntity source) {
        return source == null ? null : this.modelMapper.map(source, UserResponseDto.class);
    }

    public UserEntity mapToEntity(UserRequestDto source) {
       return source == null ? null : this.modelMapper.map(source, UserEntity.class);
    }

    public void mapUserLocationDtoToEntity(LocationRequestDto source, UserEntity dest) {
        this.modelMapper.map(source, dest);
    }
}
