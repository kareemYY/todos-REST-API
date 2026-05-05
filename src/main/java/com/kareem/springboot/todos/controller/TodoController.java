package com.kareem.springboot.todos.controller;


import com.kareem.springboot.todos.dto.TodoRequest;
import com.kareem.springboot.todos.dto.TodoResponse;
import com.kareem.springboot.todos.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Todo REST API Endpoint",description = "Operation fo managing user todo")
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create todo for user ", description = "Create Todo for the sign user")
    @PostMapping
    public TodoResponse createTodo(@Valid @RequestBody TodoRequest todoRequest) {
        return todoService.createTodo(todoRequest);
    }

    @Operation(summary = "All todos",description = "get all todo for the sign user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<TodoResponse> getAllTodosForUserSignIn() {
        return todoService.getAllTodosByUser();
    }


    @Operation(summary = "toggle Todo complete",description = "toggle todo complete for the sign user")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public TodoResponse toggleTodoCompletion(@PathVariable @Min(1) long id) {
        return todoService.toggleTodoCompletion(id);
    }

    @Operation(summary = "delete todo",description = "delete todo for the sign user")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable @Min(1) long id) {
        todoService.deleteTodoById(id);
    }

}
