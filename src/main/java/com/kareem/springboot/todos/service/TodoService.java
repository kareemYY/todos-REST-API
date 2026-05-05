package com.kareem.springboot.todos.service;

import com.kareem.springboot.todos.dto.TodoRequest;
import com.kareem.springboot.todos.dto.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse createTodo(TodoRequest todoRequest);

    List<TodoResponse> getAllTodosByUser();

    TodoResponse toggleTodoCompletion(long id);

    void deleteTodoById(long id);
}
