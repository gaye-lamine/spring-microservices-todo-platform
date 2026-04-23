package com.example.todo.presentation.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Represents the request DTO for creating or updating a Todo.
 * Business rules:
 * - A Todo must have a title.
 * - Title must be at least 3 characters.
 * 
 * @param title the title of the Todo
 */
public class TodoRequest {

    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, message = "Title must be at least 3 characters")
    private String title;

    public TodoRequest() {
    }

    public TodoRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}