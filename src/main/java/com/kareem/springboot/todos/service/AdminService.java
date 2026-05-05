package com.kareem.springboot.todos.service;

import com.kareem.springboot.todos.dto.UserResponse;

import java.util.List;

public interface AdminService {

    List<UserResponse> getAllUsers();

    UserResponse promoteToAdmin(long id);

    void deleteUser(long id);
}
