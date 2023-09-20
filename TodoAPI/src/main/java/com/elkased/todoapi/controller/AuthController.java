package com.elkased.todoapi.controller;

import com.elkased.todoapi.model.AuthenticationResponse;
import com.elkased.todoapi.model.LoginDto;
import com.elkased.todoapi.model.RegisterDto;
import com.elkased.todoapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = {"/register", "/register/"})
    public ResponseEntity<AuthenticationResponse> register(@Validated @RequestBody RegisterDto registerDto) {

        return new ResponseEntity<>(authenticationService.register(registerDto), HttpStatus.OK);
    }

    @PostMapping(value = {"/login", "/login/"})
    public ResponseEntity<AuthenticationResponse> login(@Validated @RequestBody LoginDto loginDto) {

        return new ResponseEntity<>(authenticationService.authenticate(loginDto), HttpStatus.OK);
    }
}
