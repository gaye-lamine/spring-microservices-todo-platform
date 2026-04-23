package com.example.todo.application.usecases;

import com.example.todo.application.dto.CreateTodoCommand;
import com.example.todo.application.dto.TodoResponse;
import com.example.todo.domain.entities.Todo;
import com.example.todo.domain.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTodoUseCase {

    private final TodoRepository todoRepository;

    public CreateTodoUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public TodoResponse execute(CreateTodoCommand command) {
        Todo todo = new Todo(command.title(), command.userId());
        Todo savedTodo = todoRepository.save(todo);
        return new TodoResponse(savedTodo);
    }
}