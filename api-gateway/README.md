# API Gateway Documentation

This document provides an overview of the API Gateway for the Todo platform, including setup instructions, usage guidelines, and endpoint descriptions.

## Overview

The API Gateway serves as the entry point for all client requests to the Todo platform. It handles routing, authentication, and rate limiting, ensuring that requests are directed to the appropriate microservices.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd todo-platform/api-gateway
   ```

2. **Build the Project**
   Use Maven to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the API Gateway**
   You can run the API Gateway locally using the following command:
   ```bash
   mvn spring-boot:run
   ```

4. **Docker Setup**
   To build and run the API Gateway in a Docker container, use the following commands:
   ```bash
   docker build -t api-gateway .
   docker run -p 8080:8080 api-gateway
   ```

## Usage Guidelines

The API Gateway provides a unified interface for interacting with the Todo and User services. All requests should be made to the API Gateway, which will forward them to the appropriate service.

### Health Check Endpoint

- **GET /health**
  - Returns the health status of the API Gateway.

### Authentication

The API Gateway uses JWT for authentication. Clients must include a valid JWT token in the `Authorization` header for protected endpoints.

## API Endpoints

### User Service Endpoints

- **POST /users**
  - Create a new user.

- **POST /auth/login**
  - Authenticate a user and return a JWT token.

- **GET /users/me**
  - Retrieve the authenticated user's profile.

### Todo Service Endpoints

- **POST /todos**
  - Create a new Todo.

- **PUT /todos/{id}**
  - Update an existing Todo.

- **PATCH /todos/{id}/status**
  - Change the status of a Todo.

- **DELETE /todos/{id}**
  - Delete a Todo.

- **GET /todos**
  - List all Todos for the authenticated user with pagination.

## Monitoring and Logging

The API Gateway integrates with Spring Boot Actuator for monitoring and provides structured logging for all incoming requests and responses.

## Conclusion

The API Gateway is a crucial component of the Todo platform, providing a seamless interface for users to interact with the underlying microservices. For further details on specific endpoints and their usage, refer to the OpenAPI documentation available in the respective service directories.