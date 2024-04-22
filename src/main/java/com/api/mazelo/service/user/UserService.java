package com.api.mazelo.service.user;

import com.api.mazelo.dto.request.LocationRequestDto;
import com.api.mazelo.dto.request.UserRequestDto;
import com.api.mazelo.dto.response.UserResponseDto;
import com.api.mazelo.entity.UserEntity;
import com.api.mazelo.entity.auth.JwtResponse;
import com.api.mazelo.entity.auth.UserAuth;
import com.api.mazelo.exception.ResourceNotFoundException;
import com.api.mazelo.mapper.UserMapper;
import com.api.mazelo.repository.UserRepository;
import com.api.mazelo.repository.specification.user.UserSpecification;
import com.api.mazelo.type.StatusType;
import com.api.mazelo.utility.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserSpecification userSpec;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Page<UserResponseDto> getAllUsers(Pageable paging, StatusType statusType) {
        Specification<UserEntity> spec = userSpec.userStatusIs(statusType);
        return userRepository.findAll(spec, paging).map(entity -> userMapper.mapToResponseDto(entity));
    }

    public UserResponseDto getUserById(Long userId) {
        Specification<UserEntity> specification = userSpec.userIdIs(userId);
        return userRepository.findOne(specification).map(entity -> userMapper.mapToResponseDto(entity)).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
    }

    public void registerUser(UserRequestDto user) {
        UserEntity userToSave = userMapper.mapToEntity(user);
        userToSave.setStatus(StatusType.ACTIVE);
        userToSave.setPassword(new BCryptPasswordEncoder().encode(userToSave.getPassword()));
        userRepository.save(userToSave);
    }

    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        userRepository.delete(user);
    }

    public UserEntity getUserByUserName(String username) {
        Specification<UserEntity> spec = userSpec.userNameIs(username);
        return userRepository.findOne(spec).orElse(null);
    }

    public JwtResponse createAuthToken(UserEntity user) {
        Authentication authentication = new UserAuth(user.getId(), user.getEmail(), user.getPassword(), user.getRoles());
        return new JwtResponse(jwtTokenUtil.generateToken(authentication, user.getId()));
    }

    public UserResponseDto updateUserAddress(LocationRequestDto userDto, Long userId) {
        UserEntity existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        userMapper.mapUserLocationDtoToEntity(userDto, existingUser);
        UserEntity user = userRepository.save(existingUser);
        return userMapper.mapToResponseDto(user);
    }
}