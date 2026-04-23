package com.example.todo.domain.repository;

import com.example.todo.domain.entities.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing Todo data.
 */
public interface TodoRepository {

    Todo save(Todo todo);

    void deleteById(Long id);

    Optional<Todo> findById(Long id);

    List<Todo> findByUserId(Long userId, Pageable pageable);

    Page<Todo> findAll(Pageable pageable);
}