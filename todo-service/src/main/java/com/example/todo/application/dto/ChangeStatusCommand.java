package com.example.todo.application.dto;

import com.example.todo.domain.entities.TodoStatus;

/**
 * Command to change a Todo's status.
 */
public class ChangeStatusCommand {
    private final Long todoId;
    private final TodoStatus newStatus;

    public ChangeStatusCommand(Long todoId, TodoStatus newStatus) {
        this.todoId = todoId;
        this.newStatus = newStatus;
    }

    public Long getTodoId() {
        return todoId;
    }

    public TodoStatus getNewStatus() {
        return newStatus;
    }
}
