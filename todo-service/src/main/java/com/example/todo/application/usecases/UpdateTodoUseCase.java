package com.example.todo.application.usecases;

import com.example.todo.domain.entities.Todo;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.application.dto.TodoResponse;
import com.example.todo.application.dto.UpdateTodoCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateTodoUseCase {

    private final TodoRepository todoRepository;

    @Autowired
    public UpdateTodoUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public TodoResponse execute(UpdateTodoCommand command) {
        Todo todo = todoRepository.findById(command.getId())
                .orElseThrow(() -> new TodoNotFoundException(command.getId()));

        if (todo.isDone()) {
            throw new TodoModificationException("Cannot modify a DONE Todo");
        }

        todo.update(command.getTitle(), command.getStatus());
        todoRepository.save(todo);

        return new TodoResponse(todo);
    }
}