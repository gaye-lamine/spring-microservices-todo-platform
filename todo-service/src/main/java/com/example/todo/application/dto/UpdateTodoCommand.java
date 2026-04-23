package com.example.todo.application.dto;

import com.example.todo.domain.entities.TodoStatus;

/**
 * Command to update a Todo's title and/or status.
 */
public class UpdateTodoCommand {
    private final Long id;
    private final String title;
    private final TodoStatus status;

    public UpdateTodoCommand(Long id, String title, TodoStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TodoStatus getStatus() {
        return status;
    }
}
