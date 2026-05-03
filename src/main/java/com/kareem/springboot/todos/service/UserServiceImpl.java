package com.kareem.springboot.todos.service;

import com.kareem.springboot.todos.dto.UserResponse;
import com.kareem.springboot.todos.entity.Authority;
import com.kareem.springboot.todos.entity.User;
import com.kareem.springboot.todos.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null||!authentication.isAuthenticated()||authentication.getPrincipal().equals("anonymousUser")) {
          throw new AccessDeniedException("Authentication Requires");
        }
        User user = (User) authentication.getPrincipal();
        return new UserResponse(user.getId(),
                user.getFirstName()+" "+user.getLastName(),
                user.getEmail(),
                user.getAuthorities().stream().map(auth -> (Authority)  auth).toList());
    }
}
