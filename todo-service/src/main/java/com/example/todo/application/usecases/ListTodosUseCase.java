package com.example.todo.application.usecases;

import com.example.todo.domain.entities.Todo;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.application.dto.TodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListTodosUseCase {

    private final TodoRepository todoRepository;

    @Autowired
    public ListTodosUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponse> execute(Long userId, Pageable pageable) {
        List<Todo> todos = todoRepository.findByUserId(userId, pageable);
        return todos.stream()
                .map(TodoResponse::new)
                .collect(Collectors.toList());
    }
}