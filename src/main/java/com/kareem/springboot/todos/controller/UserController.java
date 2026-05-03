package com.kareem.springboot.todos.controller;


import com.kareem.springboot.todos.dto.PasswordRequest;
import com.kareem.springboot.todos.dto.UserResponse;
import com.kareem.springboot.todos.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User REST API Endpoint ",description = "Operation related to info of current user")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public UserResponse getUserInfo() {
        return userService.getUserInfo();
    }

    @DeleteMapping
    public void deleteUser() {
        userService.deleteUser();
    }


    @PutMapping("/password")
    public void passwordUpdate(@Valid @RequestBody PasswordRequest passwordRequest)throws Exception{
        userService.updatePassword(passwordRequest);
    }
}
