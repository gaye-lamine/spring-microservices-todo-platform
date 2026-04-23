import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TodoDomainTests {

    @InjectMocks
    private Todo todo;

    @Mock
    private Title title;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTodoCreation() {
        String expectedTitle = "Sample Todo";
        when(title.getValue()).thenReturn(expectedTitle);
        
        todo = new Todo(title, "USER_ID");

        assertEquals(expectedTitle, todo.getTitle().getValue());
        assertEquals("USER_ID", todo.getUserId());
        assertEquals(Todo.Status.TODO, todo.getStatus());
    }

    @Test
    public void testTodoStatusChange() {
        todo = new Todo(title, "USER_ID");
        
        todo.changeStatus(Todo.Status.IN_PROGRESS);

        assertEquals(Todo.Status.IN_PROGRESS, todo.getStatus());
    }

    @Test
    public void testTodoModificationNotAllowedWhenDone() {
        todo = new Todo(title, "USER_ID");
        todo.changeStatus(Todo.Status.DONE);

        Exception exception = null;
        try {
            todo.changeStatus(Todo.Status.TODO);
        } catch (Exception e) {
            exception = e;
        }
        assertEquals("Cannot modify a DONE Todo", exception.getMessage());
    }
}