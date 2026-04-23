# Todo Platform - Enterprise Microservices Architecture

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.12-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A production-ready, scalable Todo management platform built with microservices architecture, implementing Domain-Driven Design (DDD) principles and event-driven communication patterns.

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Security](#security)
- [Monitoring & Observability](#monitoring--observability)
- [Deployment](#deployment)
- [Development](#development)

---

## Architecture Overview

The platform follows a microservices architecture with the following components:

```
┌─────────────┐
│   Clients   │
└──────┬──────┘
       │
       ▼
┌─────────────────────────────────────┐
│        API Gateway (9090)           │
│  - JWT Authentication               │
│  - Request Routing                  │
│  - Load Balancing                   │
└──────┬──────────────────────────────┘
       │
       ├──────────────┬──────────────┬──────────────┐
       ▼              ▼              ▼              ▼
┌─────────────┐ ┌─────────────┐ ┌─────────────┐ ┌─────────────┐
│User Service │ │Todo Service │ │Notification │ │   Shared    │
│   (8081)    │ │   (8080)    │ │Service(8082)│ │   Library   │
│             │ │             │ │             │ │             │
│ PostgreSQL  │ │ PostgreSQL  │ │   Kafka     │ │   DTOs      │
│   Redis     │ │   Kafka     │ │   Email     │ │  Utilities  │
└─────────────┘ └─────────────┘ └─────────────┘ └─────────────┘
```

### Service Responsibilities

| Service | Port | Description | Database |
|---------|------|-------------|----------|
| **API Gateway** | 9090 | Entry point, authentication, routing | - |
| **User Service** | 8081 | User management, authentication, JWT generation | PostgreSQL + Redis |
| **Todo Service** | 8080 | Todo CRUD operations, business logic | PostgreSQL |
| **Notification Service** | 8082 | Event-driven notifications via Kafka | - |

---

## Key Features

### Core Functionality
- User Management: Registration, authentication, profile management
- Todo Management: Full CRUD operations with status tracking (PENDING, IN_PROGRESS, DONE)
- Event-Driven Notifications: Asynchronous notifications via Kafka
- JWT Authentication: Secure token-based authentication with HS512 algorithm

### Architecture Patterns
- Domain-Driven Design (DDD): Clear separation of domain, application, infrastructure, and presentation layers
- Event-Driven Architecture: Kafka-based event streaming for inter-service communication
- API Gateway Pattern: Centralized entry point with authentication and routing
- CQRS Ready: Separation of command and query responsibilities

### Technical Features
- Security: BCrypt password hashing, JWT token validation
- Monitoring: Prometheus metrics, Grafana dashboards
- Containerization: Docker and Docker Compose support
- Kubernetes Ready: Complete K8s manifests with HPA
- API Documentation: OpenAPI/Swagger specifications
- Observability: Structured logging with strategic log points

---

## Technology Stack

### Backend
- **Java 17** - LTS version with modern language features
- **Spring Boot 3.2.12** - Application framework
- **Spring Cloud Gateway** - API Gateway implementation
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence layer
- **Hibernate** - ORM framework

### Data & Messaging
- **PostgreSQL 15** - Primary database for User and Todo services
- **Redis 7** - Caching layer for User service
- **Apache Kafka 3.6.2** - Event streaming platform
- **Zookeeper** - Kafka coordination

### Security
- **JJWT 0.11.5** (Gateway) / **0.9.1** (User Service) - JWT implementation
- **BCrypt** - Password hashing algorithm
- **HS512** - JWT signing algorithm

### DevOps & Infrastructure
- **Docker** - Containerization
- **Docker Compose** - Local orchestration
- **Kubernetes** - Production orchestration
- **Prometheus** - Metrics collection
- **Grafana** - Metrics visualization
- **Maven 3.9** - Build tool

---

## Project Structure

```
todo-platform/
├── api-gateway/                 # API Gateway service
│   ├── src/main/java/com/example/gateway/
│   │   ├── config/             # Gateway routing configuration
│   │   ├── security/           # JWT authentication filter
│   │   └── presentation/       # Health check endpoints
│   └── src/main/resources/
│       └── application.yml     # Gateway configuration
│
├── user-service/               # User management service
│   ├── src/main/java/com/example/user/
│   │   ├── application/        # Use cases & DTOs
│   │   ├── domain/             # Domain entities & repositories
│   │   ├── infrastructure/     # External integrations
│   │   └── presentation/       # REST controllers
│   └── src/main/resources/
│       └── application.yml     # Service configuration
│
├── todo-service/               # Todo management service
│   ├── src/main/java/com/example/todo/
│   │   ├── application/        # Use cases & DTOs
│   │   ├── domain/             # Domain entities & repositories
│   │   ├── infrastructure/     # Kafka producers
│   │   └── presentation/       # REST controllers
│   └── src/main/resources/
│       └── application.yml     # Service configuration
│
├── notification-service/       # Notification service
│   ├── src/main/java/com/example/notification/
│   │   ├── application/        # Event handlers
│   │   ├── domain/             # Event models
│   │   ├── infrastructure/     # Kafka consumers, email client
│   │   └── presentation/       # REST controllers
│   └── src/main/resources/
│       └── application.yml     # Service configuration
│
├── shared/                     # Shared library
│   └── src/main/java/com/example/shared/
│       └── dto/                # Common DTOs
│
├── k8s/                        # Kubernetes manifests
│   ├── deployments/            # Service deployments
│   ├── services/               # Service definitions
│   ├── configmaps/             # Configuration
│   ├── secrets/                # Secrets (encrypted)
│   ├── hpa/                    # Horizontal Pod Autoscalers
│   ├── ingress.yaml            # Ingress configuration
│   └── namespace.yaml          # Namespace definition
│
├── charts/                     # Helm charts
│   └── todo-platform-chart/    # Platform Helm chart
│
├── infra/                      # Infrastructure configuration
│   ├── docker/                 # Docker network configs
│   └── monitoring/             # Prometheus & Grafana configs
│
├── deployment-scripts/         # Deployment automation
│   ├── ci/                     # CI/CD scripts
│   └── docker-compose/         # Docker Compose files
│
├── docker-compose.yml          # Local development setup
└── pom.xml                     # Parent Maven POM
```

---

## Getting Started

### Prerequisites

- **Java 17+** (JDK)
- **Maven 3.9+**
- **Docker** & **Docker Compose**
- **Git**

### Quick Start (Docker Compose)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd todo-platform
   ```

2. **Start all services**
   ```bash
   docker-compose up --build -d
   ```

3. **Verify services are running**
   ```bash
   docker-compose ps
   ```

4. **Check service health**
   ```bash
   curl http://localhost:9090/actuator/health
   ```

### Service Endpoints

| Service | URL | Description |
|---------|-----|-------------|
| API Gateway | http://localhost:9090 | Main entry point |
| User Service | http://localhost:8081 | Direct access (dev only) |
| Todo Service | http://localhost:8080 | Direct access (dev only) |
| Notification Service | http://localhost:8082 | Direct access (dev only) |
| Prometheus | http://localhost:9091 | Metrics |
| Grafana | http://localhost:3000 | Dashboards (admin/admin) |

---

## API Documentation

### Authentication Flow

1. **Register a new user**
   ```bash
   curl -X POST http://localhost:9090/api/users \
     -H "Content-Type: application/json" \
     -d '{
       "username": "john",
       "email": "john@example.com",
       "password": "password123"
     }'
   ```

2. **Login to get JWT token**
   ```bash
   curl -X POST http://localhost:9090/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{
       "email": "john@example.com",
       "password": "password123"
     }'
   ```
   
   Response:
   ```json
   {
     "token": "eyJhbGciOiJIUzUxMiJ9..."
   }
   ```

3. **Use token for authenticated requests**
   ```bash
   curl -X GET http://localhost:9090/api/users/profile/1 \
     -H "Authorization: Bearer <your-token>"
   ```

### API Endpoints

#### User Service (via Gateway)

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/users` | No | Create new user |
| POST | `/api/auth/login` | No | Authenticate user |
| GET | `/api/users/profile/{id}` | Yes | Get user profile |

#### Todo Service (via Gateway)

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/todos?userId={id}` | Yes | Create todo |
| GET | `/api/todos?userId={id}&page=0&size=10` | Yes | List todos (paginated) |
| PUT | `/api/todos/{id}` | Yes | Update todo |
| PATCH | `/api/todos/{id}/status?status={status}` | Yes | Change todo status |

**Status Values**: `PENDING`, `IN_PROGRESS`, `DONE`

**Business Rule**: Todos with status `DONE` cannot be updated (returns 400).

#### Notification Service (via Gateway)

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/notifications` | No | Send notification |

### OpenAPI Specifications

- User Service: `user-service/src/main/resources/openapi/user-openapi.yaml`
- Todo Service: `todo-service/src/main/resources/openapi/todo-openapi.yaml`

---

## Security

### JWT Authentication

- **Algorithm**: HS512 (HMAC-SHA512)
- **Token Expiration**: 24 hours (86400000 ms)
- **Secret Key**: Base64-encoded 512-bit key (configured in `application.yml`)

### Password Security

- **Hashing Algorithm**: BCrypt with strength 10
- **Salt**: Automatically generated per password

### Public Endpoints (No Authentication Required)

- `POST /api/users` - User registration
- `POST /api/auth/login` - User authentication
- `POST /api/notifications` - Notification webhook

### Protected Endpoints (JWT Required)

All other endpoints require a valid JWT token in the `Authorization` header:
```
Authorization: Bearer <jwt-token>
```

### Security Headers

The API Gateway adds the following security headers:
- `X-Content-Type-Options: nosniff`
- `X-Frame-Options: DENY`
- `X-XSS-Protection: 0`
- `Cache-Control: no-cache, no-store, max-age=0, must-revalidate`
- `Referrer-Policy: no-referrer`

---

## Monitoring & Observability

### Prometheus Metrics

Access metrics at: `http://localhost:9091/metrics`

Available metrics:
- JVM metrics (memory, threads, GC)
- HTTP request metrics (count, duration)
- Database connection pool metrics
- Custom business metrics

### Grafana Dashboards

Access Grafana at: `http://localhost:3000` (admin/admin)

Pre-configured dashboards:
- Service health overview
- Request rate and latency
- Database performance
- JVM metrics

### Logging

Strategic logging is implemented with prefixes for easy filtering:
- `[GATEWAY]` - API Gateway logs
- `[JWT]` - JWT validation logs
- Service-specific logs with correlation IDs

**Log Levels**:
- `INFO` - Normal operations
- `DEBUG` - Detailed service logs
- `ERROR` - Errors and exceptions

---

## Deployment

### Docker Compose (Development)

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down

# Rebuild and restart
docker-compose up --build -d
```

### Kubernetes (Production)

1. **Create namespace**
   ```bash
   kubectl apply -f k8s/namespace.yaml
   ```

2. **Apply ConfigMaps and Secrets**
   ```bash
   kubectl apply -f k8s/configmaps/
   kubectl apply -f k8s/secrets/
   ```

3. **Deploy services**
   ```bash
   kubectl apply -f k8s/deployments/
   kubectl apply -f k8s/services/
   ```

4. **Configure Horizontal Pod Autoscaling**
   ```bash
   kubectl apply -f k8s/hpa/
   ```

5. **Set up Ingress**
   ```bash
   kubectl apply -f k8s/ingress.yaml
   ```

6. **Verify deployment**
   ```bash
   kubectl get pods -n todo-platform
   kubectl get svc -n todo-platform
   ```

### Helm Chart (Recommended)

```bash
# Install the chart
helm install todo-platform ./charts/todo-platform-chart

# Upgrade the chart
helm upgrade todo-platform ./charts/todo-platform-chart

# Uninstall
helm uninstall todo-platform
```

---

## Development

### Building from Source

```bash
# Build all services
mvn clean install

# Build specific service
cd user-service
mvn clean package

# Skip tests
mvn clean package -DskipTests
```

### Running Services Locally (without Docker)

1. **Start infrastructure services**
   ```bash
   docker-compose up postgres-user postgres-todo redis kafka zookeeper -d
   ```

2. **Run each service**
   ```bash
   # Terminal 1 - User Service
   cd user-service
   mvn spring-boot:run

   # Terminal 2 - Todo Service
   cd todo-service
   mvn spring-boot:run

   # Terminal 3 - Notification Service
   cd notification-service
   mvn spring-boot:run

   # Terminal 4 - API Gateway
   cd api-gateway
   mvn spring-boot:run
   ```

### Code Style & Standards

- **Architecture**: Domain-Driven Design (DDD)
- **Package Structure**: `domain`, `application`, `infrastructure`, `presentation`
- **Naming Conventions**: Java standard conventions
- **Documentation**: JavaDoc for public APIs

### Testing

```bash
# Run all tests
mvn test

# Run tests for specific service
cd user-service
mvn test

# Run integration tests
mvn verify
```

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

