# Backend Service Documentation

## Overview
This project serves as the backend for a web application, providing database access and API endpoints. It's built with Spring Boot and includes Swagger UI for API documentation.

## Getting Started

### Prerequisites
- Java JDK (version TBD)
- Gradle

### Installation
Run the following commands at the root of the project:
```bash
./gradlew build
./gradlew run
```

When the application starts successfully, you'll see "Hello Spring" displayed in the terminal.

## Access Points

### Main URLs
- **Homepage**: [http://localhost:8080](http://localhost:8080)
- **Database Console**: [http://localhost:8080/console/](http://localhost:8080/console/)
- **API Documentation**: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

### Authentication

The system provides two default user accounts:

#### Regular User
- Username: `user`
- Password: `password`
- Access to basic features

#### Administrator
- Username: `admin`
- Password: `password`
- Full system access
