# Notification Service Documentation

This document provides an overview of the Notification Service within the Todo platform, detailing its purpose, setup instructions, and usage guidelines.

## Overview

The Notification Service is responsible for handling notifications related to Todo events. It listens for events published by the Todo Service and sends notifications via email or other channels as required.

## Features

- Consumes Todo events from Kafka.
- Sends notifications to users based on Todo actions (e.g., creation, updates).
- Integrates with external email services for notification delivery.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd todo-platform/notification-service
   ```

2. **Build the Service**
   Ensure you have Maven installed, then run:
   ```bash
   mvn clean install
   ```

3. **Configuration**
   Update the `application.yml` file with the necessary configurations, including Kafka and email service settings.

4. **Run the Service**
   You can run the service using:
   ```bash
   mvn spring-boot:run
   ```

## Usage

The Notification Service does not expose any direct REST endpoints. It operates as a background service that listens for events. Ensure that the Todo Service is properly configured to publish events to the Kafka topic that this service subscribes to.

## Testing

Unit tests are provided in the `src/test/java/com.example.notification` directory. You can run the tests using:
```bash
mvn test
```

## Logging

The service uses structured logging. Check the logs for detailed information about the events being processed and any errors that may occur.

## Monitoring

The Notification Service is integrated with Spring Boot Actuator for monitoring. You can access the actuator endpoints to check the health and metrics of the service.

## Contribution

For contributions, please follow the standard pull request process. Ensure that your code adheres to the project's coding standards and includes appropriate tests.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.