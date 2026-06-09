# Remediation Plan

## Priority 1: Runtime and Framework Upgrade (High Severity)

### Action 1.1: Upgrade Java 8 → Java 17 (or 21)
- Update `pom.xml`: `<java.version>17</java.version>`
- Update `maven.compiler.source` and `maven.compiler.target` to `17`
- Verify all dependencies are compatible with Java 17
- **Recommended transformation**: `AWS/java-version-upgrade`

### Action 1.2: Migrate javax.* → jakarta.*
- Replace `javax.persistence.*` with `jakarta.persistence.*` in User.java
- Replace `javax.validation.*` with `jakarta.validation.*` in User.java, UserDTO.java, UserController.java
- Update dependency from `spring-boot-starter-validation` (auto-handled by Spring Boot 3.x)

### Action 1.3: Upgrade Spring Boot 2.5.14 → 3.x
- Update parent POM to Spring Boot 3.x
- Requires Java 17+ (Action 1.1) and Jakarta EE migration (Action 1.2)
- Review Spring Boot 3.x migration guide for breaking changes

### Action 1.4: Migrate from JBoss to Standalone Spring Boot
- Change packaging from `war` to `jar` in pom.xml
- Remove `spring-boot-starter-tomcat` provided scope
- Remove `jboss-web.xml`
- Remove `maven-war-plugin` configuration
- **Recommended transformation**: `AWS/JBoss-to-Spring-Boot`

## Priority 2: Dependency Updates (Medium Severity)

### Action 2.1: Update MySQL Connector
- Replace `mysql:mysql-connector-java:8.0.33` with `com.mysql:mysql-connector-j:8.x.x`
- Update groupId and artifactId in pom.xml

## Priority 3: Code Quality (Low Severity)

### Action 3.1: Replace Field Injection with Constructor Injection
- Add constructor with `@RequiredArgsConstructor` (Lombok) to UserController and UserService
- Remove `@Autowired` field annotations

### Action 3.2: Implement Custom Exception Hierarchy
- Create `UserNotFoundException extends ResponseStatusException` (404)
- Create `DuplicateResourceException extends ResponseStatusException` (409)
- Replace generic RuntimeException throws

### Action 3.3: Add Pagination
- Change `getAllUsers()` to accept `Pageable` parameter
- Return `Page<UserDTO>` instead of `List<UserDTO>`

### Action 3.4: Add Uniqueness Validation on Update
- Check username/email uniqueness in `updateUser` (excluding current user's ID)

## Execution Order

```
1.1 Java Upgrade → 1.2 Jakarta Migration → 1.3 Spring Boot 3.x → 1.4 Remove JBoss
                                                                         │
2.1 MySQL Connector ─────────────────────────────────────────────────────┤
                                                                         │
3.1-3.4 Code Quality (can be done in parallel) ──────────────────────────┘
```

## Cross-References

- [Summary](summary.md)
- [Outdated Components](outdated-components.md)
- [Technical Debt Report](../technical-debt-report.md)
