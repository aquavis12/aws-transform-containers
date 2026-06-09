# Test Specifications

## Test Cases for Migration Validation

### TC-1: Application Startup
- **Objective**: Verify application boots successfully after migration
- **Steps**: Start the Spring Boot application
- **Expected**: Application starts without errors, listening on configured port

### TC-2: Create User (Happy Path)
- **Objective**: Verify user creation workflow
- **Steps**: POST /api/users with valid JSON body
- **Expected**: 201 Created, response contains generated ID and timestamps

### TC-3: Create User (Duplicate Username)
- **Objective**: Verify uniqueness constraint on username
- **Steps**: POST /api/users with an existing username
- **Expected**: 400 Bad Request, message "Username already exists"

### TC-4: Create User (Duplicate Email)
- **Objective**: Verify uniqueness constraint on email
- **Steps**: POST /api/users with an existing email
- **Expected**: 400 Bad Request, message "Email already exists"

### TC-5: Create User (Validation Failure)
- **Objective**: Verify input validation
- **Steps**: POST /api/users with blank username/email
- **Expected**: 400 Bad Request, field-level error messages

### TC-6: Get All Users
- **Objective**: Verify list retrieval
- **Steps**: GET /api/users
- **Expected**: 200 OK, JSON array of UserDTO objects

### TC-7: Get User by ID (Found)
- **Objective**: Verify single user retrieval
- **Steps**: GET /api/users/{existing-id}
- **Expected**: 200 OK, UserDTO with matching ID

### TC-8: Get User by ID (Not Found)
- **Objective**: Verify not-found handling
- **Steps**: GET /api/users/99999
- **Expected**: 400 Bad Request, message "User not found with id: 99999"

### TC-9: Update User
- **Objective**: Verify user update
- **Steps**: PUT /api/users/{id} with modified fields
- **Expected**: 200 OK, updated UserDTO with new updatedAt timestamp

### TC-10: Delete User
- **Objective**: Verify user deletion
- **Steps**: DELETE /api/users/{existing-id}
- **Expected**: 200 OK, message "User deleted successfully"

### TC-11: Search by Username
- **Objective**: Verify username search
- **Steps**: GET /api/users/search/username/{username}
- **Expected**: 200 OK, matching UserDTO

### TC-12: Search by Email
- **Objective**: Verify email search
- **Steps**: GET /api/users/search/email/{email}
- **Expected**: 200 OK, matching UserDTO

### TC-13: Search by Name
- **Objective**: Verify name search (case-insensitive partial match)
- **Steps**: GET /api/users/search?q=john
- **Expected**: 200 OK, list of matching UserDTOs

## Cross-References

- [Validation Criteria](validation-criteria.md)
- [API Reference](../reference/api-reference.md)
