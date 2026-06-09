# Components

## Component Inventory

### 1. SpringCrudApplication
- **File**: `src/main/java/com/example/SpringCrudApplication.java`
- **Type**: Application entry point
- **Responsibility**: Bootstrap Spring Boot application
- **Annotations**: `@SpringBootApplication`

### 2. UserController
- **File**: `src/main/java/com/example/controller/UserController.java`
- **Type**: REST Controller
- **Responsibility**: HTTP request handling, input validation delegation, response formatting
- **Annotations**: `@RestController`, `@RequestMapping("/users")`, `@Slf4j`
- **Dependencies**: UserService
- **Endpoints**: 8 REST endpoints (GET, POST, PUT, DELETE)

### 3. UserService
- **File**: `src/main/java/com/example/service/UserService.java`
- **Type**: Service / Business Logic
- **Responsibility**: Business rule enforcement, DTO-Entity conversion, orchestration
- **Annotations**: `@Service`, `@Transactional`, `@Slf4j`
- **Dependencies**: UserRepository
- **Methods**: 8 public + 2 private (conversion utilities)

### 4. UserRepository
- **File**: `src/main/java/com/example/repository/UserRepository.java`
- **Type**: Data Access Interface
- **Responsibility**: Database CRUD operations, custom queries
- **Annotations**: `@Repository`
- **Extends**: `JpaRepository<User, Long>`
- **Custom Queries**: 1 JPQL query (`searchByName`)

### 5. User (Entity)
- **File**: `src/main/java/com/example/model/User.java`
- **Type**: JPA Entity
- **Responsibility**: Database table mapping, lifecycle hooks
- **Annotations**: `@Entity`, `@Table(name="users")`, `@Data`
- **Fields**: 8 (id, username, email, firstName, lastName, address, phoneNumber, createdAt, updatedAt)

### 6. UserDTO
- **File**: `src/main/java/com/example/dto/UserDTO.java`
- **Type**: Data Transfer Object
- **Responsibility**: API contract definition, input validation constraints
- **Annotations**: `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`

### 7. GlobalExceptionHandler
- **File**: `src/main/java/com/example/exception/GlobalExceptionHandler.java`
- **Type**: Cross-cutting concern
- **Responsibility**: Centralized exception handling, error response formatting
- **Annotations**: `@RestControllerAdvice`, `@Slf4j`
- **Handles**: `MethodArgumentNotValidException`, `RuntimeException`, `Exception`

## Component Interaction Summary

```
Client → UserController → UserService → UserRepository → MySQL
                ↕                              ↕
        GlobalExceptionHandler          User (Entity)
                                        UserDTO (conversion)
```

## Cross-References

- [System Overview](system-overview.md)
- [Program Structure](../reference/program-structure.md)
- [Interfaces](../reference/interfaces.md)
