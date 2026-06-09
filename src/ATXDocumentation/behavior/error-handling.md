> ⚠️ **Early Access**: Behavior documentation is in early access. Please review critically.

# Error Handling

## Exception Handling Strategy

The application uses `@RestControllerAdvice` (`GlobalExceptionHandler`) to centralize all exception handling. All exceptions are caught and transformed into standardized JSON error responses.

## Exception Handlers

### 1. Validation Exceptions
- **Handler**: `handleValidationException(MethodArgumentNotValidException)`
- **Trigger**: `@Valid` annotation failures on `@RequestBody`
- **HTTP Status**: 400 Bad Request
- **Response Format**:
  ```json
  {
    "timestamp": "2024-01-15T10:30:45",
    "status": 400,
    "message": "Validation failed",
    "errors": {
      "fieldName": "error message"
    }
  }
  ```

### 2. Runtime Exceptions (Business Errors)
- **Handler**: `handleRuntimeException(RuntimeException)`
- **Trigger**: Business rule violations thrown in UserService
- **HTTP Status**: 400 Bad Request
- **Response Format**:
  ```json
  {
    "timestamp": "2024-01-15T10:30:45",
    "status": 400,
    "message": "User not found with id: 999"
  }
  ```
- **Known Messages**:
  - "User not found with id: {id}"
  - "User not found with username: {username}"
  - "User not found with email: {email}"
  - "Username already exists"
  - "Email already exists"

### 3. Generic Exceptions (Catch-all)
- **Handler**: `handleGenericException(Exception)`
- **Trigger**: Any unhandled exception
- **HTTP Status**: 500 Internal Server Error
- **Response Format**:
  ```json
  {
    "timestamp": "2024-01-15T10:30:45",
    "status": 500,
    "message": "An unexpected error occurred"
  }
  ```

## Error Recovery Patterns

- **No retry logic**: All operations fail immediately on error
- **No circuit breaker**: Direct database access without resilience patterns
- **Transaction rollback**: `@Transactional` on UserService ensures atomicity — failed operations roll back

## Issues

- "User not found" errors return 400 instead of the semantically correct 404
- All business exceptions use generic `RuntimeException` — no custom exception hierarchy
- Stack traces logged for generic exceptions but not returned to client (good practice)

## Cross-References

- [Decision Logic](decision-logic.md)
- [Security Patterns](../analysis/security-patterns.md)
