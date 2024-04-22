package com.api.mazelo.service.restaurant;

import com.api.mazelo.dto.request.RestaurantRequestDto;
import com.api.mazelo.dto.response.RestaurantResponseDto;
import com.api.mazelo.entity.RestaurantEntity;
import com.api.mazelo.entity.UserEntity;
import com.api.mazelo.exception.ResourceNotFoundException;
import com.api.mazelo.mapper.RestaurantMapper;
import com.api.mazelo.repository.RestaurantRepository;
import com.api.mazelo.repository.specification.restaurant.RestaurantSpecification;
import com.api.mazelo.type.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantMapper restaurantMapper;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantSpecification restaurantSpecification;

    public Page<RestaurantResponseDto> getAllRestaurants(Pageable paging, StatusType statusType) {
        Specification<RestaurantEntity> spec = restaurantSpecification.statusIs(statusType);
        return restaurantRepository.findAll(spec, paging).map(m -> restaurantMapper.mapToResponseDto(m));
    }

    public RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantEntity restaurantEntity = restaurantMapper.mapToEntity(restaurantRequestDto);
        restaurantEntity.setStatus(StatusType.ACTIVE);
        restaurantEntity.setPassword(new BCryptPasswordEncoder().encode(restaurantRequestDto.getPassword()));
        RestaurantEntity restaurant = restaurantRepository.save(restaurantEntity);
        return restaurantMapper.mapToResponseDto(restaurant);
    }

    public void deleteRestaurant(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found on :: " + restaurantId));
        restaurantRepository.delete(restaurant);
    }
}
