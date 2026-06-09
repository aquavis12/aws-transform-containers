# Component Migration Order

## Recommended Migration Sequence

Based on dependency analysis, components should be migrated in the following order (leaf dependencies first):

### Phase 1: Foundation
1. **pom.xml** — Update Java version, Spring Boot parent, dependencies
2. **User.java** (model) — Migrate javax.persistence → jakarta.persistence, javax.validation → jakarta.validation

### Phase 2: Data Layer
3. **UserRepository.java** — Verify compatibility with updated Spring Data JPA
4. **init-database.sql** — No changes needed (database-side)

### Phase 3: Transfer Objects
5. **UserDTO.java** — Migrate javax.validation → jakarta.validation

### Phase 4: Business Logic
6. **UserService.java** — No import changes needed (uses internal types only)

### Phase 5: Web Layer
7. **UserController.java** — Migrate javax.validation → jakarta.validation
8. **GlobalExceptionHandler.java** — Verify Spring MVC compatibility

### Phase 6: Deployment Modernization
9. **application.properties** — Update Hibernate dialect, remove deprecated properties
10. **jboss-web.xml** — Remove (if migrating to standalone JAR)
11. **pom.xml** — Change packaging from `war` to `jar`, remove war plugin

## Dependency Rationale

```
pom.xml (defines all versions)
   └── User.java (core entity, no internal deps)
       └── UserRepository.java (depends on User)
           └── UserService.java (depends on Repository + DTO)
               └── UserController.java (depends on Service + DTO)
```

Migrating in this order ensures each component's dependencies are already migrated when it is processed.

## Cross-References

- [Validation Criteria](validation-criteria.md)
- [Test Specifications](test-specifications.md)
- [Remediation Plan](../technical-debt/remediation-plan.md)
