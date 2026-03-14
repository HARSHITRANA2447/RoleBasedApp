# Role-Based Application

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-green.svg)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-4.0+-blue.svg)](https://www.mongodb.com/)
[![JWT](https://img.shields.io/badge/JWT-Enabled-orange.svg)](https://jwt.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A robust and secure role-based application built with Spring Boot, featuring JWT authentication, MongoDB integration, and comprehensive user management with role-based access control (RBAC).

## 🌟 Features

- **🔐 JWT Authentication**: Secure token-based authentication for user sessions.
- **👥 Role-Based Access Control**: Supports multiple roles (Admin, HR, User) with specific permissions.
- **📊 MongoDB Integration**: NoSQL database for efficient data storage and retrieval.
- **🚀 RESTful APIs**: Well-structured endpoints for user management, authentication, and role-specific operations.
- **🛡️ Security Configuration**: Custom security setup with CORS support for frontend integration.
- **📝 Comprehensive Logging**: Detailed logging for debugging and monitoring.
- **🔧 Exception Handling**: Global exception handler for consistent error responses.

## 🛠️ Technologies Used

- **Backend**: Spring Boot 3.0+
- **Database**: MongoDB
- **Security**: JWT (JSON Web Tokens)
- **Build Tool**: Maven
- **Java Version**: 17+
- **Other**: Spring Security, Spring Data MongoDB

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- Java 17 or higher
- Maven 3.6+
- MongoDB 4.0+
- Git (for cloning the repository)

## 🚀 Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/role-based-app.git
   cd role-based-app
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Configure MongoDB:**
   - Ensure MongoDB is running on `localhost:27017`
   - The application will create the database `rolebasedapp` automatically

4. **Configure JWT Secret:**
   - Update `jwt.secret` in `src/main/resources/application.properties` for production use

5. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📖 Usage

### API Endpoints

#### Authentication
- `POST /api/auth/signup` - User registration
- `POST /api/auth/login` - User login

#### User Management (Requires Authentication)
- `GET /api/user/profile` - Get user profile
- `PUT /api/user/update` - Update user information

#### HR Operations (HR Role Required)
- `GET /api/hr/employees` - View all employees
- `POST /api/hr/employee` - Add new employee

#### Admin Operations (Admin Role Required)
- `GET /api/admin/users` - View all users
- `DELETE /api/admin/user/{id}` - Delete a user
- `PUT /api/admin/user/{id}/role` - Update user role

### Example API Usage

```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password"}'

# Access protected endpoint
curl -X GET http://localhost:8080/api/admin/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔧 Configuration

Key configuration options in `application.properties`:

- `server.port`: Application port (default: 8080)
- `spring.data.mongodb.*`: MongoDB connection settings
- `jwt.secret`: Secret key for JWT token generation
- `jwt.expiration`: Token expiration time in milliseconds
- `cors.allowed-origins`: Allowed origins for CORS

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/example/rolebasedapp/
│   │   ├── RoleBasedAppApplication.java
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── AdminController.java
│   │   │   ├── AuthController.java
│   │   │   ├── HRController.java
│   │   │   └── UserController.java
│   │   ├── dto/
│   │   │   ├── AuthResponse.java
│   │   │   ├── LoginRequest.java
│   │   │   ├── SignupRequest.java
│   │   │   └── UserDTO.java
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── UnauthorizedException.java
│   │   ├── model/
│   │   │   ├── Role.java
│   │   │   └── User.java
│   │   ├── repository/
│   │   │   └── UserRepository.java
│   │   ├── security/
│   │   │   ├── CustomUserDetailsService.java
│   │   │   ├── JwtAuthenticationFilter.java
│   │   │   └── JwtUtil.java
│   │   └── service/
│   │       ├── AdminService.java
│   │       ├── AuthService.java
│   │       ├── HRService.java
│   │       └── UserService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/rolebasedapp/
        └── RoleBasedAppApplicationTests.java
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Contact

For questions or support, please open an issue on GitHub.

---

⭐ If you find this project helpful, please give it a star!
