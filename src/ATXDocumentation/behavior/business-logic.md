> ⚠️ **Early Access**: Behavior documentation is in early access. Please review critically.

# Business Logic

## UserService Business Rules

### User Creation (`createUser`)
- **Location**: `UserService.java:44-62`
- **Rules**:
  1. Username must be unique — if `findByUsername` returns a result, throw "Username already exists"
  2. Email must be unique — if `findByEmail` returns a result, throw "Email already exists"
  3. On successful save, `@PrePersist` sets `createdAt` and `updatedAt` to current time
  4. Returns the saved entity as DTO (includes generated ID and timestamps)

### User Update (`updateUser`)
- **Location**: `UserService.java:67-86`
- **Rules**:
  1. User must exist by ID — if not found, throw "User not found with id: {id}"
  2. All mutable fields are overwritten: username, email, firstName, lastName, address, phoneNumber
  3. `@PreUpdate` automatically sets `updatedAt` to current time
  4. No uniqueness re-validation on update (potential bug: can create duplicate username/email)

### User Deletion (`deleteUser`)
- **Location**: `UserService.java:91-100`
- **Rules**:
  1. User must exist by ID — checked via `existsById`
  2. Hard delete (no soft-delete mechanism)

### User Retrieval
- **getUserById**: Must exist or throws RuntimeException
- **getUserByUsername**: Must exist or throws RuntimeException
- **getUserByEmail**: Must exist or throws RuntimeException
- **getAllUsers**: Returns all users (no pagination)
- **searchUsersByName**: Case-insensitive LIKE search on firstName and lastName

## Input Validation (Bean Validation)

### UserDTO Constraints
| Field | Constraint | Message |
|-------|-----------|---------|
| username | `@NotBlank` | "Username cannot be blank" |
| email | `@NotBlank`, `@Email` | "Email cannot be blank", "Email should be valid" |
| firstName | `@NotBlank` | "First name cannot be blank" |
| lastName | `@NotBlank` | "Last name cannot be blank" |

### User Entity Constraints
| Field | DB Constraint | Validation |
|-------|--------------|-----------|
| username | UNIQUE, NOT NULL | `@NotBlank` |
| email | UNIQUE, NOT NULL | `@NotBlank`, `@Email` |
| firstName | NOT NULL | `@NotBlank` |
| lastName | NOT NULL | `@NotBlank` |
| address | VARCHAR(500) | None |
| phoneNumber | VARCHAR(20) | None |

## Cross-References

- [Workflows](workflows.md)
- [Decision Logic](decision-logic.md)
- [UserService Component](../architecture/components.md)
