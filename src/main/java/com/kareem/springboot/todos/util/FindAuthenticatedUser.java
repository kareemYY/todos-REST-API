package com.kareem.springboot.todos.util;

import com.kareem.springboot.todos.entity.User;

public interface FindAuthenticatedUser {

    User getAuthenticatedUser();
}
