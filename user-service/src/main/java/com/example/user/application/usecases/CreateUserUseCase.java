package com.example.user.application.usecases;

import com.example.user.application.dto.CreateUserCommand;
import com.example.user.domain.entities.User;
import com.example.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles the creation of a new User.
 * Business rules:
 * - Email must be valid and unique.
 * 
 * @param command contains input data for creating a User.
 * @return created User entity.
 */
@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(CreateUserCommand command) {
        User user = new User(command.email(), passwordEncoder.encode(command.password()));
        return userRepository.save(user);
    }
}