# Blogging-Platform Backend

This is the backend component of the **Blogging-Platform** project, developed using **Spring Boot**. It provides a secure RESTful API for managing users and blog posts, including authentication, authorization, and content management.

-----

## Overview

The backend offers APIs to:

  - Register and authenticate users with role-based access control (`USER`, `ADMIN`).
  - Create, read, update, and delete blog posts linked to authenticated users.
  - Allow admins to moderate blog content and manage user accounts.
  - Secure endpoints using JWT-based stateless authentication.
  - Handle exceptions uniformly with meaningful error responses.

-----

## Features

  - **User Management:**
      - Secure registration with unique username and email.
      - Passwords securely hashed with BCrypt.
      - Role assignment at registration (default `USER`).
  - **Authentication & Authorization:**
      - Login returns JWT tokens for sessionless auth.
      - Role-based restriction on API routes (`ADMIN` vs `USER`).
      - JWT filter validates tokens on incoming requests.
  - **Blog Operations:**
      - Users can create, update, and delete their own blogs.
      - Blogs have statuses: `PENDING`, `APPROVED`, and `REJECTED`.
      - Admins can view and moderate all blogs.
  - **Exception Handling:**
      - Centralized global exception handling with custom exceptions.
      - Consistent error response format including timestamp and HTTP status.

-----

## Technologies Used

  - Java 17, Spring Boot 3.x
  - Spring Security with JWT
  - Spring Data JPA and Hibernate ORM
  - BCrypt password encoder
  - Jakarta Validation API
  - JSON Web Tokens (jjwt library)
  - H2 (in-memory) or any relational database supported by JPA

-----

## API Highlights

  - `POST /auth/register`: Register a new user.
  - `POST /auth/login`: Authenticate and obtain JWT token.
  - `GET/POST/PUT/DELETE /api/user/blogs`: CRUD operations on blogs by logged-in users.
  - `GET/PUT/DELETE /api/admin/blogs`: Blog moderation operations by admins.
  - `GET/PUT/DELETE /api/admin/users`: User management by admins.

-----

## Running the Backend

1.  Configure your database connection in `application.properties` or `application.yml`.
2.  Build using Maven:
    ```
    mvn clean install
    ```
3.  Run the application:
    ```
    mvn spring-boot:run
    ```
4.  Use API testing tools (Postman, curl) to interact with the endpoints.
5.  Include JWT token in `Authorization: Bearer <token>` header for secured APIs.

-----

## Next Steps

  - Frontend implementation using React.js will consume these APIs.
  - Possible future enhancements include comments, tags, user profiles, and password reset flows.

-----

This backend is designed to be robust, extensible, and secure, providing a solid foundation for the blogging platform.

-----

For any questions or contributions, feel free to open issues or pull requests.
