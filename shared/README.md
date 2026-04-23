# Todo Platform

This repository contains a production-grade Todo management platform built using Java and Spring Boot, following modern best practices and clean architecture principles.

## Overview

The Todo platform is designed to manage user-specific Todo items with features such as creating, updating, deleting, and listing Todos. The system is structured into microservices, ensuring scalability and maintainability.

## Microservices

1. **User Service**: Manages user accounts, authentication, and profiles.
2. **Todo Service**: Handles all operations related to Todos.
3. **API Gateway**: Acts as a single entry point for all client requests, routing them to the appropriate microservices.
4. **Notification Service**: Sends notifications based on events in the system.

## Architecture

The platform follows a microservices architecture with the following layers:

- **Domain**: Contains business logic and domain entities.
- **Application**: Contains use cases and application logic.
- **Infrastructure**: Manages data persistence, external services, and configurations.
- **Presentation**: Exposes RESTful APIs for client interactions.

## Technologies Used

- Java 17
- Spring Boot (latest stable version)
- PostgreSQL for data storage
- Redis for caching
- Kafka for event-driven communication
- Docker for containerization
- Kubernetes for orchestration
- Swagger/OpenAPI for API documentation
- GitHub Actions for CI/CD

## Getting Started

### Prerequisites

- Java 17
- Docker
- Kubernetes (Minikube or any other cluster)
- Maven

### Setup Instructions

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/todo-platform.git
   cd todo-platform
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the services using Docker:
   ```
   docker-compose up
   ```

4. Deploy to Kubernetes:
   ```
   kubectl apply -f k8s/
   ```

5. Access the API Gateway at `http://localhost:8080`.

## API Documentation

API endpoints are documented using Swagger. Access the documentation at `http://localhost:8080/swagger-ui.html`.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.