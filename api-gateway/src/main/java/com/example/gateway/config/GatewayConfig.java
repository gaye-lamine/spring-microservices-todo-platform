package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service", r -> r.path("/api/users/**")
                        .uri("http://user-service:8081"))
                .route("todo_service", r -> r.path("/api/todos/**")
                        .uri("http://todo-service:8080"))
                .route("notification_service", r -> r.path("/api/notifications/**")
                        .uri("http://notification-service:8082"))
                .build();
    }
}