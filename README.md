# Spring Boot CRUD Application with MySQL and JBoss

A complete Java Spring Boot application with CRUD operations for managing users, designed to run on JBoss with MySQL database.

## Features

- **CRUD Operations**: Create, Read, Update, Delete user records
- **RESTful API**: Full REST endpoints with validation
- **MySQL Integration**: Persistent data storage with MySQL 8.0
- **JBoss Compatible**: War packaging for JBoss/WildFly deployment
- **Spring Boot 2.5.14**: Stable version with LTS support
- **Input Validation**: Bean validation with meaningful error messages
- **Global Exception Handling**: Centralized error handling
- **Logging**: SLF4J with Logback
- **Data Transfer Objects**: DTO pattern for clean API contracts

## Technology Stack

- **Java 8**
- **Spring Boot 2.5.14**
- **Spring Data JPA**
- **MySQL 8.0.33**
- **Lombok** - Reduces boilerplate code
- **Maven** - Build tool
- **JBoss/WildFly** - Application server

## Project Structure

```
src/main/java/com/example/
├── SpringCrudApplication.java      # Main Spring Boot application class
├── controller/
│   └── UserController.java         # REST endpoints
├── service/
│   └── UserService.java            # Business logic
├── repository/
│   └── UserRepository.java         # Data access layer
├── model/
│   └── User.java                   # Entity class
├── dto/
│   └── UserDTO.java                # Data Transfer Object
└── exception/
    └── GlobalExceptionHandler.java # Centralized exception handling

src/main/resources/
├── application.properties           # Application configuration

src/main/webapp/WEB-INF/
└── jboss-web.xml                   # JBoss deployment descriptor
```

## Prerequisites

- Java 8 or higher
- Maven 3.6+
- MySQL 5.7 or 8.0
- JBoss/WildFly 12+ or embedded Tomcat for local testing

## Setup Instructions

### 1. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE crud_db;
USE crud_db;
```

The application will automatically create the `users` table using Hibernate DDL auto.

### 2. Update Database Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crud_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
```

### 3. Build the Application

```bash
mvn clean install
```

### 4. Run Locally (for testing)

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

### 5. Deploy to JBoss/WildFly

Generate WAR file:
```bash
mvn clean package
```

The WAR file will be generated at `target/spring-crud-app-1.0.0.war`

Deploy to JBoss:
1. Copy the WAR file to `$JBOSS_HOME/standalone/deployments/`
2. Access the application at `http://localhost:8080/spring-crud-app`

## API Endpoints

### 1. Get All Users
```
GET /api/users
curl -X GET http://localhost:8080/api/users
```

### 2. Get User by ID
```
GET /api/users/{id}
curl -X GET http://localhost:8080/api/users/1
```

### 3. Create User
```
POST /api/users
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username":"john_doe",
    "email":"john@example.com",
    "firstName":"John",
    "lastName":"Doe",
    "address":"123 Main St",
    "phoneNumber":"1234567890"
  }'
```

### 4. Update User
```
PUT /api/users/{id}
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "username":"john_updated",
    "email":"john_updated@example.com",
    "firstName":"John",
    "lastName":"Doe",
    "address":"456 Oak St",
    "phoneNumber":"0987654321"
  }'
```

### 5. Delete User
```
DELETE /api/users/{id}
curl -X DELETE http://localhost:8080/api/users/1
```

### 6. Get User by Username
```
GET /api/users/search/username/{username}
curl -X GET http://localhost:8080/api/users/search/username/john_doe
```

### 7. Get User by Email
```
GET /api/users/search/email/{email}
curl -X GET http://localhost:8080/api/users/search/email/john@example.com
```

### 8. Search Users by Name
```
GET /api/users/search?q={searchTerm}
curl -X GET "http://localhost:8080/api/users/search?q=john"
```

## User Entity

The `User` entity contains the following fields:

- `id` - Long (Primary Key, Auto-generated)
- `username` - String (Unique, Required)
- `email` - String (Unique, Required, Valid Email)
- `firstName` - String (Required)
- `lastName` - String (Required)
- `address` - String (Optional)
- `phoneNumber` - String (Optional)
- `createdAt` - LocalDateTime (Auto-set)
- `updatedAt` - LocalDateTime (Auto-set)

## Error Handling

The application includes global exception handling:

### Validation Errors (400 Bad Request)
```json
{
  "timestamp": "2024-01-15 10:30:45",
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email should be valid",
    "username": "Username cannot be blank"
  }
}
```

### Resource Not Found (500 Internal Server Error)
```json
{
  "timestamp": "2024-01-15 10:30:45",
  "status": 500,
  "message": "User not found with id: 999"
}
```

## Configuration

### Hibernate/JPA Settings

- `spring.jpa.hibernate.ddl-auto=update` - Automatically updates schema (use `validate` in production)
- `spring.jpa.show-sql=false` - Set to `true` for debugging SQL queries
- Batch processing enabled for better performance

### Logging

Configure logging level in `application.properties`:

```properties
logging.level.com.example=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.hibernate.SQL=DEBUG
```

## Performance Optimization

- **Batch Operations**: Hibernate batch size set to 20
- **Lazy Loading**: Default JPA lazy loading for associations
- **Connection Pooling**: HikariCP (default with Spring Boot)

## Production Deployment Checklist

- [ ] Set `spring.jpa.hibernate.ddl-auto=validate` in production
- [ ] Enable SSL/TLS for HTTPS
- [ ] Configure secure database connection
- [ ] Set appropriate logging levels
- [ ] Configure connection pool settings
- [ ] Set up database backups
- [ ] Configure JBoss datasource for connection pooling
- [ ] Enable CORS if needed for frontend applications
- [ ] Implement authentication/authorization
- [ ] Set up monitoring and alerting

## Troubleshooting

### Database Connection Issues
- Verify MySQL is running
- Check database credentials in `application.properties`
- Ensure database exists: `CREATE DATABASE crud_db;`

### Port Already in Use
```bash
# Find process using port 8080
lsof -i :8080
# Kill the process
kill -9 <PID>
```

### JBoss Deployment Issues
- Check JBoss logs: `$JBOSS_HOME/standalone/log/server.log`
- Verify WAR file is correctly formatted
- Ensure JBoss datasource is configured if not using embedded database

## License

This project is provided as-is for development and educational purposes.

## Author

Spring CRUD Application - Development Team
