package com.example.todo.presentation.rest;

import org.springframework.web.bind.annotation.*;

import com.example.todo.application.dto.CreateTodoCommand;
import com.example.todo.application.dto.UpdateTodoCommand;
import com.example.todo.application.dto.ChangeStatusCommand;
import com.example.todo.application.dto.TodoResponse;
import com.example.todo.application.usecases.CreateTodoUseCase;
import com.example.todo.application.usecases.ListTodosUseCase;
import com.example.todo.application.usecases.UpdateTodoUseCase;
import com.example.todo.application.usecases.ChangeStatusUseCase;
import com.example.todo.presentation.rest.dto.TodoRequest;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Handles operations related to Todos.
 * Business rules:
 * - A Todo must have a title with at least 3 characters.
 * - A DONE Todo cannot be modified.
 * - A User can only access their own Todos.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final CreateTodoUseCase createTodoUseCase;
    private final UpdateTodoUseCase updateTodoUseCase;
    private final ChangeStatusUseCase changeStatusUseCase;
    private final ListTodosUseCase listTodosUseCase;

    public TodoController(CreateTodoUseCase createTodoUseCase,
                          UpdateTodoUseCase updateTodoUseCase,
                          ChangeStatusUseCase changeStatusUseCase,
                          ListTodosUseCase listTodosUseCase) {
        this.createTodoUseCase = createTodoUseCase;
        this.updateTodoUseCase = updateTodoUseCase;
        this.changeStatusUseCase = changeStatusUseCase;
        this.listTodosUseCase = listTodosUseCase;
    }

    /**
     * Creates a new Todo.
     * @param request contains input data for the new Todo.
     * @return created Todo response.
     */
    @PostMapping
    public ResponseEntity<?> createTodo(
        @RequestBody TodoRequest request,
        @RequestParam(name = "userId", required = false) Long userId
    ) {
        CreateTodoCommand command = new CreateTodoCommand(request.getTitle(), userId);
        TodoResponse response = createTodoUseCase.execute(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Updates an existing Todo.
     * @param id the ID of the Todo to update.
     * @param request contains updated data for the Todo.
     * @return updated Todo response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable("id") Long id, @Valid @RequestBody TodoRequest request) {
        UpdateTodoCommand command = new UpdateTodoCommand(id, request.getTitle(), null);
        TodoResponse response = updateTodoUseCase.execute(command);
        return ResponseEntity.ok(response);
    }

    /**
     * Changes the status of a Todo.
     * @param id the ID of the Todo to change status.
     * @param status the new status for the Todo.
     * @return updated Todo response.
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(
        @PathVariable("id") Long id,
        @RequestParam(name = "status", required = false) String status /* ou @RequestBody String status */
    ) {
        ChangeStatusCommand command = new ChangeStatusCommand(id, com.example.todo.domain.entities.TodoStatus.valueOf(status));
        com.example.todo.application.dto.TodoResponse response = new com.example.todo.application.dto.TodoResponse(changeStatusUseCase.execute(command));
        return ResponseEntity.ok(response);
    }

    /**
     * Lists all Todos for the authenticated user with pagination.
     * @param page the page number.
     * @param size the size of the page.
     * @return list of Todos.
     */
    @GetMapping
    public ResponseEntity<?> listTodos(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "userId", required = false) Long userId
    ) {
        List<TodoResponse> todos = listTodosUseCase.execute(userId, org.springframework.data.domain.PageRequest.of(page, size));
        return ResponseEntity.ok(todos);
    }
}