> ⚠️ **Early Access**: Behavior documentation is in early access. Please review critically.

# Decision Logic

## UserService Decision Points

### D1: Create User — Username Uniqueness Check
- **Location**: `UserService.java:49-51`
- **Condition**: `userRepository.findByUsername(userDTO.getUsername()).isPresent()`
- **True Branch**: Throw RuntimeException "Username already exists"
- **False Branch**: Proceed to email check

### D2: Create User — Email Uniqueness Check
- **Location**: `UserService.java:54-56`
- **Condition**: `userRepository.findByEmail(userDTO.getEmail()).isPresent()`
- **True Branch**: Throw RuntimeException "Email already exists"
- **False Branch**: Proceed to save

### D3: Update User — Existence Check
- **Location**: `UserService.java:71-72`
- **Condition**: `userRepository.findById(id)` returns empty Optional
- **True Branch**: Throw RuntimeException "User not found with id: {id}"
- **False Branch**: Proceed to update fields

### D4: Delete User — Existence Check
- **Location**: `UserService.java:93-95`
- **Condition**: `!userRepository.existsById(id)`
- **True Branch**: Throw RuntimeException "User not found with id: {id}"
- **False Branch**: Proceed to delete

### D5: Get User by ID — Existence Check
- **Location**: `UserService.java:35-37`
- **Condition**: `userRepository.findById(id)` returns empty Optional
- **True Branch**: Throw RuntimeException "User not found with id: {id}"
- **False Branch**: Return user DTO

### D6: Get User by Username — Existence Check
- **Location**: `UserService.java:105-107`
- **Condition**: `userRepository.findByUsername(username)` returns empty Optional
- **True Branch**: Throw RuntimeException "User not found with username: {username}"
- **False Branch**: Return user DTO

### D7: Get User by Email — Existence Check
- **Location**: `UserService.java:114-116`
- **Condition**: `userRepository.findByEmail(email)` returns empty Optional
- **True Branch**: Throw RuntimeException "User not found with email: {email}"
- **False Branch**: Return user DTO

## GlobalExceptionHandler Decision Points

### D8: Exception Type Routing
- **Location**: `GlobalExceptionHandler.java`
- **Decision Tree**:
  ```
  Exception thrown
      │
      ├── MethodArgumentNotValidException? → 400 + field-level errors map
      │
      ├── RuntimeException? → 400 + exception message
      │
      └── Other Exception? → 500 + "An unexpected error occurred"
  ```

## Cross-References

- [Business Logic](business-logic.md)
- [Error Handling](error-handling.md)
