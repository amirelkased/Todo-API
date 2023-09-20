package com.elkased.todoapi.service;

import com.elkased.todoapi.exception.LoginFailureException;
import com.elkased.todoapi.exception.RegisterFailureException;
import com.elkased.todoapi.jwt.JwtService;
import com.elkased.todoapi.model.AuthenticationResponse;
import com.elkased.todoapi.model.LoginDto;
import com.elkased.todoapi.model.RegisterDto;
import com.elkased.todoapi.model.Role;
import com.elkased.todoapi.model.entity.User;
import com.elkased.todoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    public AuthenticationResponse register(RegisterDto registerDto) {

        try {
            User user = User.builder()
                    .firstname(registerDto.getFirstname())
                    .lastname(registerDto.getLastname())
                    .username(registerDto.getUsername())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .role(Role.USER)
                    .build();

            userRepository.save(user);
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse(token);
        } catch (Exception e) {
            String message = "Registration was fail!";
            throw new RegisterFailureException(message);
        }
    }

    public AuthenticationResponse authenticate(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        authenticationManager.authenticate(authToken);

        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new LoginFailureException("Login process is fail, recheck your username or password"));
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
