# Dependencies

## External Dependencies (from pom.xml)

| Dependency | Group ID | Version | Scope | Status |
|-----------|----------|---------|-------|--------|
| spring-boot-starter-web | org.springframework.boot | 2.5.14 (managed) | compile | **EOL** |
| spring-boot-starter-data-jpa | org.springframework.boot | 2.5.14 (managed) | compile | **EOL** |
| spring-boot-starter-validation | org.springframework.boot | 2.5.14 (managed) | compile | **EOL** |
| spring-boot-starter-tomcat | org.springframework.boot | 2.5.14 (managed) | provided | **EOL** |
| spring-boot-starter-test | org.springframework.boot | 2.5.14 (managed) | test | **EOL** |
| mysql-connector-java | mysql | 8.0.33 | compile | Renamed to mysql-connector-j |
| lombok | org.projectlombok | Managed by Spring Boot | compile (optional) | Current |

## Build Plugins

| Plugin | Version | Purpose |
|--------|---------|---------|
| spring-boot-maven-plugin | 2.5.14 (managed) | Repackage as executable/WAR |
| maven-war-plugin | 3.3.1 | WAR packaging (failOnMissingWebXml=false) |

## Internal Component Dependencies

```
SpringCrudApplication
    └── (Spring auto-configuration)

UserController
    └── UserService (field injection via @Autowired)

UserService
    └── UserRepository (field injection via @Autowired)

UserRepository
    └── JpaRepository<User, Long> (extends)

User
    └── javax.persistence.* (JPA annotations)
    └── javax.validation.constraints.* (Bean Validation)
    └── lombok.* (code generation)

UserDTO
    └── javax.validation.constraints.* (Bean Validation)
    └── com.fasterxml.jackson.annotation.* (JSON formatting)
    └── lombok.* (code generation)

GlobalExceptionHandler
    └── org.springframework.web.bind.* (Spring MVC)
    └── org.springframework.validation.* (Validation)
```

## Declarative Service Definitions

### jboss-web.xml
- **File**: `src/main/webapp/WEB-INF/jboss-web.xml`
- **Purpose**: JBoss deployment descriptor
- **Defines**: Context root `/spring-crud-app`
- **Schema version**: jboss-web 10.0

## Transitive Dependencies (Key)

Via Spring Boot 2.5.14 BOM:
- Hibernate 5.4.x (JPA implementation)
- HikariCP (Connection pooling)
- Jackson 2.12.x (JSON serialization)
- Logback 1.2.x (Logging implementation)
- Tomcat 9.0.x (Embedded, provided scope)

## Cross-References

- [Dependency Analysis](../analysis/dependency-analysis.md)
- [Outdated Components](../technical-debt/outdated-components.md)
