# Technical Debt Summary

## Overview

The Spring CRUD Application carries technical debt primarily in its runtime and framework choices. The application is functional but built on end-of-life components that no longer receive security updates.

## Debt Categories

| Category | Severity | Items |
|----------|----------|-------|
| EOL Runtimes & Frameworks | High | Java 8, Spring Boot 2.5.14, javax.* namespace |
| Outdated Dependencies | Medium | mysql-connector-java naming |
| Code Quality | Low | Field injection, generic exceptions, missing pagination, missing update validation |

## Risk Assessment

- **Security Risk**: High — No security patches for framework or runtime
- **Upgrade Path**: Blocked — Cannot move to Spring Boot 3.x without Java 17+ and Jakarta EE migration
- **Operational Risk**: Medium — JBoss deployment adds operational complexity

## Priority Actions

1. Upgrade Java 8 → Java 17 or 21 (LTS)
2. Upgrade Spring Boot 2.5.14 → 3.x (requires Jakarta EE migration)
3. Migrate from JBoss WAR deployment to standalone Spring Boot JAR
4. Address code quality issues

## Cross-References

- [Outdated Components](outdated-components.md)
- [Maintenance Burden](maintenance-burden.md)
- [Remediation Plan](remediation-plan.md)
- [Technical Debt Report](../technical-debt-report.md)
