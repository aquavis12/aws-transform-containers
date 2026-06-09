# Program Structure

## Package Hierarchy

```
com.example
├── SpringCrudApplication.java          [Application Entry Point]
├── controller/
│   └── UserController.java             [REST Controller]
├── dto/
│   └── UserDTO.java                    [Data Transfer Object]
├── exception/
│   └── GlobalExceptionHandler.java     [Cross-Cutting: Error Handling]
├── model/
│   └── User.java                       [JPA Entity]
├── repository/
│   └── UserRepository.java            [Data Access Interface]
└── service/
    └── UserService.java                [Business Logic]
```

## Resource Files

```
src/main/resources/
└── application.properties              [Spring Boot Configuration]

src/main/webapp/WEB-INF/
└── jboss-web.xml                       [JBoss Deployment Descriptor]
```

## Project Root Files

```
/
├── pom.xml                             [Maven Build Configuration]
├── README.md                           [Project Documentation]
├── DEPLOYMENT.md                       [JBoss Deployment Guide]
└── sql/
    └── init-database.sql               [Database Schema + Seed Data]
```

## Class Details

### SpringCrudApplication
- **Modifiers**: public class
- **Annotations**: `@SpringBootApplication`
- **Methods**: `main(String[] args)` — application bootstrap

### UserController
- **Modifiers**: public class
- **Annotations**: `@RestController`, `@RequestMapping("/users")`, `@Slf4j`
- **Fields**: `UserService userService` (@Autowired)
- **Methods**: 8 public endpoint handlers

### UserService
- **Modifiers**: public class
- **Annotations**: `@Service`, `@Transactional`, `@Slf4j`
- **Fields**: `UserRepository userRepository` (@Autowired)
- **Methods**: 8 public + 2 private

### UserRepository
- **Modifiers**: public interface
- **Annotations**: `@Repository`
- **Extends**: `JpaRepository<User, Long>`
- **Methods**: 6 custom query methods

### User
- **Modifiers**: public class
- **Annotations**: `@Entity`, `@Table(name="users")`, `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`
- **Fields**: 8 persisted fields + lifecycle callbacks

### UserDTO
- **Modifiers**: public class
- **Annotations**: `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`
- **Fields**: 8 fields with validation annotations

### GlobalExceptionHandler
- **Modifiers**: public class
- **Annotations**: `@RestControllerAdvice`, `@Slf4j`
- **Methods**: 3 exception handlers

## Cross-References

- [Components](../architecture/components.md)
- [Modules](modules.md)
- [Interfaces](interfaces.md)
