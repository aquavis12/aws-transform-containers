# Outdated Components

## Runtime and Framework (High Severity)

### Java 8
- **Current**: 1.8 (configured in pom.xml `<java.version>1.8</java.version>`)
- **Status**: End of public updates (Oracle, Jan 2019); OpenJDK community patches ongoing but ecosystem is moving to 17+
- **Latest LTS**: Java 21 (September 2023)
- **Impact**: Missing language features, reduced security posture, shrinking library compatibility

### Spring Boot 2.5.14
- **Current**: 2.5.14 (pom.xml parent)
- **Status**: End of Life (August 2023)
- **Latest**: Spring Boot 3.4.x (requires Java 17+)
- **Impact**: No security patches, no bug fixes, dependency constraints

### javax.* APIs (Pre-Jakarta EE)
- **Current**: `javax.persistence.*`, `javax.validation.*`
- **Status**: Superseded by Jakarta EE 9+ (`jakarta.*` namespace)
- **Impact**: Blocks Spring Boot 3.x migration path
- **Files affected**: User.java, UserDTO.java, UserController.java

## Dependencies (Medium Severity)

### mysql-connector-java 8.0.33
- **Current**: `mysql:mysql-connector-java:8.0.33`
- **Status**: Artifact renamed to `com.mysql:mysql-connector-j` in 8.0.31+
- **Latest**: mysql-connector-j 9.x
- **Impact**: Deprecated artifact coordinates; functionally still works

## Build Tools (Low Severity)

### Maven WAR Plugin 3.3.1
- **Current**: 3.3.1
- **Latest**: 3.4.0
- **Impact**: Minor improvements only

## Application Server

### JBoss/WildFly Deployment Model
- **Current**: WAR packaging with jboss-web.xml descriptor
- **Status**: Legacy deployment model; modern Spring Boot apps use standalone JAR
- **Impact**: Operational overhead, additional infrastructure to maintain, slower deployment cycles

## Cross-References

- [Summary](summary.md)
- [Remediation Plan](remediation-plan.md)
- [Dependencies](../architecture/dependencies.md)
