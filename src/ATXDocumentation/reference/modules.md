# Modules

## Module Organization

This is a single-module Maven project with the following logical packages:

### Package: `com.example`
- **Purpose**: Root package, contains application entry point
- **Classes**: `SpringCrudApplication`

### Package: `com.example.controller`
- **Purpose**: REST API layer — receives HTTP requests, delegates to service
- **Classes**: `UserController`
- **Dependencies**: `com.example.dto`, `com.example.service`

### Package: `com.example.dto`
- **Purpose**: Data transfer objects for API contract
- **Classes**: `UserDTO`
- **Dependencies**: External only (javax.validation, Jackson, Lombok)

### Package: `com.example.exception`
- **Purpose**: Cross-cutting exception handling
- **Classes**: `GlobalExceptionHandler`
- **Dependencies**: Spring MVC (validation, web.bind)

### Package: `com.example.model`
- **Purpose**: JPA entity definitions (persistence layer)
- **Classes**: `User`
- **Dependencies**: External only (javax.persistence, javax.validation, Lombok)

### Package: `com.example.repository`
- **Purpose**: Data access layer
- **Interfaces**: `UserRepository`
- **Dependencies**: `com.example.model`, Spring Data JPA

### Package: `com.example.service`
- **Purpose**: Business logic and orchestration
- **Classes**: `UserService`
- **Dependencies**: `com.example.dto`, `com.example.model`, `com.example.repository`

## Dependency Flow (Packages)

```
controller → service → repository → model
    ↓                                  ↑
   dto ←────────────────── service ────┘
                              ↓
exception (independent, cross-cutting)
```

## Build Module

Single Maven module defined in `pom.xml`:
- **Group**: `com.example`
- **Artifact**: `spring-crud-app`
- **Packaging**: `war`

## Cross-References

- [Program Structure](program-structure.md)
- [Dependencies](../architecture/dependencies.md)
- [Dependency Analysis](../analysis/dependency-analysis.md)
