package com.example.todo.domain.valueobjects;

import java.util.Objects;

/**
 * Represents the value object for the Todo title.
 * Enforces validation rules for the title.
 * Business rules:
 * - Title must be at least 3 characters long.
 */
public class Title {
    private final String value;

    /**
     * Constructs a Title value object.
     * @param value the title value
     * @throws IllegalArgumentException if the title is less than 3 characters
     */
    public Title(String value) {
        if (value == null || value.length() < 3) {
            throw new IllegalArgumentException("Title must be at least 3 characters long.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Title)) return false;
        Title title = (Title) o;
        return Objects.equals(value, title.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}