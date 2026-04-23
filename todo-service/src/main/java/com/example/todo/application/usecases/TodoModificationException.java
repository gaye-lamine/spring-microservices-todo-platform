package com.example.todo.application.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TodoModificationException extends RuntimeException {
    public TodoModificationException(String message) {
        super(message);
    }
}
