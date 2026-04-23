package com.example.user.presentation.rest;

import com.example.user.application.dto.AuthRequest;
import com.example.user.application.usecases.AuthenticateUserUseCase;
import com.example.shared.dto.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;

    public AuthController(AuthenticateUserUseCase authenticateUserUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
        String token = authenticateUserUseCase.execute(authRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}