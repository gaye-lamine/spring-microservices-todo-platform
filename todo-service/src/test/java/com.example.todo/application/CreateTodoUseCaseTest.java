import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.todo.application.dto.CreateTodoCommand;
import com.example.todo.application.usecases.CreateTodoUseCase;
import com.example.todo.domain.entities.Todo;
import com.example.todo.domain.repository.TodoRepository;

class CreateTodoUseCaseTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private CreateTodoUseCase createTodoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTodoSuccessfully() {
        CreateTodoCommand command = new CreateTodoCommand("Test Todo", "userId");
        Todo todo = new Todo(command.getTitle(), command.getUserId());

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo createdTodo = createTodoUseCase.execute(command);

        assertNotNull(createdTodo);
        assertEquals("Test Todo", createdTodo.getTitle());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void shouldThrowExceptionWhenTitleIsTooShort() {
        CreateTodoCommand command = new CreateTodoCommand("No", "userId");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            createTodoUseCase.execute(command);
        });

        assertEquals("Title must be at least 3 characters", exception.getMessage());
        verify(todoRepository, never()).save(any(Todo.class));
    }
}