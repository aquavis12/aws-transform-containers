> ⚠️ **Early Access**: Behavior documentation is in early access. Please review critically.

# Workflows

## Workflow 1: Create User

**Entry Point**: `POST /api/users` → `UserController.createUser()`

```
Client sends POST /api/users with JSON body
    │
    ▼
Spring validates @RequestBody against @Valid UserDTO
    │
    ├── Validation fails → MethodArgumentNotValidException → 400 + field errors
    │
    ▼ (validation passes)
UserController.createUser() invoked
    │
    ▼
UserService.createUser()
    │
    ├── Check username uniqueness (findByUsername)
    │   └── Exists → RuntimeException "Username already exists" → 400
    │
    ├── Check email uniqueness (findByEmail)
    │   └── Exists → RuntimeException "Email already exists" → 400
    │
    ▼ (both unique)
Convert UserDTO → User entity
    │
    ▼
UserRepository.save(user) — JPA persists to MySQL
    │ (@PrePersist sets createdAt, updatedAt)
    │
    ▼
Convert saved User → UserDTO
    │
    ▼
Return 201 CREATED with UserDTO body
```

## Workflow 2: Update User

**Entry Point**: `PUT /api/users/{id}` → `UserController.updateUser()`

```
Client sends PUT /api/users/{id} with JSON body
    │
    ▼
Spring validates @RequestBody against @Valid UserDTO
    │
    ├── Validation fails → 400 + field errors
    │
    ▼
UserService.updateUser(id, userDTO)
    │
    ├── findById(id) — user must exist
    │   └── Not found → RuntimeException → 400
    │
    ▼
Update entity fields from DTO
    │
    ▼
UserRepository.save(user) — JPA merges
    │ (@PreUpdate sets updatedAt)
    │
    ▼
Return 200 OK with updated UserDTO
```

## Workflow 3: Delete User

**Entry Point**: `DELETE /api/users/{id}` → `UserController.deleteUser()`

```
Client sends DELETE /api/users/{id}
    │
    ▼
UserService.deleteUser(id)
    │
    ├── existsById(id) check
    │   └── Not found → RuntimeException → 400
    │
    ▼
UserRepository.deleteById(id)
    │
    ▼
Return 200 OK "User deleted successfully"
```

## Workflow 4: Search Users

**Entry Point**: `GET /api/users/search?q={term}` → `UserController.searchUsers()`

```
Client sends GET /api/users/search?q=john
    │
    ▼
UserService.searchUsersByName(searchTerm)
    │
    ▼
UserRepository.searchByName() — JPQL LIKE query
    │ (case-insensitive on firstName and lastName)
    │
    ▼
Convert List<User> → List<UserDTO>
    │
    ▼
Return 200 OK with List<UserDTO>
```

## Cross-References

- [Business Logic](business-logic.md)
- [API Reference](../reference/api-reference.md)
- [Decision Logic](decision-logic.md)
