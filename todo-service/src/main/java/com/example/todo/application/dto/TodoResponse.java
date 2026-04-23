package com.example.todo.application.dto;

import com.example.todo.domain.entities.Todo;
import com.example.todo.domain.entities.TodoStatus;

public class TodoResponse {
    private Long id;
    private String title;
    private String status;
    private Long userId;

    public TodoResponse() {}

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.status = todo.getStatus().name();
        this.userId = todo.getUserId();
    }

    public TodoResponse(Long id, String title, TodoStatus status) {
        this.id = id;
        this.title = title;
        this.status = status.name();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}