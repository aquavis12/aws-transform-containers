# API Documentation

## Service Overview

RESTful CRUD API for user management. All endpoints are under the `/users` path with a configurable context path (`/api` by default).

## Content Type

- **Request**: `application/json`
- **Response**: `application/json`

## Authentication

None. All endpoints are publicly accessible.

## Endpoints Summary

| Method | Path | Description | Status Codes |
|--------|------|-------------|-------------|
| GET | /users | List all users | 200 |
| GET | /users/{id} | Get user by ID | 200, 400 |
| POST | /users | Create user | 201, 400 |
| PUT | /users/{id} | Update user | 200, 400 |
| DELETE | /users/{id} | Delete user | 200, 400 |
| GET | /users/search/username/{username} | Find by username | 200, 400 |
| GET | /users/search/email/{email} | Find by email | 200, 400 |
| GET | /users/search?q={term} | Search by name | 200 |

## Data Contract

### UserDTO (Request/Response)
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

### Required Fields (for POST/PUT)
- `username` — non-blank string
- `email` — non-blank, valid email format
- `firstName` — non-blank string
- `lastName` — non-blank string

### Optional Fields
- `address` — string or null
- `phoneNumber` — string or null

### Read-Only Fields (ignored on input)
- `id` — auto-generated
- `createdAt` — auto-set on creation
- `updatedAt` — auto-set on creation and update

## Error Response Format

```json
{
  "timestamp": "2024-01-15T10:30:45",
  "status": 400,
  "message": "Error description",
  "errors": {
    "fieldName": "field-specific error"
  }
}
```

The `errors` field only appears for validation failures.

## Cross-References

- [API Reference](../reference/api-reference.md)
- [Workflows](../behavior/workflows.md)
