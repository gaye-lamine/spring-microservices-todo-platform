package com.example.user.application;

import com.example.user.application.dto.AuthRequest;
import com.example.user.domain.entities.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.infrastructure.security.JwtProvider;
import com.example.user.application.usecases.AuthenticateUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticateUserUseCase authenticateUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAuthenticateSuccessfully() {
        AuthRequest authRequest = new AuthRequest("testuser@example.com", "password123");

        User user = Mockito.mock(User.class);
        when(user.getEmail()).thenReturn("testuser@example.com");
        when(user.getPassword()).thenReturn("encoded-password");

        when(userRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(authRequest.getPassword(), "encoded-password")).thenReturn(true);
        when(jwtProvider.generateToken("testuser@example.com")).thenReturn("mocked-jwt");

        String token = authenticateUserUseCase.execute(authRequest);
        assertNotNull(token);
        assertEquals("mocked-jwt", token);

        verify(userRepository, times(1)).findByEmail("testuser@example.com");
        verify(jwtProvider, times(1)).generateToken("testuser@example.com");
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        AuthRequest authRequest = new AuthRequest("nouser@example.com", "password");
        when(userRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> authenticateUserUseCase.execute(authRequest));
        assertTrue(ex.getMessage().toLowerCase().contains("not found"));
    }

    @Test
    void shouldThrowWhenPasswordInvalid() {
        AuthRequest authRequest = new AuthRequest("testuser@example.com", "wrongpass");

        User user = Mockito.mock(User.class);
        when(user.getPassword()).thenReturn("encoded-password");
        when(userRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(authRequest.getPassword(), "encoded-password")).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> authenticateUserUseCase.execute(authRequest));
        assertTrue(ex.getMessage().toLowerCase().contains("invalid"));
    }
}