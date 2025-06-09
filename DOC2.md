## Table of Contents
1. [Introduction](#introduction)
2. [Technologies & Frameworks](#technologies--frameworks)
3. [Project Design & Architecture](#project-design--architecture)
    - [Architecture Overview](#architecture-overview)
    - [Database Design](#database-design)
    - [Entity Layer](#entity-layer)
    - [DTOs](#dtos)
    - [Mappers](#mappers)
    - [Services](#services)
    - [Repositories](#repositories)
    - [Exception Handling](#exception-handling)
    - [Configuration Classes & Bootstrapping](#configuration-classes--bootstrapping)

4. [Flyway Database Migration](#flyway-database-migration)
5. [Configuration: ](#configuration-applicationproperties)`application.properties`
6. [Build File: `pom.xml` and Dependencies](#build-file-pomxml-and-dependencies)
7. [Controllers and API Endpoints](#controllers-and-api-endpoints)
    - [ProductController](#productcontroller)
    - [UserController](#usercontroller)

8. [API Response Formats](#api-response-formats)
9. [Security](#security)
10. [Logging & Monitoring](#logging--monitoring)
11. [Error Handling & Standardized Responses](#error-handling--standardized-responses)
12. [Swagger/OpenAPI Documentation](#swaggeropenapi-documentation)
13. [Running and Building the Application](#running-and-building-the-application)
14. [Testing](#testing)
15. [Troubleshooting Guide](#troubleshooting-guide)
16. [Appendix & Additional Resources](#appendix--additional-resources)

## 1. Introduction
This documentation describes a robust, maintainable, and scalable Spring Boot backend application. Designed for clean separation of concerns, the backend supports core business logic, exposes restful APIs, handles security, user and product management, and offers easy integration for frontend clients.
## 2. Technologies & Frameworks
- **Java 22** — Main programming language.
- **Spring Boot** — Rapid application development and auto-configuration.
- **Spring Data JPA** — ORM/persistence layer abstraction.
- **Spring MVC** — RESTful endpoints.
- **Lombok** — Minimizes boilerplate in POJOs.
- **MySQL** — Relational database backend.
- **Flyway** — Database schema version control.
- **Springdoc/OpenAPI (Swagger)** — API documentation and UI.
- **SLF4J/Logback** — Logging facade and implementation.
- **Jakarta EE** — Modular enhanced Java APIs.
- **Maven** — Build and dependency management.

**Project Structure:**
``` 
src/
  main/
    java/
      iti/
        ... (controllers, services, entities, dtos, mappers, repos, config)
    resources/
      application.properties
      db/
        migration/
          V1__init.sql
  test/
    java/
      ...
pom.xml
README.md
```
## 3. Project Design & Architecture
### Architecture Overview
The application utilizes a standardized layered architecture:
- **Controller Layer:** REST endpoints, input validation, API contracts.
- **Service Layer:** Application logic, business processes.
- **Repository Layer:** Data persistence (Spring Data JPA), DB abstraction.
- **Domain Layer:** Entities, DTOs, and mappers.
- **Configuration Layer:** Application startup, property sources, security, Swagger.
- **Exception Handling:** Centralized handler for uniform error responses.

This separation promotes:
- **Reusability**
- **Testability**
- **Ease of maintenance**

#### Component Diagram
``` 
[Frontend] <-----> [Controller] <-----> [Service] <-----> [Repository] <-----> [Database]
  |           REST              business        JPA/Custom             MySQL
                      ^           ^                ^
                  Swagger    Exception        Flyway
                     |
               [Configuration, Logging, Security]
```
### Database Design
- **DBMS:** MySQL
- **Schema Management:** Flyway (SQL scripts versioned in `/db/migration/`)
- **Core Tables (examples):**
    - `users` – application users
    - `products` – products
    - `roles` – user authorization roles

- Carefully mapped entity relationships (e.g., users ↔ orders, products ↔ categories)

**Sample Table (products):** | Field | Type | Description | |------------|-------------|---------------------| | id | BIGINT PK | Unique | | name | VARCHAR | Name | | brand | VARCHAR | Brand identifier | | price | DECIMAL | Price | | ... | ... | ... |
### Entity Layer
A Java Bean mapped to a DB table, annotated as `@Entity`.
_Purpose:_ Structure and type safety for DB records, cache data for business logic, transfer model within the backend.
- **Examples:** `UserEntity`, `ProductEntity`

### DTOs
**Data Transfer Objects** decouple internal data models from input/output API contracts.
_Purpose:_
- Hide inner model structure from API consumers for security and flexibility
- Tailor responses/requests for the client’s needs

_Examples:_
- — product details shown on lists `ProductSummaryDTO`
- — more in-depth product info `ProductDetailDTO`
- — user profile data `UserProfileDataDTO`

### Mappers
_Purpose:_ Convert between Entities and DTOs.
- Can be implemented manually or using mapping frameworks.
- Important for data encapsulation and separation of layers.

### Services
_Purpose:_
- Encapsulate business logic
- Orchestrate repositories and mappers
- Enforce rules, validation

**Example:**
`ProductService.getFilteredProducts(...)` calls the repository, applies logic, maps to DTOs.
### Repositories
- **Technology:** Spring Data JPA in `repository` package.
- Extend to access CRUD, pagination, and custom queries. `JpaRepository`

### Exception Handling
Centralized, uniform error handling using Spring’s `@ControllerAdvice`.
- Catches thrown exceptions, logs, and returns standardized error JSON.

**Common exception types:**
- `ResourceNotFoundException`
- `ValidationException`
- `BadRequestException`

### Configuration Classes & Bootstrapping
- **App configuration:** Spring Boot configures itself based on dependencies and main class.
- **Database, security, and API documentation are externalized in .`application.properties`**

## 4. Flyway Database Migration
_Purpose:_ Reliable version control for schema changes.
- Place migration scripts in: `/src/main/resources/db/migration/`
- Examples:
    - `V1__init_schema.sql`
    - `V2__add_table_product.sql`

- Migrations run automatically on startup.

## 5. Configuration: `application.properties`
Defines:
- **Active profile:** `spring.profiles.active=credentials`
- **DB Connection:** url, username, password
- **Hibernate & JPA:** dialect, naming strategy, SQL log
- **Flyway settings**
- **Swagger API docs URL/enable**
- **Logging path and pattern**

## 6. Build File: `pom.xml` and Dependencies
**Key dependencies:**
``` 
spring-boot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-validation
mysql-connector-java
lombok
flyway-core
springdoc-openapi-ui
```
**Maven:** Handles library management, test plugins, build profiles, and JAR packaging.
## 7. Controllers and API Endpoints
### **A. ProductController**
**Base Path:** `/api/products`
#### 1. [GET] `/api/products`
**Purpose:** List products with optional filters and pagination
**Query Parameters:**
- `brand` (list of brands to filter)
- `size` (list)
- `color` (list)
- `orderBy` (sorting criteria, e.g. price_asc/desc)
- `gender` (filter)
- `category` (filter)
- `keyWord` (search string)
- , `pageSize` (pagination) `pageNumber`

**Example Request:**
`GET /api/products?brand=Nike&color=Red&pageNumber=1&pageSize=10`
**Response:**
_HTTP 200, Content-Type: application/json_
``` json
{
  "items": [
    {
      "id": 123,
      "name": "Running Shoe",
      "brand": "Nike",
      "price": 199.99,
      "mainImageUrl": "https://cdn.example.com/prod/123.jpg"
    }
    // ...more products
  ],
  "page": 1,
  "size": 10,
  "total": 57
}
```
**Errors:**
- 400: Invalid query or values
- 500: Internal error

#### 2. [GET] `/api/products/{productId}`
**Purpose:** Detailed product info by Product ID
**Path Variable:**
- `productId` (long, required)

**Example Request:**
`GET /api/products/321`
**Response:** _HTTP 200, Content-Type: application/json_
``` json
{
  "id": 321,
  "name": "Ultra Cushion Running Shoe",
  "brand": "Adidas",
  "description": "Breathable mesh...etc",
  "price": 219.99,
  "sizes": ["40", "41", "42"],
  "colors": ["Red", "Blue"],
  "category": "Shoes",
  "gender": "Unisex",
  "images": [
    "https://cdn.example.com/prod/321_1.jpg",
    "https://cdn.example.com/prod/321_2.jpg"
  ]
}
```
**Errors:**
- 404: Product not found
- 500: Internal error

### **B. UserController**
**Base Path:** `/api/users`
**Security:**
Accessible only to the user or authorized parties (see `@PreAuthorize`).
Assumes JWT or session-based security.
#### 1. [GET] `/api/users/{userId}`
**Purpose:** Returns profile data of the specific user
**Path Variable:**
- `userId` (long, required)

**Example Request:**
`GET /api/users/99`
**Response:**
_HTTP 200, Content-Type: application/json_
``` json
{
  "id": 99,
  "name": "John Doe",
  "email": "john@example.com",
  "roles": ["USER"],
  "phone": "+12345678",
  "address": "123 Apple St, City"
}
```
**Errors:**
- 401: Not authenticated
- 403: Forbidden (different user)
- 404: User not found

#### 2. [PUT] `/api/users/{userId}`
**Purpose:** Update profile data of specific user
**Consumes:** `application/json`
**Path Variable:** `userId` **RequestBody:** `UserProfileDataDTO`
**Example:**
``` json
{
  "name": "John Doe",
  "phone": "+12345678",
  "address": "12 Main St",
  "email": "john@example.com"
}
```
**Response:**
_HTTP 204 No Content_ (successful update)
**Errors:**
- 400: Invalid input
- 401: Unauthorized
- 403: Forbidden
- 404: User not found

## 8. API Response Formats
- **Paginated resources:** Always return `page`, `size`, `total`, `items`
- **Error responses:**
``` json
{
  "timestamp": "2025-06-10T10:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found"
}
```
## 9. Security
- **Endpoints**: Protected API routes (eg. `/api/users`) require authentication and proper role/ownership
- **Implementation:** Spring Security + `@PreAuthorize`, token/JWT-based scheme

## 10. Logging & Monitoring
- Logs to `logs/app.log`
- Log pattern and level set in `application.properties`
- Health monitoring via Actuator endpoints

## 11. Error Handling & Standardized Responses
- Central `@ControllerAdvice` catches thrown exceptions.
- Returns clear, consistent error JSONs.
- Always logs detailed stacktraces for 5xx errors.

## 12. Swagger/OpenAPI Documentation
- **Swagger UI:** `/swagger-ui.html`
- **Raw JSON:** `/api-docs`
- All endpoints, models, error docs auto-generated.

## 13. Running and Building the Application
1. **Install requirements:** Java 22, Maven, MySQL.
2. **Configure DB in .`application.properties`**
3. **Build**:
   `mvn clean install`
4. **Start**:
   `java -jar target/YOUR-APP-NAME.jar`
5. **Visit:**
    - `/swagger-ui.html` for docs
    - `/api/products` for products list

## 14. Testing
- **Unit/integration tests** in `src/test/java/`
- Test DB is migrated via Flyway test profile
- Mockito used for service layer tests

## 15. Troubleshooting Guide
- App won't start: Check DB, property values, logs.
- DB migration fails: Check Flyway logs, DB user, and script syntax.
- 404/401/403: Authentication, authorization problems.
- Review logs for detailed error traces.

## 16. Appendix & Additional Resources
- [Project README.md]
- [Detailed pom.xml]
- [Sample migration scripts]
- [Swagger outputs]
- [Useful links:](https://spring.io/projects/spring-boot), [https://swagger.io/], [https://flywaydb.org/]
