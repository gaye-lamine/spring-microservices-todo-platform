package com.example.user.presentation.rest;

import com.example.user.application.usecases.AuthenticateUserUseCase;
import com.example.user.application.usecases.CreateUserUseCase;
import com.example.user.application.usecases.GetProfileUseCase;
import com.example.user.application.dto.AuthRequest;
import com.example.user.application.dto.CreateUserCommand;
import com.example.user.application.dto.UserProfileResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final GetProfileUseCase getProfileUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
                          AuthenticateUserUseCase authenticateUserUseCase,
                          GetProfileUseCase getProfileUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.getProfileUseCase = getProfileUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserCommand command) {
        var created = createUserUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
        String token = authenticateUserUseCase.execute(authRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable("id") Long id) {
        return ResponseEntity.ok(getProfileUseCase.execute(id));
    }
}