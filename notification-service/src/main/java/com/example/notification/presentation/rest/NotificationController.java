package com.example.notification.presentation.rest;

import com.example.notification.domain.events.TodoEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody TodoEvent todoEvent) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}