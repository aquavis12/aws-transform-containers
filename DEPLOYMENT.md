# JBoss Deployment Guide

This document provides step-by-step instructions for deploying the Spring CRUD Application to JBoss/WildFly.

## Prerequisites

- JBoss/WildFly 12.0.0 or later installed and configured
- MySQL 5.7 or 8.0 running and accessible
- The application built as a WAR file

## Step 1: Database Setup

### Option A: Using Script
```bash
mysql -u root -p < sql/init-database.sql
```

### Option B: Manual Setup
```sql
CREATE DATABASE crud_db;
USE crud_db;
```

## Step 2: Build the Application

```bash
mvn clean package
```

The WAR file will be created at:
```
target/spring-crud-app-1.0.0.war
```

## Step 3: Configure JBoss Data Source

### 3.1 Edit JBoss Configuration File

Edit `$JBOSS_HOME/standalone/configuration/standalone.xml`

Find the `<datasources>` section and add:

```xml
<datasources>
    <datasource jndi-name="java:jboss/datasources/CrudDB" pool-name="CrudDB_Pool" enabled="true" use-java-context="true">
        <connection-url>jdbc:mysql://localhost:3306/crud_db?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true</connection-url>
        <driver-name>mysql</driver-name>
        <security>
            <user-name>root</user-name>
            <password>root</password>
        </security>
        <pool>
            <min-pool-size>10</min-pool-size>
            <max-pool-size>20</max-pool-size>
            <prefill>true</prefill>
            <use-strict-min>false</use-strict-min>
            <flush-strategy>FailingConnectionOnly</flush-strategy>
        </pool>
        <validation>
            <check-valid-connection-sql>SELECT 1</check-valid-connection-sql>
            <background-validation>true</background-validation>
            <background-validation-millis>10000</background-validation-millis>
        </validation>
        <statement>
            <prepared-statement-cache-size>32</prepared-statement-cache-size>
            <share-prepared-statements>true</share-prepared-statements>
        </statement>
    </datasource>
    
    <xa-datasource jndi-name="java:jboss/datasources/xa/CrudDB_XA" pool-name="CrudDB_XA_Pool" enabled="false">
        <xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
        <xa-datasource-property name="ServerName">localhost</xa-datasource-property>
        <xa-datasource-property name="Port">3306</xa-datasource-property>
        <xa-datasource-property name="DatabaseName">crud_db</xa-datasource-property>
        <xa-datasource-property name="User">root</xa-datasource-property>
        <xa-datasource-property name="Password">root</xa-datasource-property>
    </xa-datasource>
    
    <drivers>
        <driver name="mysql" module="com.mysql">
            <xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
        </driver>
        <driver name="h2" module="com.h2database.h2">
            <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
        </driver>
    </drivers>
</datasources>
```

### 3.2 Add MySQL Driver Module

Create directory structure:
```
$JBOSS_HOME/modules/com/mysql/main/
```

Create `module.xml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
    <resources>
        <resource-root path="mysql-connector-java-8.0.33.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```

Copy `mysql-connector-java-8.0.33.jar` to `$JBOSS_HOME/modules/com/mysql/main/`

## Step 4: Deploy the Application

### Option A: Copy to Deployments Directory
```bash
cp target/spring-crud-app-1.0.0.war $JBOSS_HOME/standalone/deployments/
```

### Option B: Using JBoss CLI

```bash
cd $JBOSS_HOME/bin
./jboss-cli.sh
```

In the CLI:
```
connect
deploy --force /path/to/target/spring-crud-app-1.0.0.war --name=spring-crud-app.war
```

## Step 5: Start JBoss

### Linux/Mac
```bash
$JBOSS_HOME/bin/standalone.sh
```

### Windows
```bash
$JBOSS_HOME\bin\standalone.bat
```

## Step 6: Verify Deployment

Check logs:
```bash
tail -f $JBOSS_HOME/standalone/log/server.log
```

Look for messages like:
```
Deployed "spring-crud-app.war"
```

## Step 7: Test the Application

```bash
curl -X GET http://localhost:8080/spring-crud-app/api/users
```

## Configuration Updates for Production

### Update application.properties

Create `src/main/resources/application-prod.properties`:

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://db-server:3306/crud_db?useSSL=true&serverTimezone=UTC
spring.datasource.username=crud_user
spring.datasource.password=secure_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Logging
logging.level.root=WARN
logging.level.com.example=INFO
```

### Build with Production Profile

```bash
mvn clean package -P prod
```

## Monitoring and Troubleshooting

### Check Application Logs
```bash
tail -f $JBOSS_HOME/standalone/log/server.log
```

### JBoss Management Console

Access: `http://localhost:9990`

Default credentials may need to be configured:
```bash
$JBOSS_HOME/bin/add-user.sh
```

### Database Connection Issues

Test connection from JBoss CLI:
```
connect
/subsystem=datasources/data-source=CrudDB:test-connection-in-pool
```

### Performance Tuning

Edit `$JBOSS_HOME/bin/standalone.conf` (Linux/Mac):

```bash
JAVA_OPTS="$JAVA_OPTS -Xms1024m -Xmx2048m"
JAVA_OPTS="$JAVA_OPTS -XX:+UseG1GC -XX:MaxGCPauseMillis=200"
```

## Undeployment

### Option A: Remove WAR File
```bash
rm $JBOSS_HOME/standalone/deployments/spring-crud-app.war*
```

### Option B: Using JBoss CLI
```bash
cd $JBOSS_HOME/bin
./jboss-cli.sh
connect
undeploy spring-crud-app.war
```

## Additional Resources

- [JBoss Documentation](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/)
- [Spring Boot Deployment](https://spring.io/guides/gs/spring-boot/)
- [MySQL JDBC Driver](https://dev.mysql.com/downloads/connector/j/)

## Support

For issues or questions, check:
1. JBoss server logs
2. Spring application logs
3. MySQL error logs
4. Database connectivity tests
