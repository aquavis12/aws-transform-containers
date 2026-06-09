# Code Metrics

## Summary

| Metric | Value |
|--------|-------|
| Total source files | 9 (7 Java + 1 properties + 1 XML) |
| Total lines of code | ~559 |
| Java classes | 6 |
| Java interfaces | 1 |
| Packages | 6 |
| REST endpoints | 8 |
| Database tables | 1 |

## Per-File Metrics

| File | Lines | Methods | Complexity |
|------|-------|---------|-----------|
| UserController.java | ~100 | 8 | Low |
| UserService.java | ~135 | 10 | Low-Medium |
| UserRepository.java | ~40 | 6 | Low |
| User.java | ~55 | 2 (lifecycle) | Low |
| UserDTO.java | ~35 | 0 (Lombok-generated) | Low |
| GlobalExceptionHandler.java | ~65 | 3 | Low |
| SpringCrudApplication.java | ~12 | 1 | Low |

## Quality Indicators

- **Test coverage**: No test files found in source directory (spring-boot-starter-test is a dependency)
- **Documentation**: Javadoc comments on repository methods and controller endpoints
- **Code duplication**: Minimal — conversion methods are single-location
- **Naming conventions**: Consistent Java conventions throughout
- **Logging**: Comprehensive SLF4J logging in controller and service layers

## Cross-References

- [Complexity Analysis](complexity-analysis.md)
- [Program Structure](../reference/program-structure.md)
