package com.example.notification.infrastructure.kafka;

import com.example.notification.domain.events.TodoEvent;
import com.example.notification.application.handlers.TodoEventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumes Todo events from Kafka and processes them.
 * This class listens for events related to Todo actions and triggers appropriate handlers.
 */
@Component
public class TodoEventConsumer {

    private final TodoEventHandler todoEventHandler;

    @Autowired
    public TodoEventConsumer(TodoEventHandler todoEventHandler) {
        this.todoEventHandler = todoEventHandler;
    }

    /**
     * Listens to the Kafka topic for Todo events.
     *
     * @param record the consumed Kafka record containing the Todo event
     */
    @KafkaListener(topics = "todo-events", groupId = "notification-service")
    public void consume(ConsumerRecord<String, TodoEvent> record) {
        TodoEvent todoEvent = record.value();
        todoEventHandler.handle(todoEvent);
    }
}