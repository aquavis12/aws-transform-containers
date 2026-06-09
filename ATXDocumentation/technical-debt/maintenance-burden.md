# Maintenance Burden

## Areas Requiring Significant Maintenance

### 1. JBoss Application Server Management
- **Burden**: High
- **Description**: Requires maintaining JBoss/WildFly installation, configuration (standalone.xml), MySQL driver module, and datasource definitions separate from the application
- **Evidence**: `DEPLOYMENT.md` documents extensive manual configuration steps
- **Impact**: Deployment complexity, environment drift, configuration management overhead

### 2. javax to jakarta Migration (Future)
- **Burden**: High
- **Description**: When upgrading to Spring Boot 3.x, all `javax.persistence` and `javax.validation` imports must be migrated to `jakarta.*`
- **Files affected**: User.java, UserDTO.java, UserController.java
- **Impact**: Requires coordinated changes across multiple files

### 3. Manual DTO Conversion
- **Burden**: Low
- **Description**: `UserService` manually maps between User and UserDTO (2 private methods)
- **Current scale**: 8 fields, manageable
- **Growth risk**: Each new field requires updating both conversion methods

### 4. Database Schema Synchronization
- **Burden**: Medium
- **Description**: Using `ddl-auto=update` — Hibernate manages schema changes automatically
- **Risk**: Production deployments should use `validate` with managed migrations (Flyway/Liquibase)
- **Impact**: No version-controlled schema evolution

### 5. Security Gap Maintenance
- **Burden**: Medium
- **Description**: No authentication/authorization framework in place
- **Impact**: Any production deployment requires significant security implementation work before being viable

## Maintenance Metrics

| Area | Change Frequency | Effort per Change | Risk |
|------|-----------------|-------------------|------|
| JBoss config | Per environment | High | Medium |
| Entity/DTO fields | Per feature | Low | Low |
| Dependencies | Per security advisory | High (blocked) | High |
| Database schema | Per feature | Medium | Medium |

## Cross-References

- [Summary](summary.md)
- [Remediation Plan](remediation-plan.md)
- [Patterns](../architecture/patterns.md)
