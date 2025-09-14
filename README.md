# DevOps Platform - Spring Boot Backend

A comprehensive DevOps platform backend built with Spring Boot, providing requirements management (Jira-like), merge request tracking (GitLab-like), artifact management (Minio/Artifactory), and CI/CD pipeline management.

## 🚀 Features

- **Project Management**: Create and manage development projects
- **Requirements Management**: Jira-like epics, stories, tasks, and bugs
- **Merge Request Tracking**: GitLab-like code review workflow
- **Artifact Management**: Support for Minio/Artifactory storage
- **Build & Deployment Tracking**: CI/CD pipeline monitoring
- **User & Role Management**: RBAC with multiple user roles
- **RESTful APIs**: Comprehensive API endpoints for all operations

## 🛠️ Tech Stack

- **Java 17+**: Modern Java features and performance
- **Spring Boot 3.2.0**: Rapid application development
- **Spring Data JPA**: Database abstraction layer
- **H2 Database**: In-memory database for development
- **MySQL**: Production database (configurable)
- **Maven**: Build and dependency management

## 📦 Project Structure

```
src/
├── main/
│   ├── java/com/devops/platform/
│   │   ├── controller/     # REST API controllers
│   │   ├── service/        # Business logic layer
│   │   ├── repository/     # Data access layer (Spring Data JPA)
│   │   ├── entity/         # JPA entities (database models)
│   │   ├── config/         # Configuration classes
│   │   └── dto/            # Data Transfer Objects
│   └── resources/
│       ├── application.yml # Main configuration
│       └── data.sql        # Initial test data
└── test/
    ├── java/com/devops/platform/controller/ # Integration tests
    └── resources/
        ├── application-test.yml # Test configuration
        └── data.sql             # Test data initialization
```

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- (Optional) MySQL for production

### Running the Application

1. **Clone and navigate to project**:
   ```bash
   cd sandbox_springboot
   ```

2. **Run with Maven (默认开发环境)**:
   ```bash
   mvn spring-boot:run
   ```

3. **Application will start on**: http://localhost:8081

### Using Maven Wrapper
```bash
./mvnw spring-boot:run
```

## 🌍 多环境配置

项目支持多环境部署，通过Maven Profile选择不同的环境配置。

### 环境配置

| 环境 | Profile | 端口 | 数据库 | DDL策略 | 用途 |
|------|---------|------|--------|---------|------|
| dev | dev (默认) | 8081 | H2内存 | create-drop | 开发环境 |
| test | test | 8082 | H2内存 | create-drop | 测试环境 |
| staging | staging | 8083 | MySQL | update | 预发布环境 |
| prod | prod | 8080 | MySQL | validate | 生产环境 |

### 启动不同环境

```bash
# 开发环境 (默认)
mvn spring-boot:run

# 测试环境
mvn spring-boot:run -Ptest

# 预发布环境
mvn spring-boot:run -Pstaging

# 生产环境
mvn spring-boot:run -Pprod
```

### 环境变量配置

对于staging和prod环境，需要设置以下环境变量：

```bash
# MySQL数据库配置
export DB_HOST=localhost
export DB_PORT=3306
export DB_USERNAME=your_username
export DB_PASSWORD=your_password

# 生产环境JWT密钥
export JWT_SECRET=your_jwt_secret_key
```

### 配置文件说明

- `application.yml` - 基础配置，所有环境共享
- `application-test.yml` - 测试环境特定配置
- `application-staging.yml` - 预发布环境特定配置
- `application-prod.yml` - 生产环境特定配置

配置文件按优先级加载：基础配置 → Profile特定配置 → 环境变量 → 命令行参数

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=ProjectControllerTest
```

### Test Coverage Report
```bash
mvn test jacoco:report
```

## 📋 API Endpoints

### Projects
- `GET /api/projects` - List all projects
- `POST /api/projects` - Create new project
- `GET /api/projects/{id}` - Get project by ID
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

### Requirements (Jira-like)
- `GET /api/requirements/project/{projectId}` - Get project requirements
- `POST /api/requirements` - Create requirement
- `PATCH /api/requirements/{id}/status/{statusId}` - Update status

### Merge Requests (GitLab-like)
- `GET /api/merge-requests/project/{projectId}` - Get project MRs
- `POST /api/merge-requests` - Create merge request
- `PATCH /api/merge-requests/{id}/status/{statusId}` - Update MR status

### Artifacts (Minio/Artifactory)
- `GET /api/artifacts/project/{projectId}` - Get project artifacts
- `POST /api/artifacts` - Create artifact record
- `GET /api/artifacts/artifact/{artifactId}` - Get artifacts by ID

## ⚙️ Configuration

### Development (H2 Database)
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:devops_platform
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

### Production (MySQL)
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/devops_platform
    username: your_username
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
```

## 🗃️ Database Schema

The platform includes comprehensive entity relationships:

- **Projects** ↔ Requirements, Merge Requests, Builds, Sprints
- **Requirements** ↔ Types, Statuses, Priorities, Sprints
- **Merge Requests** ↔ Statuses, Code Reviews, Projects
- **Artifacts** ↔ Types, Builds, Projects
- **Users** ↔ Roles, Code Reviews

## 🚢 Deployment

### Docker Deployment

1. **Build Docker image**:
   ```bash
   docker build -t devops-platform .
   ```

2. **Run container**:
   ```bash
   docker run -p 8081:8081 -e SPRING_PROFILES_ACTIVE=prod devops-platform
   ```

### Kubernetes Deployment

1. **Create deployment**:
   ```yaml
   apiVersion: apps/v1
   kind: Deployment
   metadata:
     name: devops-platform
   spec:
     replicas: 3
     template:
       spec:
         containers:
         - name: devops-platform
           image: devops-platform:latest
           ports:
           - containerPort: 8081
   ```

2. **Create service**:
   ```yaml
   apiVersion: v1
   kind: Service
   metadata:
     name: devops-platform-service
   spec:
     selector:
       app: devops-platform
     ports:
     - protocol: TCP
       port: 80
       targetPort: 8081
   ```

## 🔧 Development

### Adding New Entities

1. Create entity class in `entity/` package
2. Create repository interface in `repository/` package
3. Create service class in `service/` package
4. Create controller in `controller/` package
5. Add test cases in `test/` package

### Code Style
- Follow Spring Boot conventions
- Use Lombok for boilerplate code reduction
- Implement proper error handling
- Write comprehensive unit tests

## 📊 Monitoring

### Health Check
```bash
curl http://localhost:8081/actuator/health
```

### Metrics
```bash
curl http://localhost:8081/actuator/metrics
```

### Info Endpoint
```bash
curl http://localhost:8081/actuator/info
```

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support, email support@devops-platform.com or create an issue in the GitHub repository.

---

**Happy Coding! 🚀**