package com.example.todo.infrastructure.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes Todo events to Kafka for event-driven communication.
 * This component is responsible for sending messages related to Todo actions.
 */
@Component
public class TodoEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TodoEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes a Todo event to the specified Kafka topic.
     *
     * @param topic the Kafka topic to publish the event to
     * @param event the event message to be published
     */
    public void publish(String topic, String event) {
        kafkaTemplate.send(topic, event);
    }
}