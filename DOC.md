# Backend Project Documentation
## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Packages and Layers](#packages-and-layers)
- [Controllers & Endpoints](#controllers--endpoints)
    - [Authentication](#authentication)
    - [Products](#products)
    - [User Management](#user-management)
    - [Cart](#cart)
    - [Orders](#orders)
    - [Wishlist](#wishlist)
    - [Admin APIs](#admin-apis-if-present)

- [Security Scheme](#security-scheme)
    - [Global Security](#global-security)
    - [Endpoint Security Table](#endpoint-security-table)

- [Entities (Model Layer)](#entities-model-layer)
- [Repositories](#repositories)
- [Services](#services)
- [Spring Configuration](#spring-configuration)
- [API Documentation](#api-documentation)
- [How to Run](#how-to-run)
- [Environment Variables](#environment-variables)
- [Development Hints](#development-hints)
- [Conventions](#conventions)
- [Further Improvement Ideas](#further-improvement-ideas)

## Overview
This project is a Jakarta EE backend using Spring Data JPA, Spring MVC, and Lombok. It supports JWT-based authentication, user and product management, order processing, cart and wishlist features, and provides REST APIs secured where appropriate.
## Architecture
- **Multi-layered:** Follows standard controller-service-repository-entity pattern.
- **Security:** Uses Spring Security with JWT (Bearer) authentication and method-level authorization.
- **Persistence:** Uses Spring Data JPA repositories.
- **Documentation:** Swagger/OpenAPI integration for testable and discoverable endpoints.
- **DTOs:** Used for request/response shaping.

## Packages and Layers

| Package/Layers | Contents |
| --- | --- |
| `controllers` | REST controllers (Auth, User, Product, etc.) |
| `services` | Business logic/services |
| `repositories` | Spring Data JPA repositories |
| `model/entities` | JPA entity classes |
| `model/dto` | Data Transfer Objects for API requests/responses |
| `config` | Security, Swagger, and other Spring configs |
## Controllers & Endpoints
### Authentication
- **`POST /api/auth/login`**
  Authenticates user and returns JWT.
- **`POST /api/auth/register`**
  Registers new user and (optionally) returns JWT.

### Products
- **`GET /api/products`**
  List all products. (Public)
- **`GET /api/products/{productId}`**
  Get product details. (Public)

### User Management
- **`GET /api/users/{userId}`**
  Get user profile (self only).
  **Security:** Requires bearer token, userId must match JWT.
- **`PUT /api/users/{userId}`**
  Update user profile (self only).
  **Security:** Requires bearer token, userId must match JWT.

### Cart
- **`GET /api/users/{userId}/cart`**
  Get cart contents for user (self only).
  **Security:** Bearer token, userId must match JWT.
- **`POST /api/users/{userId}/cart`**
  Add to cart (self only).
  **Security:** Bearer token, userId must match JWT.
- **`DELETE /api/users/{userId}/cart`**
  Remove from cart (self only).
  **Security:** Bearer token, userId must match JWT.

### Orders
- **`GET /api/users/{userId}/orders`**
  List orders for user (self only).
  **Security:** Bearer token, userId must match JWT.
- **`POST /api/users/{userId}/orders`**
  Place an order (self only).
  **Security:** Bearer token, userId must match JWT.

### Wishlist
- **`GET /api/users/{userId}/wishlist`**
  View wishlist (self only).
  **Security:** Bearer token, userId must match JWT.
- **`POST /api/users/{userId}/wishlist`**
  Add to wishlist (self only).
  **Security:** Bearer token, userId must match JWT.
- **`DELETE /api/users/{userId}/wishlist`**
  Remove from wishlist (self only).
  **Security:** Bearer token, userId must match JWT.

### Admin APIs (if present)
- **Typical operations:** Manage users and products (e.g., ban users, add/remove products).
- **Security:** Usually require admin role, protected via `@PreAuthorize("hasRole('ADMIN')")`.

## Security Scheme
### Global Security
- **Scheme:** Bearer authentication (JWT tokens)
- **Annotation:**
    - on major secured controllers. `@SecurityRequirement(name = "bearerAuth")`
    - for "self only" endpoints. `@PreAuthorize("#userId == principal.user.userId")`
    - Some admin endpoints may check for specific roles.

**Token Extraction and Validation:**
Spring Security filter extracts the token from `Authorization: Bearer <jwt>`, checks its validity, and sets the authenticated principal.
### Endpoint Security Table

| Endpoint | Level | Security/Annotation |
| --- | --- | --- |
| `/api/products/**` | Public | None |
| `/api/auth/**` | Public | None |
| `/api/users/{userId}` | Private (self only) | Bearer JWT + `@PreAuthorize` |
| `/api/users/{userId}/cart/**` | Private (self only) | Bearer JWT + `@PreAuthorize` |
| `/api/users/{userId}/orders/**` | Private (self only) | Bearer JWT + `@PreAuthorize` |
| `/api/users/{userId}/wishlist/**` | Private (self only) | Bearer JWT + `@PreAuthorize` |
| `/api/admin/**` (if present) | Admin only | Bearer JWT + `@PreAuthorize("hasRole('ADMIN')")` |
## Entities (Model Layer)
- **User**: User account information, roles, profile.
- **Product**: Product catalog entries.
- **Order**: Purchase orders, contain order lines.
- **Cart/Wishlist**: Transient or persistent collection entities for users.
- **Others**: As needed for your business.

_(Entities are usually annotated with `@Entity` and mapped with JPA annotations in the `model/entities` package.)_
## Repositories
- Located in the `repositories` package.
- Extend or . `JpaRepository``CrudRepository`
- Typical examples:
    - `UserRepository`
    - `ProductRepository`
    - `OrderRepository`

## Services
- Located in the package. `services`
- Contain main business logic and orchestrate repository usage.

## Spring Configuration
- **Security:** In `SecurityConfig` class.
    - Configures authentication filters, endpoint security, and password encoding.

- **Swagger/OpenAPI:** In a configuration class or with annotations for documentation.

## API Documentation
- Accessible via `/swagger-ui.html` or . `/swagger-ui/index.html`
- If not public, requires authentication for testing secured endpoints.

## How to Run
1. **Clone the repository**
2. **Set environment variables** (see below)
3. **Run database and seed scripts:**
    - (schema) `db_script.sql`
    - (init data) `script_data.sql`

4. **Run the Spring Boot application:**
    - In IDE: Run `Application` class.
    - Or via Maven: `mvn spring-boot:run`

5. **Browse Swagger UI:** `http://localhost:8080/swagger-ui.html`

## Environment Variables
- `DB_URL`, , `DB_PASSWORD`: For database access. `DB_USER`
- `JWT_SECRET`: Used to sign and verify JWT tokens.
- (Others as configured in ) `application.properties`

## Development Hints
- Use Lombok for boilerplate (`@Data`, `@AllArgsConstructor`, etc.)
- Use for logging. `@Slf4j`
- Use DTOs for all inputs/outputs in controllers.
- Validate inputs with Jakarta Bean Validation (e.g., `@NotNull`, `@Email`).

## Conventions
- REST endpoints follow standard naming and HTTP verbs.
- IDs appear in paths for user-scoped endpoints.
- Error handling via custom exceptions or `@ControllerAdvice`.

## Further Improvement Ideas
- **Add tests:** Unit and integration.
- **Rate limiting:** On login/auth, writes, or sensitive endpoints.
- **Role-based access control:** More granular permissions.
- **Refresh tokens:** For session longevity.
- **Email notifications/integration.**
