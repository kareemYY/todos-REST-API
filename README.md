# Todo REST API

A Spring Boot RESTful API for managing personal todos with JWT authentication, role-based authorization, validation, exception handling,
Swagger documentation and secure user-specific access control.

---

## Features
  - User registration & login using JWT Authentication.
  - Role-based authorization using Spring Security.
  - Full CRUD operations for user todos.
  - User-specific todo ownership validation.
  - Password encryption using BCrypt.
  - DTO mapping layer.
  - Input validation using Bean Validation.
  - Global exception handling.
  - Swagger / OpenAPI documentation.
  - Stateless session management.
  - Admin management endpoints.
  - Automatic admin role assignment for the first registered user.
  - Secure password update functionality.
  - H2 database integration.

---

## Tools & Technologies
  - Java
  - Spring Boot
  - Spring Data JPA
  - Hibernate
  - Spring Security
  - JWT Authentication
  - H2 Database
  - Swagger / OpenAPI
  - Maven

---

## API Endpoints Documentation

  ### Authentication Endpoints
  - @POST("/api/auth/register") ==========> Register new user
  - @POST("/api/auth/login") =============> Authenticate user & generate JWT token

  ### Todo Endpoints
  - @GET("/api/todo") ====================> Get all todos for authenticated user
  - @POST("/api/todo") ===================> Create new todo
  - @PUT("/api/todo/{id}") ===============> Toggle todo completion
  - @DELETE("/api/todo/{id}") ============> Delete todo
  
  ### User Endpoints
  - @GET("/api/users/info") ==============> Get current authenticated user information
  - @PUT("/api/users/password") ==========> Update user password
  - @DELETE("/api/users") ================> Delete current user account
  

  ### Admin Endpoints  
  - @GET("/api/admin") ===================> Get all users
  - @PUT("/api/admin/{userId}") ==========> Promote user to admin
  - @DELETE("/api/admin/{userId}") =======> Delete non-admin user
  
  ---

## Roles

- USER ==========> Access personal todo endpoints
- ADMIN =========> Full access to admin management endpoints

