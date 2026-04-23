package com.example.notification.domain.events;

/**
 * Represents an event related to Todo actions.
 * This event is used to notify other services about changes in Todo state,
 * such as creation, updates, or status changes.
 */
public class TodoEvent {
    private String todoId;
    private String userId;
    private String action;

    /**
     * Constructs a TodoEvent with the specified parameters.
     *
     * @param todoId the ID of the Todo
     * @param userId the ID of the user associated with the Todo
     * @param action the action performed on the Todo (e.g., CREATED, UPDATED, DELETED)
     */
    public TodoEvent() {}

    public TodoEvent(String todoId, String userId, String action) {
        this.todoId = todoId;
        this.userId = userId;
        this.action = action;
    }

    public String getTodoId() {
        return todoId;
    }

    public String getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }
}