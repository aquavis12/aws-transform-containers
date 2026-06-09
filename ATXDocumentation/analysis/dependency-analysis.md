# Dependency Analysis

## External Dependency Graph

```
spring-boot-starter-parent:2.5.14
    │
    ├── spring-boot-starter-web
    │   ├── spring-webmvc
    │   ├── spring-boot-starter-json (Jackson 2.12.x)
    │   └── spring-boot-starter-tomcat (provided)
    │       └── tomcat-embed-core 9.0.x
    │
    ├── spring-boot-starter-data-jpa
    │   ├── hibernate-core 5.4.x
    │   ├── spring-data-jpa
    │   └── HikariCP (connection pool)
    │
    ├── spring-boot-starter-validation
    │   └── hibernate-validator
    │       └── javax.validation-api
    │
    ├── mysql-connector-java:8.0.33
    │
    ├── lombok (optional, compile-time only)
    │
    └── spring-boot-starter-test (test scope)
        ├── JUnit 5
        ├── Mockito
        └── Spring Test
```

## Internal Dependency Graph

```
SpringCrudApplication
    └── (no direct dependencies, Spring auto-wires)

UserController
    └── UserService

UserService
    ├── UserRepository
    ├── UserDTO
    └── User

UserRepository
    └── User

GlobalExceptionHandler
    └── (no internal dependencies)

UserDTO
    └── (no internal dependencies)

User
    └── (no internal dependencies)
```

## Dependency Criticality

| Dependency | Criticality | Reason |
|-----------|-------------|--------|
| Spring Boot 2.5.14 | High | Core framework, EOL |
| MySQL Connector 8.0.33 | High | Database connectivity |
| Hibernate (via JPA starter) | High | ORM, schema management |
| Lombok | Medium | Build-time convenience |
| Jackson | Medium | JSON serialization |
| HikariCP | Medium | Connection pooling |

## Cross-References

- [Dependencies](../architecture/dependencies.md)
- [Outdated Components](../technical-debt/outdated-components.md)
- [Tech Debt](tech-debt.md)
