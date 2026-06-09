# Complexity Analysis

## Overall Assessment

This is a low-complexity application. It follows straightforward CRUD patterns with minimal branching logic.

## Method Complexity

### UserService (Highest Relative Complexity)

| Method | Cyclomatic Complexity | Notes |
|--------|----------------------|-------|
| createUser | 3 | Two conditional checks (username/email uniqueness) |
| updateUser | 2 | One conditional (existence check) |
| deleteUser | 2 | One conditional (existence check) |
| getUserById | 2 | One conditional (existence check) |
| getUserByUsername | 2 | One conditional (existence check) |
| getUserByEmail | 2 | One conditional (existence check) |
| getAllUsers | 1 | Linear stream operation |
| searchUsersByName | 1 | Linear stream operation |
| convertToDTO | 1 | Direct field mapping |
| convertToEntity | 1 | Direct field mapping |

### GlobalExceptionHandler

| Method | Cyclomatic Complexity | Notes |
|--------|----------------------|-------|
| handleValidationException | 2 | Loop over errors |
| handleRuntimeException | 1 | Direct mapping |
| handleGenericException | 1 | Direct mapping |

### UserController

All methods have cyclomatic complexity of 1 (pure delegation to service layer).

## Complexity Hotspots

1. **UserService.createUser** — Most complex method (CC=3), but still straightforward
2. **GlobalExceptionHandler.handleValidationException** — Iterates over field errors

## Coupling Analysis

- **Afferent coupling (Ca)**: UserService is depended on by UserController (Ca=1)
- **Efferent coupling (Ce)**: UserService depends on UserRepository, UserDTO, User (Ce=3)
- **Instability**: UserService I = Ce/(Ca+Ce) = 3/4 = 0.75 (high instability, appropriate for concrete implementation)

## Cross-References

- [Code Metrics](code-metrics.md)
- [Patterns](../architecture/patterns.md)
