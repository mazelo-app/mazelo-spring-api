package com.api.mazelo.controller.auth;

import com.api.mazelo.entity.RestaurantEntity;
import com.api.mazelo.entity.UserEntity;
import com.api.mazelo.entity.auth.JwtRequest;
import com.api.mazelo.exception.UnAuthenticatedException;
import com.api.mazelo.service.restaurant.RestaurantService;
import com.api.mazelo.service.user.UserService;
import com.api.mazelo.utility.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mazelo")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws UnAuthenticatedException {
        UserEntity user = userService.getUserByUserName(authenticationRequest.getUsername());
        UserEntity restaurantUser = null;
        if (user == null) {
            RestaurantEntity restaurant = restaurantService.getRestaurantByUserName(authenticationRequest.getUsername());
            restaurantUser = UserEntity.builder()
                    .password(restaurant.getPassword())
                    .email(restaurant.getEmail())
                    .roles(restaurant.getRoles())
                    .id(restaurant.getId()).build();
        }
        if (user == null && restaurantUser == null) {
            throw new UnAuthenticatedException("UnAuthenticated!");
        }
        if (user == null) {
            return new ResponseEntity<>(userService.createAuthToken(restaurantUser), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(userService.createAuthToken(user), HttpStatus.ACCEPTED);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}