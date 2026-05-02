package com.kareem.springboot.todos.service;

import com.kareem.springboot.todos.dto.AuthenticationRequest;
import com.kareem.springboot.todos.dto.AuthenticationResponse;
import com.kareem.springboot.todos.dto.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest input)throws Exception;

    AuthenticationResponse login(AuthenticationRequest request);
}
