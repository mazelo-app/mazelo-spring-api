package com.api.mazelo.controller.user;

import com.api.mazelo.dto.request.LocationRequestDto;
import com.api.mazelo.dto.request.UserRequestDto;
import com.api.mazelo.dto.response.UserResponseDto;
import com.api.mazelo.security.accessForAdmin;
import com.api.mazelo.security.accessForUser;
import com.api.mazelo.service.user.UserService;
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
@RequestMapping("/api/mazelo/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "get all users - paginated")
    @GetMapping("")
//    @accessForUser
    public ResponseEntity<?> getAllUsers(@RequestHeader Map<String, String> headers, @RequestParam(defaultValue = "", required = false) StatusType statusType, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName").ascending());
        Page<UserResponseDto> usersList = userService.getAllUsers(pageable, statusType);
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @Operation(summary = "get user by Id")
    @GetMapping("/{userId}")
//    @accessForUser
    public ResponseEntity<?> getUserById(@RequestHeader Map<String, String> headers, @PathVariable Long userId) {
        UserResponseDto responseDto = userService.getUserById(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "register user with basic details")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestHeader Map<String, String> headers, @RequestBody UserRequestDto userDto) {
        userService.registerUser(userDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Operation(summary = "update user address")
    @PutMapping("/update-address/{userId}")
//    @accessForUser
    public ResponseEntity<?> updateUserAddress(@RequestHeader Map<String, String> headers,
                                               @PathVariable Long userId,
                                               @RequestBody LocationRequestDto userDto) {
        return new ResponseEntity<>(userService.updateUserAddress(userDto, userId), HttpStatus.OK);
    }

    @Operation(summary = "delete user by id")
    @DeleteMapping("/{userId}")
//    @accessForAdmin
    public ResponseEntity<?> deleteUser(@RequestHeader Map<String, String> headers, @PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
