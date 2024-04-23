package com.api.mazelo.service.auth;

import com.api.mazelo.configuration.AppUserDetails;
import com.api.mazelo.entity.RestaurantEntity;
import com.api.mazelo.entity.RoleEntity;
import com.api.mazelo.entity.UserEntity;
import com.api.mazelo.exception.UnAuthenticatedException;
import com.api.mazelo.repository.UserRepository;
import com.api.mazelo.service.restaurant.RestaurantService;
import com.api.mazelo.service.user.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService restaurantService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userService.getUserByUserName(username);
        UserEntity restaurantUser = null;
        if (user == null) {
            RestaurantEntity restaurantEntity = restaurantService.getRestaurantByUserName(username);
            restaurantUser = UserEntity.builder()
                    .password(restaurantEntity.getPassword())
                    .email(restaurantEntity.getEmail())
                    .roles(restaurantEntity.getRoles())
                    .id(restaurantEntity.getId()).build();
        }
        if (user == null && restaurantUser == null) {
            throw new UnAuthenticatedException("UnAuthenticated");
        }
        if (user == null) {
            return new AppUserDetails(restaurantUser.getId(), restaurantUser.getEmail(),
                    restaurantUser.getPassword(), getAuthorities(restaurantUser.getRoles()));
        }
        return new AppUserDetails(user.getId(), user.getEmail(),
                user.getPassword(), getAuthorities(user.getRoles()));
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Set<RoleEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toSet());
    }
}