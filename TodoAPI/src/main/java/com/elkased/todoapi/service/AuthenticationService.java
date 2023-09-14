package com.elkased.todoapi.service;

import com.elkased.todoapi.dto.*;
import com.elkased.todoapi.exception.LoginFailureException;
import com.elkased.todoapi.jwt.JwtService;
import com.elkased.todoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        UserDTO userDTO = mapRegisterRequestToUser(registerRequest);
        userRepository.save(userDTO);
        String token = jwtService.generateToken(userDTO);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest) {

        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            authenticationManager.authenticate(authToken);

            UserDTO userDTO = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
            String token = jwtService.generateToken(userDTO);

            return new AuthenticationResponse(token);
        } catch (AuthenticationException e) {
            String message = "Login process is fail, recheck your username or password";
            throw new LoginFailureException(message);
        }
    }

    private UserDTO mapRegisterRequestToUser(RegisterRequest registerRequest) {

        UserDTO userDTO = new UserDTO();

        userDTO.setFirstname(registerRequest.getFirstname());
        userDTO.setLastname(registerRequest.getLastname());
        userDTO.setUsername(registerRequest.getUsername());
        userDTO.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userDTO.setRole(Role.USER);

        return userDTO;
    }
}
