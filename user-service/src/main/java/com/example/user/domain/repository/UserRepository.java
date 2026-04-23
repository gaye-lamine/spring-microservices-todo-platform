package com.example.user.domain.repository;

import com.example.user.domain.entities.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    void deleteById(Long id);
}