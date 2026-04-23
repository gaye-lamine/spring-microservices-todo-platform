# Todo Service Documentation

This document provides an overview of the Todo Service within the Todo Platform, including setup instructions, usage guidelines, and architectural details.

## Overview

The Todo Service is responsible for managing Todo items for users. It allows users to create, update, delete, and list their Todos while adhering to business rules and ensuring data integrity.

## Features

- Create a Todo
- Update a Todo
- Change the status of a Todo
- Delete a Todo
- List Todos with pagination

## Business Rules

- A Todo must have a title.
- The title must be at least 3 characters long.
- A Todo belongs to a User.
- A Todo has a status: TODO, IN_PROGRESS, DONE.
- A DONE Todo cannot be modified.
- A User can only access their own Todos.

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Maven
- Docker
- Kubernetes (for deployment)

### Building the Project

To build the Todo Service, navigate to the `todo-service` directory and run:

```bash
mvn clean install
```

### Running the Service

You can run the Todo Service locally using Docker. Build the Docker image with:

```bash
docker build -t todo-service .
```

Then run the container:

```bash
docker run -p 8080:8080 todo-service
```

### Accessing the API

Once the service is running, you can access the API at `http://localhost:8080/api/todos`.

## API Documentation

The API endpoints are documented using Swagger/OpenAPI. You can access the documentation at `http://localhost:8080/swagger-ui.html` once the service is running.

## Testing

Unit tests are provided for the domain and application layers. To run the tests, execute:

```bash
mvn test
```

## Deployment

Deployment configurations for Kubernetes are located in the `k8s` directory. You can deploy the service using the provided YAML files.

## Monitoring

The service includes Spring Boot Actuator for monitoring. You can access the actuator endpoints at `http://localhost:8080/actuator`.

## Logging

Structured logging is configured using Logback. Logs will be output to the console and can be configured further in the `logback-spring.xml` file.

## Conclusion

The Todo Service is a critical component of the Todo Platform, providing essential functionality for managing user Todos. Follow the setup instructions to get started, and refer to the API documentation for detailed usage guidelines.