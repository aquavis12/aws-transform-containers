# Data Models

## User Entity

**File**: `src/main/java/com/example/model/User.java`  
**Table**: `users`

| Field | Java Type | Column | Constraints | Notes |
|-------|-----------|--------|-------------|-------|
| id | Long | id | PK, AUTO_INCREMENT | `@GeneratedValue(IDENTITY)` |
| username | String | username | UNIQUE, NOT NULL | `@NotBlank` |
| email | String | email | UNIQUE, NOT NULL | `@NotBlank`, `@Email` |
| firstName | String | first_name | NOT NULL | `@NotBlank` |
| lastName | String | last_name | NOT NULL | `@NotBlank` |
| address | String | address | VARCHAR(500) | Optional |
| phoneNumber | String | phone_number | VARCHAR(20) | Optional |
| createdAt | LocalDateTime | created_at | NOT NULL, non-updatable | Set by `@PrePersist` |
| updatedAt | LocalDateTime | updated_at | — | Set by `@PrePersist`, `@PreUpdate` |

## UserDTO

**File**: `src/main/java/com/example/dto/UserDTO.java`

| Field | Java Type | Validation | JSON Format |
|-------|-----------|-----------|-------------|
| id | Long | — | numeric |
| username | String | `@NotBlank` | string |
| email | String | `@NotBlank`, `@Email` | string |
| firstName | String | `@NotBlank` | string |
| lastName | String | `@NotBlank` | string |
| address | String | — | string (nullable) |
| phoneNumber | String | — | string (nullable) |
| createdAt | LocalDateTime | — | "yyyy-MM-dd HH:mm:ss" |
| updatedAt | LocalDateTime | — | "yyyy-MM-dd HH:mm:ss" |

## Database Schema (SQL)

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    address VARCHAR(500),
    phone_number VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

## Entity-DTO Mapping

```
User.id          ↔ UserDTO.id
User.username    ↔ UserDTO.username
User.email       ↔ UserDTO.email
User.firstName   ↔ UserDTO.firstName
User.lastName    ↔ UserDTO.lastName
User.address     ↔ UserDTO.address
User.phoneNumber ↔ UserDTO.phoneNumber
User.createdAt   ↔ UserDTO.createdAt
User.updatedAt   ↔ UserDTO.updatedAt
```

All fields map 1:1 between entity and DTO. Conversion is performed manually in `UserService.convertToDTO()` and `UserService.convertToEntity()`.

## Cross-References

- [Interfaces](interfaces.md)
- [Database Patterns](../specialized/database-patterns.md)
