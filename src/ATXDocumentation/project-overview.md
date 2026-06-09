# Project Overview

## Application Identity

- **Name**: Spring CRUD Application
- **Group**: com.example
- **Artifact**: spring-crud-app
- **Version**: 1.0.0
- **Packaging**: WAR

## Purpose

A RESTful CRUD application for managing user records. Provides endpoints for creating, reading, updating, deleting, and searching users. Designed for deployment on JBoss/WildFly application server with MySQL as the persistent data store.

## Technology Stack

| Layer | Technology | Version |
|-------|-----------|---------|
| Language | Java | 8 (1.8) |
| Framework | Spring Boot | 2.5.14 |
| ORM | Spring Data JPA / Hibernate | Managed by Spring Boot |
| Database | MySQL | 8.0 |
| DB Driver | mysql-connector-java | 8.0.33 |
| Build Tool | Maven | 3.6+ |
| App Server | JBoss/WildFly | 12+ |
| Utility | Lombok | Managed by Spring Boot |

## Source Code Metrics

- **Total source files**: 7 Java files + 1 properties + 1 XML descriptor
- **Total lines of code**: ~559 (Java, properties, XML)
- **Packages**: 5 (controller, service, repository, model, dto, exception)

## Key Characteristics

1. **Layered Architecture**: Controller → Service → Repository → Database
2. **WAR Deployment**: Packaged as WAR for JBoss/WildFly with embedded Tomcat marked as `provided`
3. **DTO Pattern**: Separates API contract (UserDTO) from persistence model (User)
4. **Bean Validation**: javax.validation constraints on input
5. **Global Exception Handling**: Centralized error responses via @RestControllerAdvice
6. **JPA Lifecycle Hooks**: @PrePersist/@PreUpdate for timestamp management

## Cross-References

- [Architecture Details](architecture/system-overview.md)
- [Technical Debt Report](technical-debt-report.md)
- [API Reference](reference/api-reference.md)
