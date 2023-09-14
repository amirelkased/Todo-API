package com.elkased.todoapi.service;

import com.elkased.todoapi.dto.AuthenticationResponse;
import com.elkased.todoapi.dto.LoginRequest;
import com.elkased.todoapi.dto.RegisterRequest;
import com.elkased.todoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        
    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
    }
}
