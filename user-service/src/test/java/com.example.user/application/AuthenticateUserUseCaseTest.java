import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.user.application.dto.AuthRequest;
import com.example.user.application.usecases.AuthenticateUserUseCase;
import com.example.user.domain.entities.User;
import com.example.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthenticateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticateUserUseCase authenticateUserUseCase;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("test@example.com", "password123");
    }

    @Test
    public void authenticateUser_ShouldReturnUser_WhenCredentialsAreValid() {
        AuthRequest authRequest = new AuthRequest("test@example.com", "password123");
        when(userRepository.findByEmail("test@example.com")).thenReturn(java.util.Optional.of(user));

        String token = authenticateUserUseCase.execute(authRequest);

        assertNotNull(token);
        assertEquals("mocked-jwt", token);
    }

    @Test
    public void authenticateUser_ShouldThrowException_WhenUserNotFound() {
        AuthRequest authRequest = new AuthRequest("unknown@example.com", "password123");
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authenticateUserUseCase.execute(authRequest);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void authenticateUser_ShouldThrowException_WhenPasswordIsIncorrect() {
        AuthRequest authRequest = new AuthRequest("test@example.com", "wrongpassword");
        when(userRepository.findByEmail("test@example.com")).thenReturn(java.util.Optional.of(user));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authenticateUserUseCase.execute(authRequest);
        });

        assertEquals("Invalid password", exception.getMessage());
    }
}