
package com.restorentInfo.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.restorentInfo.dto.RegisterRequest;
import com.restorentInfo.model.User;
import com.restorentInfo.repository.UserRepository;

import com.restorentInfo.dto.LoginRequest;
import com.restorentInfo.service.JwtService;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {

        if (userRepository
                .findByUsername(request.getUsername())
                .isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,"Username already exists");
        }

        String hashedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getUsername(),
                hashedPassword,
                "USER"
        );

        userRepository.save(user);
    }
    
    public String login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid username or password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!passwordMatches) {
            throw new IllegalArgumentException(
                    "Invalid username or password");
        }

        return jwtService.generateToken(user.getUsername());
    }
}
