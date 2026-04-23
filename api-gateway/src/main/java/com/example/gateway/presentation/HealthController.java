package com.example.gateway.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Provides health check endpoints for the API Gateway.
 */
@RestController
public class HealthController {

    /**
     * Health check endpoint to verify the API Gateway is running.
     * 
     * @return a simple message indicating the service is up.
     */
    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "API Gateway is up and running!";
    }
}