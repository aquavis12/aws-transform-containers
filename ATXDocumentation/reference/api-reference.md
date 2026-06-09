# API Reference

## Base URL

- **Local**: `http://localhost:8080/api`
- **JBoss**: `http://localhost:8080/spring-crud-app/api`

## Endpoints

### GET /users
- **Description**: Retrieve all users
- **Controller**: `UserController.getAllUsers()`
- **Response**: `200 OK` — `List<UserDTO>`

### GET /users/{id}
- **Description**: Retrieve user by ID
- **Controller**: `UserController.getUserById(Long id)`
- **Path Parameters**: `id` (Long) — User ID
- **Response**: `200 OK` — `UserDTO`
- **Errors**: `400` — "User not found with id: {id}"

### POST /users
- **Description**: Create a new user
- **Controller**: `UserController.createUser(UserDTO userDTO)`
- **Request Body**: `UserDTO` (validated)
- **Response**: `201 CREATED` — `UserDTO`
- **Errors**:
  - `400` — Validation failed (field errors)
  - `400` — "Username already exists"
  - `400` — "Email already exists"

### PUT /users/{id}
- **Description**: Update an existing user
- **Controller**: `UserController.updateUser(Long id, UserDTO userDTO)`
- **Path Parameters**: `id` (Long) — User ID
- **Request Body**: `UserDTO` (validated)
- **Response**: `200 OK` — `UserDTO`
- **Errors**:
  - `400` — Validation failed
  - `400` — "User not found with id: {id}"

### DELETE /users/{id}
- **Description**: Delete a user
- **Controller**: `UserController.deleteUser(Long id)`
- **Path Parameters**: `id` (Long) — User ID
- **Response**: `200 OK` — `"User deleted successfully"`
- **Errors**: `400` — "User not found with id: {id}"

### GET /users/search/username/{username}
- **Description**: Find user by username
- **Controller**: `UserController.getUserByUsername(String username)`
- **Path Parameters**: `username` (String)
- **Response**: `200 OK` — `UserDTO`
- **Errors**: `400` — "User not found with username: {username}"

### GET /users/search/email/{email}
- **Description**: Find user by email
- **Controller**: `UserController.getUserByEmail(String email)`
- **Path Parameters**: `email` (String)
- **Response**: `200 OK` — `UserDTO`
- **Errors**: `400` — "User not found with email: {email}"

### GET /users/search?q={searchTerm}
- **Description**: Search users by first or last name (case-insensitive partial match)
- **Controller**: `UserController.searchUsers(String q)`
- **Query Parameters**: `q` (String) — Search term
- **Response**: `200 OK` — `List<UserDTO>`

## Request/Response Examples

### Create User Request
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "address": "123 Main St",
  "phoneNumber": "1234567890"
}
```

### User Response
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "address": "123 Main St",
  "phoneNumber": "1234567890",
  "createdAt": "2024-01-15 10:30:45",
  "updatedAt": "2024-01-15 10:30:45"
}
```

### Validation Error Response
```json
{
  "timestamp": "2024-01-15T10:30:45",
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email should be valid",
    "username": "Username cannot be blank"
  }
}
```

## Cross-References

- [Interfaces](interfaces.md)
- [Workflows](../behavior/workflows.md)
- [API Documentation (Specialized)](../specialized/api-documentation.md)
