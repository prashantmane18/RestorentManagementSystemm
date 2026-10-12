
package com.restorentInfo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restorentInfo.dto.RegisterRequest;
import com.restorentInfo.dto.RegisterResponse;
import com.restorentInfo.service.UserService;

import com.restorentInfo.dto.LoginRequest;
import com.restorentInfo.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request) {

    	 RegisterResponse response =
    	            userService.register(request);

    	    return ResponseEntity
    	            .status(HttpStatus.CREATED)
    	            .body(response);
    }
    

@PostMapping("/login")
public ResponseEntity<?> login(
        @RequestBody LoginRequest request) {

        String token = userService.login(request);

        LoginResponse response =
                new LoginResponse(token, "Bearer");

        return ResponseEntity.ok(response);

    }
}


