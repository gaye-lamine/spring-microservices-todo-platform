package com.example.todo.infrastructure.persistence.jpa;

import com.example.todo.domain.entities.Todo;
import com.example.todo.domain.repository.TodoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implements the TodoRepository interface using JPA.
 * Provides methods for accessing Todo data in the database.
 */
@Repository
public interface JpaTodoRepository extends JpaRepository<Todo, Long>, TodoRepository {
    // Additional query methods can be defined here if needed
}