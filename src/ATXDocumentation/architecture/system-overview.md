# System Overview

## Architecture Style

**Layered Monolithic Architecture** deployed as a WAR on JBoss/WildFly application server.

```
┌─────────────────────────────────────────────┐
│              JBoss/WildFly                   │
│  ┌───────────────────────────────────────┐  │
│  │         Spring Boot 2.5.14            │  │
│  │  ┌─────────────────────────────────┐  │  │
│  │  │     REST Controller Layer       │  │  │
│  │  │     (UserController)            │  │  │
│  │  └──────────────┬──────────────────┘  │  │
│  │                 │                     │  │
│  │  ┌──────────────▼──────────────────┐  │  │
│  │  │      Service Layer              │  │  │
│  │  │      (UserService)              │  │  │
│  │  └──────────────┬──────────────────┘  │  │
│  │                 │                     │  │
│  │  ┌──────────────▼──────────────────┐  │  │
│  │  │     Repository Layer            │  │  │
│  │  │     (UserRepository)            │  │  │
│  │  └──────────────┬──────────────────┘  │  │
│  │                 │                     │  │
│  └─────────────────┼─────────────────────┘  │
└─────────────────────┼───────────────────────┘
                      │
         ┌────────────▼────────────┐
         │    MySQL 8.0 Database   │
         │    (crud_db / users)    │
         └─────────────────────────┘
```

## Deployment Model

- **Packaging**: WAR file (`spring-crud-app-1.0.0.war`)
- **Application Server**: JBoss/WildFly 12+
- **Context Root**: `/spring-crud-app` (configured in `jboss-web.xml`)
- **API Base Path**: `/api` (configured via `server.servlet.context-path`)
- **Port**: 8080

## Technology Decisions

| Decision | Choice | Rationale |
|----------|--------|-----------|
| Build tool | Maven | Standard Java build tool |
| ORM | Spring Data JPA | Simplifies data access with repository pattern |
| Validation | Bean Validation (javax.validation) | Declarative input validation |
| Logging | SLF4J + Logback | Spring Boot default, structured logging |
| Boilerplate reduction | Lombok | Generates getters/setters/constructors |
| JSON serialization | Jackson | Spring Boot default |

## Cross-References

- [Components](components.md)
- [Dependencies](dependencies.md)
- [Patterns](patterns.md)
- [Project Overview](../project-overview.md)
