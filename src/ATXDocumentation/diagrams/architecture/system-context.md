# Architecture Diagrams

## System Context Diagram

```
┌─────────────┐          ┌────────────────────────────────┐
│             │  HTTP     │       JBoss/WildFly            │
│   Client    │─────────►│                                │
│  (Browser/  │  REST    │  ┌──────────────────────────┐  │
│   curl/     │◄─────────│  │  Spring Boot Application │  │
│   app)      │  JSON    │  │  (spring-crud-app.war)   │  │
│             │          │  └────────────┬─────────────┘  │
└─────────────┘          │               │                │
                         └───────────────┼────────────────┘
                                         │ JDBC
                                         │
                         ┌───────────────▼────────────────┐
                         │         MySQL 8.0              │
                         │                                │
                         │  Database: crud_db             │
                         │  Table: users                  │
                         │                                │
                         │  Indexes:                      │
                         │  - idx_username                │
                         │  - idx_email                   │
                         │  - idx_created_at              │
                         └────────────────────────────────┘
```

## Integration Patterns

```
┌─────────────────────────────────────────┐
│         External Integrations           │
│                                         │
│  • MySQL (JDBC via HikariCP pool)       │
│  • No external APIs                     │
│  • No message queues                    │
│  • No caching layer                     │
│  • No service discovery                 │
└─────────────────────────────────────────┘
```

## Security Boundaries

```
┌─────────────────────────────────────────────────────┐
│                    NO AUTH BOUNDARY                  │
│  (All endpoints publicly accessible)                │
│                                                     │
│  ┌──────────────────────────────────────────────┐  │
│  │            Validation Boundary               │  │
│  │  (Bean Validation on @RequestBody)           │  │
│  │                                              │  │
│  │  ┌───────────────────────────────────────┐  │  │
│  │  │         Transaction Boundary          │  │  │
│  │  │  (@Transactional on UserService)      │  │  │
│  │  │                                       │  │  │
│  │  │  ┌────────────────────────────────┐  │  │  │
│  │  │  │     Database Constraints      │  │  │  │
│  │  │  │  (UNIQUE, NOT NULL, FK)       │  │  │  │
│  │  │  └────────────────────────────────┘  │  │  │
│  │  └───────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────┘
```

## Deployment Architecture

```
┌─────────────────────────────────────┐
│           Host Machine              │
│                                     │
│  ┌───────────────────────────────┐  │
│  │     JBoss/WildFly 12+        │  │
│  │     Port: 8080 (HTTP)        │  │
│  │     Port: 9990 (Management)  │  │
│  │                               │  │
│  │  Deployments:                 │  │
│  │  - spring-crud-app.war        │  │
│  │    Context: /spring-crud-app  │  │
│  │                               │  │
│  │  Datasources:                 │  │
│  │  - java:jboss/datasources/   │  │
│  │    CrudDB (Pool: 10-20)      │  │
│  └───────────────────────────────┘  │
│                                     │
│  ┌───────────────────────────────┐  │
│  │     MySQL 8.0                 │  │
│  │     Port: 3306               │  │
│  │     Database: crud_db         │  │
│  └───────────────────────────────┘  │
└─────────────────────────────────────┘
```
