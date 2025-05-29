# ShoeShow E-commerce Web Application - Project Handover

## Project Overview
ShoeShow is an e-commerce web application for selling shoes. It's built using Spring Boot and follows a layered architecture pattern. The application is currently in an early stage of development, with the basic structure and data model in place, but many features still need to be implemented.

## Technology Stack
- **Java 21**: Programming language
- **Spring Boot 3.4.5**: Application framework
- **Spring Data JPA**: Data access layer
- **MySQL**: Database
- **Flyway**: Database migration
- **MapStruct**: Object mapping
- **Lombok**: Boilerplate code reduction
- **Spring Boot Test**: Testing framework

## Project Structure
The project follows a standard Spring Boot application structure:

```
src/
├── main/
│   ├── java/
│   │   └── iti/
│   │       └── jets/
│   │           ├── controllers/     # REST controllers
│   │           ├── exceptions/      # Custom exceptions and error handling
│   │           ├── model/
│   │           │   ├── dtos/        # Data Transfer Objects
│   │           │   ├── entities/    # JPA entities
│   │           │   ├── enums/       # Enumeration types
│   │           │   └── mappers/     # MapStruct mappers
│   │           ├── repositories/    # Spring Data JPA repositories
│   │           ├── services/        # Business logic services
│   │           └── WebApplication.java  # Main application class
│   └── resources/
│       ├── db/
│       │   └── migration/          # Flyway migration scripts
│       ├── static/                 # Static resources (CSS, JS, images)
│       └── application.properties  # Application configuration
└── test/
    └── java/
        └── iti/
            └── jets/               # Test classes
```

## Architecture
The application follows a layered architecture:

1. **Presentation Layer (Controllers)**: Handles HTTP requests and responses
2. **Business Logic Layer (Services)**: Implements business logic
3. **Data Access Layer (Repositories)**: Interacts with the database
4. **Domain Layer (Entities, DTOs)**: Represents the domain model

## Key Components

### Entities
The application has the following main entities:

1. **Product**: Represents a shoe product with attributes like name, description, category, gender, price, brand, etc.
2. **ProductInfo**: Represents a specific variation of a product (size, color, quantity)
3. **ProductImg**: Stores product images
4. **User**: Represents a customer with attributes like name, email, password, etc.
5. **UserAddress**: Stores user addresses
6. **ShoppingCart**: Represents items in a user's shopping cart
7. **Order**: Represents a customer order
8. **OrderItem**: Represents items in an order
9. **Wishlist**: Represents items in a user's wishlist

### DTOs (Data Transfer Objects)
DTOs are used to transfer data between layers and to the client. Key DTOs include:

1. **ProductInfoDTO**: For product variation data
2. **UserDTO**: For user data
3. **ProductCreateDTO**: For creating new products
4. **ProductDetailDTO**: For detailed product information
5. **ShoppingCartDTO**: For shopping cart data

### Mappers
MapStruct is used for mapping between entities and DTOs. Key mappers include:

1. **ProductInfoMapper**: Maps between ProductInfo entity and related DTOs
2. **SizeMapper**: Maps between Size entity and ShoeSize enum

### Controllers
Currently, only one controller is implemented:

1. **ProductController**: Handles product-related HTTP requests

### Services
The application has the following services:

1. **ProductService**: Handles product-related business logic
2. **CartService**: Handles shopping cart operations
3. **OrderService**: Handles order processing
4. **ProfileService**: Handles user profile management
5. **UserService**: Handles user management
6. **WishlistService**: Handles wishlist operations

### Repositories
The application has the following repositories:

1. **ProductRepo**: For product data access
2. **AddressRepo**: For user address data access
3. **CartRep**: For shopping cart data access
4. **OrderRep**: For order data access
5. **UserRepo**: For user data access
6. **WishListRepo**: For wishlist data access

### Exception Handling
The application has a comprehensive exception handling mechanism:

1. **GlobalExceptionHandler**: Handles exceptions globally and returns consistent error responses
2. **Custom Exceptions**:
   - BadRequestException: For invalid or malformed requests
   - ConflictException: For conflicts in data operations
   - ForbiddenException: For access denied scenarios
   - ResourceNotFoundException: For when a requested resource is not found
   - UnauthorizedException: For authentication failures
   - ValidationException: For validation errors

### Database
The database schema is defined in `db_script.sql` and includes tables for all the main entities. Flyway is used for database migrations, with migration scripts located in `src/main/resources/db/migration`.

## Configuration
The application is configured in `application.properties`:

- Server port: 8080
- Database: MySQL at localhost:3306/iti_grad
- Flyway enabled for database migrations
- Logging configured to write to logs/app.log

## Development Status
The application is in an early stage of development:

- The data model and basic structure are in place
- Many services and controllers are not fully implemented
- Only one controller (ProductController) exists, and its methods are not fully implemented

## Next Steps
To continue development, focus on:

1. Implementing the remaining controller methods
2. Completing the service implementations
3. Adding authentication and authorization
4. Implementing the frontend (currently only static resources are present)
5. Adding tests for all components
6. Enhancing documentation

## Running the Application
1. Ensure MySQL is running on localhost:3306
2. Create a database named `iti_grad` (or let the application create it)
3. Run the application using `mvn spring-boot:run` or by running the `WebApplication` class
4. The application will be available at http://localhost:8080