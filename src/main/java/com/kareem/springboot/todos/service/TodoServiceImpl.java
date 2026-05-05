package com.kareem.springboot.todos.service;

import com.kareem.springboot.todos.dto.TodoRequest;
import com.kareem.springboot.todos.dto.TodoResponse;
import com.kareem.springboot.todos.entity.Todo;
import com.kareem.springboot.todos.entity.User;
import com.kareem.springboot.todos.repository.TodoRepository;
import com.kareem.springboot.todos.repository.UserRepository;
import com.kareem.springboot.todos.util.FindAuthenticatedUser;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class TodoServiceImpl implements TodoService {


    private final TodoRepository todoRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public TodoServiceImpl(TodoRepository todoRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.todoRepository = todoRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Transactional
    @Override
    public TodoResponse createTodo(TodoRequest todoRequest) {

        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Todo todo = new Todo(
                todoRequest.getTitle(),
                todoRequest.getDescription(),
                todoRequest.getPriority(),
                false,
                currentUser
        );
        Todo savedTodo= todoRepository.save(todo);

        return convertToTodoResponse(savedTodo) ;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TodoResponse> getAllTodosByUser() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        return todoRepository.findByOwner(currentUser)
                .stream()
                .map(this::convertToTodoResponse)
                .toList();
    }

    @Transactional
    @Override
    public TodoResponse toggleTodoCompletion(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Todo todo = findTodoByIdAndOwner(id, currentUser);

        todo.setComplete(!todo.isComplete());
        Todo updatedTodo = todoRepository.save(todo);

        return convertToTodoResponse(updatedTodo);
    }



    @Override
    public void deleteTodoById(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Todo todo = findTodoByIdAndOwner(id, currentUser);
        todoRepository.delete(todo);
    }

    private TodoResponse convertToTodoResponse(Todo todo){
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getPriority(),
                todo.isComplete()
        );
    }

    private Todo findTodoByIdAndOwner(long id, User currentUser) {
        Optional<Todo> todo= todoRepository.findByIdAndOwner(id, currentUser);

        if (todo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Todo not found");
        }
        return todo.get();
    }









}
