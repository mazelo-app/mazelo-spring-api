package com.api.mazelo.mapper;

import com.api.mazelo.dto.request.LocationRequestDto;
import com.api.mazelo.dto.request.RestaurantRequestDto;
import com.api.mazelo.dto.response.RestaurantResponseDto;
import com.api.mazelo.entity.RestaurantEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    private final ModelMapper modelMapper;

    public RestaurantMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

//        Converter<UserEntity, String> userEntityToStringConverter = ctx -> ctx.getSource() == null
//                ? null
//                : ctx.getSource().getFirstName() + " " + ctx.getSource().getLastName();
//
//        // Register the custom converter
//        this.modelMapper.createTypeMap(UserEntity.class, String.class).setConverter(userEntityToStringConverter);
    }

    public RestaurantResponseDto mapToResponseDto(RestaurantEntity source) {
        return source == null ? null : this.modelMapper.map(source, RestaurantResponseDto.class);
    }

    public RestaurantEntity mapToEntity(RestaurantRequestDto source) {
        return source == null ? null : this.modelMapper.map(source, RestaurantEntity.class);
    }

    public void mapRestaurantLocationDtoToEntity(LocationRequestDto src, RestaurantEntity dest) {
        this.modelMapper.map(src, dest);
    }
}
