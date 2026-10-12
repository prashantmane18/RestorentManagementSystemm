
package com.restorentInfo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restorentInfo.dto.RegisterRequest;
import com.restorentInfo.dto.RegisterResponse;
import com.restorentInfo.exception.InvalidCredentialsException;
import com.restorentInfo.exception.UsernameAlreadyExistsException;
import com.restorentInfo.model.User;
import com.restorentInfo.repository.UserRepository;

import com.restorentInfo.dto.LoginRequest;

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

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository
                .findByUsername(request.getUsername())
                .isPresent()) {

        	  throw new UsernameAlreadyExistsException(
                      "Username already exists"
              );
        }

        String hashedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getUsername(),
                hashedPassword,
                "USER"
        );

       User savedUser = userRepository.save(user);
       return new RegisterResponse(
               savedUser.getUsername(),
               savedUser.getRole()
               );
    }
    
    public String login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                new InvalidCredentialsException(
                        "Invalid username or password"
                ));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!passwordMatches) {
        	throw new InvalidCredentialsException(
                    "Invalid username or password"
            );
        }

        return jwtService.generateToken(user.getUsername());
    }
}
