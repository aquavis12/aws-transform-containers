# Architectural Patterns

## Patterns Used

### 1. Layered Architecture
- **Controller Layer**: HTTP handling, request/response mapping
- **Service Layer**: Business logic, transaction management
- **Repository Layer**: Data access abstraction
- **Model Layer**: Domain entities

### 2. Repository Pattern (Spring Data JPA)
- `UserRepository` extends `JpaRepository<User, Long>`
- Provides CRUD operations without implementation code
- Custom query methods via method naming conventions and `@Query`

### 3. DTO Pattern (Data Transfer Object)
- `UserDTO` decouples API contract from persistence model
- Manual conversion in `UserService.convertToDTO()` and `convertToEntity()`
- Allows independent evolution of API and database schema

### 4. Global Exception Handler
- `@RestControllerAdvice` provides centralized error handling
- Maps exceptions to standardized error response format
- Separates error handling from business logic

### 5. Dependency Injection
- Field injection via `@Autowired` (UserController → UserService, UserService → UserRepository)
- Spring IoC container manages lifecycle

### 6. JPA Entity Lifecycle Callbacks
- `@PrePersist` and `@PreUpdate` for automatic timestamp management
- Encapsulates audit trail logic in the entity itself

## Anti-Patterns Identified

### 1. Field Injection
- Uses `@Autowired` on fields instead of constructor injection
- Makes testing harder, hides dependencies, prevents immutability

### 2. Generic RuntimeException for Business Errors
- Uses `throw new RuntimeException(message)` for business cases (user not found, duplicate username)
- Should use custom exception types with appropriate HTTP status codes

### 3. No Separation of Concerns in Validation
- Uniqueness validation performed in service layer via repository queries
- Could be extracted to a dedicated validator component

### 4. Manual DTO Mapping
- Manual conversion code in service layer
- Could use MapStruct or ModelMapper for maintainability at scale

## Cross-References

- [Components](components.md)
- [Complexity Analysis](../analysis/complexity-analysis.md)
- [Business Logic](../behavior/business-logic.md)
