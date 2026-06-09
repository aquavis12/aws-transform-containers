# Security Patterns

## Implemented Security Controls

### 1. Input Validation
- Bean Validation (`javax.validation`) on UserDTO and User entity
- `@Valid` annotation on controller method parameters
- `@NotBlank`, `@Email` constraints prevent empty/malformed input

### 2. SQL Injection Prevention
- Spring Data JPA parameterized queries throughout
- `@Query` with `@Param` for custom JPQL (parameterized, not concatenated)

### 3. Error Information Hiding
- Generic exceptions return "An unexpected error occurred" (no stack traces to client)
- Stack traces logged server-side only

### 4. Column Length Constraints
- `address` limited to VARCHAR(500)
- `phoneNumber` limited to VARCHAR(20)
- Prevents oversized input storage

## Missing Security Controls

### High Priority
| Gap | Risk | Recommendation |
|-----|------|----------------|
| No authentication | Unauthorized access | Add Spring Security with JWT or session-based auth |
| No authorization | No role-based access control | Implement role-based access |
| Hardcoded credentials | Credential exposure in source | Use environment variables or secrets manager |

### Medium Priority
| Gap | Risk | Recommendation |
|-----|------|----------------|
| No HTTPS enforcement | Data in transit exposure | Configure TLS |
| No rate limiting | DoS vulnerability | Add rate limiting middleware |
| No CORS configuration | Cross-origin attacks | Configure CORS policy |
| No audit logging | Compliance gap | Add security event logging |

### Low Priority
| Gap | Risk | Recommendation |
|-----|------|----------------|
| No CSRF protection | Cross-site request forgery | Enable Spring Security CSRF |
| No request size limits | Resource exhaustion | Configure max request size |

## Credential Management

**Current state**: Database credentials hardcoded in `application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=root
```

**Recommendation**: Use environment variables, Spring profiles, or AWS Secrets Manager.

## Cross-References

- [Error Handling](../behavior/error-handling.md)
- [Technical Debt Report](../technical-debt-report.md)
