package com.kareem.springboot.todos.controller;


import com.kareem.springboot.todos.dto.AuthenticationRequest;
import com.kareem.springboot.todos.dto.AuthenticationResponse;
import com.kareem.springboot.todos.dto.RegisterRequest;
import com.kareem.springboot.todos.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication REST API Endpoint",description = "Operation related to register & login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    @Operation(summary = "Register a user ", description = "Create new user in database")
    public void register (@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
    }


    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login a user",description = "submit email & password to authenticate user")
    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }












}
