package com.example.todo.application.usecases;

import com.example.todo.domain.entities.Todo;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.application.dto.ChangeStatusCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeStatusUseCase {

    private final TodoRepository todoRepository;

    @Autowired
    public ChangeStatusUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public Todo execute(ChangeStatusCommand command) {
        Todo todo = todoRepository.findById(command.getTodoId())
                .orElseThrow(() -> new TodoNotFoundException(command.getTodoId()));

        if (todo.isDone()) {
            throw new TodoModificationException("Cannot modify a DONE Todo.");
        }

        todo.setStatus(command.getNewStatus());
        return todoRepository.save(todo);
    }
}