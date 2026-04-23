import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.notification.application.handlers.TodoEventHandler;
import com.example.notification.domain.events.TodoEvent;
import com.example.notification.infrastructure.external.EmailProviderClient;

public class TodoEventHandlerTest {

    @InjectMocks
    private TodoEventHandler todoEventHandler;

    @Mock
    private EmailProviderClient emailProviderClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleTodoCreatedEvent() {
        TodoEvent todoEvent = new TodoEvent("1", "Test Todo", "TODO");

        todoEventHandler.handleTodoCreatedEvent(todoEvent);

        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailProviderClient).sendNotification(emailCaptor.capture(), anyString());
        assertEquals("user@example.com", emailCaptor.getValue()); 
    }

    @Test
    public void testHandleTodoUpdatedEvent() {
        TodoEvent todoEvent = new TodoEvent("1", "Updated Todo", "IN_PROGRESS");

        todoEventHandler.handleTodoUpdatedEvent(todoEvent);

        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailProviderClient).sendNotification(emailCaptor.capture(), anyString());
        assertEquals("user@example.com", emailCaptor.getValue()); 
    }

    @Test
    public void testHandleTodoDeletedEvent() {
        TodoEvent todoEvent = new TodoEvent("1", "Deleted Todo", "DONE");

        todoEventHandler.handleTodoDeletedEvent(todoEvent);

        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailProviderClient).sendNotification(emailCaptor.capture(), anyString());
        assertEquals("user@example.com", emailCaptor.getValue()); 
    }
}