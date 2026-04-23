package com.example.todo.application.dto;

/**
 * Represents the command for creating a Todo.
 * Business rules:
 * - A Todo must have a title.
 * - Title must be at least 3 characters long.
 * - A Todo belongs to a User.
 * 
 * @param title the title of the Todo
 * @param userId the ID of the User associated with the Todo
 */
public record CreateTodoCommand(String title, Long userId) {
}