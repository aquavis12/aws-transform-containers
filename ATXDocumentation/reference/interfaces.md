# Interfaces

## UserRepository Interface

**File**: `src/main/java/com/example/repository/UserRepository.java`  
**Extends**: `JpaRepository<User, Long>`

### Inherited Methods (from JpaRepository)
- `List<User> findAll()`
- `Optional<User> findById(Long id)`
- `User save(User entity)`
- `void deleteById(Long id)`
- `boolean existsById(Long id)`
- `long count()`

### Custom Query Methods

| Method | Return Type | Parameters | Query Strategy |
|--------|------------|------------|----------------|
| `findByUsername` | `Optional<User>` | `String username` | Derived query |
| `findByEmail` | `Optional<User>` | `String email` | Derived query |
| `findByFirstNameIgnoreCase` | `List<User>` | `String firstName` | Derived query |
| `findByLastNameIgnoreCase` | `List<User>` | `String lastName` | Derived query |
| `searchByName` | `List<User>` | `@Param("searchTerm") String searchTerm` | `@Query` JPQL |
| `findByPhoneNumber` | `Optional<User>` | `String phoneNumber` | Derived query |

### JPQL Query
```sql
SELECT u FROM User u 
WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) 
   OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
```

## Implicit Interfaces (Spring Framework)

### REST API Contract (UserController)
- Implements Spring MVC `@RestController` contract
- JSON serialization/deserialization via Jackson

### JPA Entity Contract (User)
- Implements JPA lifecycle via `@Entity`
- Callback interfaces: `@PrePersist`, `@PreUpdate`

### Bean Validation Contract (UserDTO, User)
- Implements `javax.validation` constraint annotations

## Cross-References

- [API Reference](api-reference.md)
- [Data Models](data-models.md)
- [Components](../architecture/components.md)
