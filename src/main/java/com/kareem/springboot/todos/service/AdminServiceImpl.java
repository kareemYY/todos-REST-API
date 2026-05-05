package com.kareem.springboot.todos.service;

import com.kareem.springboot.todos.dto.UserResponse;
import com.kareem.springboot.todos.entity.Authority;
import com.kareem.springboot.todos.entity.User;
import com.kareem.springboot.todos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements AdminService {


    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false)
                .map(this::convertToUserResponse).toList();
    }

    @Transactional
    @Override
    public UserResponse promoteToAdmin(long id) {
        Optional<User> user =userRepository.findById(id);

        if (user.isEmpty()||isAdmin(user.get())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Already Admin Or not exist");
        }
        user.get().addAuthority(new Authority("ROLE_ADMIN"));

        User savedUser = userRepository.save(user.get());
        return convertToUserResponse(savedUser);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        User user =userRepository.findById(id).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found"));

        if (isAdmin(user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Already Admin");
        }
        userRepository.delete(user);

    }


    private UserResponse convertToUserResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getFirstName()+" "+ user.getLastName(),
                user.getEmail(),
                user.getAuthorities().stream().
                        map(auth -> (Authority) auth).toList());
    }
    private boolean isAdmin(User user){
        return user.getAuthorities().stream()
                .anyMatch(authority ->"ROLE_ADMIN".equals(authority.getAuthority()));
    }









}
