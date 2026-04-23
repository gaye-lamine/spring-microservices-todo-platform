# User Service Documentation

## Overview

The User Service is a microservice responsible for managing user-related operations in the Todo platform. It provides functionalities for user creation, authentication, and profile retrieval.

## Features

- **Create User**: Allows the registration of new users with unique email addresses.
- **Authenticate User**: Validates user credentials and issues JWT tokens for secure access.
- **Retrieve User Profile**: Fetches the profile information of the authenticated user.

## Business Rules

- A user must have a valid and unique email address.
- Passwords are securely hashed using BCrypt.
- Each user can only access their own data.

## Setup Instructions

1. **Clone the Repository**:
   ```
   git clone <repository-url>
   cd todo-platform/user-service
   ```

2. **Build the Project**:
   Ensure you have Maven installed, then run:
   ```
   mvn clean install
   ```

3. **Run the Service**:
   You can run the User Service using:
   ```
   mvn spring-boot:run
   ```

4. **Access the API**:
   The User Service will be available at `http://localhost:8080/api/users`.

## API Documentation

API endpoints are documented using Swagger/OpenAPI. You can access the documentation at `http://localhost:8080/swagger-ui.html` once the service is running.

## Testing

Unit tests are included in the project to ensure the functionality of the User Service. You can run the tests using:
```
mvn test
```

## Docker

The User Service can be built as a Docker image. Use the following command to build the Docker image:
```
docker build -t user-service .
```

## Kubernetes Deployment

Deployment configurations for Kubernetes are available in the `k8s/deployments/user-service-deployment.yaml` file. Ensure to configure the necessary secrets and config maps before deploying.

## Monitoring

The User Service is integrated with Spring Boot Actuator for monitoring. You can access the actuator endpoints at `http://localhost:8080/actuator`.

## Logging

Structured logging is configured using Logback. Logs will be available in the console and can be configured to be sent to external logging systems.

## Conclusion

The User Service is a critical component of the Todo platform, providing essential user management functionalities. Follow the setup instructions to get started with development and testing.