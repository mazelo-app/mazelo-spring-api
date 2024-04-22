package com.api.mazelo.controller.restaurant;

import com.api.mazelo.dto.request.RestaurantRequestDto;
import com.api.mazelo.dto.response.RestaurantResponseDto;
import com.api.mazelo.security.accessForAdmin;
import com.api.mazelo.security.accessForUser;
import com.api.mazelo.service.restaurant.RestaurantService;
import com.api.mazelo.type.StatusType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/mazelo/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Operation(summary = "get all restaurants - paginated")
    @GetMapping(path = "")
    @accessForUser
    public ResponseEntity<?> getAllRestaurants(@RequestHeader Map<String, String> headers, @RequestParam(defaultValue = "", required = false) StatusType statusType, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<RestaurantResponseDto> restaurantResponse = restaurantService.getAllRestaurants(pageable, statusType);
        return new ResponseEntity<>(restaurantResponse, HttpStatus.OK);
    }

    @Operation(summary = "register restaurant")
    @PostMapping(path = "/register")
    public ResponseEntity<?> addRestaurant(@RequestHeader Map<String, String> headers, @RequestBody RestaurantRequestDto restaurant) {
        RestaurantResponseDto restaurantResponse = restaurantService.saveRestaurant(restaurant);
        return new ResponseEntity<>(restaurantResponse, HttpStatus.CREATED);
    }

    @accessForAdmin
    @Operation(summary = "delete restaurant")
    @DeleteMapping(path = "/{restaurantId}")
    public ResponseEntity<?> deleteUser(@RequestHeader Map<String, String> headers, @PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
