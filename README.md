# ShoeShow E-commerce Web Application

ShoeShow is a comprehensive e-commerce web application for a shoe store, providing a seamless shopping experience for customers and robust management tools for administrators.

## Features

### Customer Features
- **User Authentication** - Secure login and registration system
- **Product Browsing** - Browse through a wide range of shoe products
- **Product Search & Filtering** - Find products by categories, sizes, colors, etc.
- **Shopping Cart** - Add products to cart and manage quantities
- **Wishlist** - Save favorite products for later
- **Order Management** - Place orders and track order status
- **User Profile** - Manage personal information and view order history

### Admin Features
- **User Management** - Manage customer accounts and permissions
- **Product Management** - Add, edit, and remove products
- **Order Management** - Process and track customer orders
- **Inventory Management** - Monitor stock levels and update product availability

## Technologies Used

### Backend
- **Java 21**
- **Spring Boot 3.4.5**
- **Spring Security** - For authentication and authorization
- **JWT** - For secure API authentication
- **Spring Data JPA** - For database access
- **MySQL** - Database
- **Flyway** - For database migrations
- **MapStruct** - For object mapping
- **Lombok** - For reducing boilerplate code
- **SpringDoc OpenAPI** - For API documentation

### Testing
- **JUnit**
- **TestContainers**
- **H2 Database** - For testing

## Installation and Setup

### Prerequisites
- Java 21 or higher
- Maven
- MySQL 8.0 or higher

### Steps
1. **Clone the repository**
   ```
   git clone https://github.com/Hashim-Sobhi/ShoeShow-E-commerce-Web-Application.git
   cd ShoeShow-E-commerce-Web-Application
   ```

2. **Configure the database**
   - Create a MySQL database (default name: `iti_grad`)
   - Update database credentials in `src\main\resources\application.properties` if needed:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/iti_grad?createDatabaseIfNotExist=true
     spring.datasource.username=root
     spring.datasource.password=root
     ```

3. **Build the application**
   ```
   mvn clean install
   ```

4. **Run the application**
   ```
   mvn spring-boot:run
   ```

   The application will be available at `http://localhost:8081`

## API Documentation

The API documentation is available via Swagger UI at:
```
http://localhost:8081/swagger-ui.html
```

## Usage

### Customer Portal
- Register a new account or login with existing credentials
- Browse products by categories
- Add products to cart or wishlist
- Checkout and place orders
- View order history and track current orders

### Admin Portal
- Login with admin credentials
- Manage products (add, edit, delete)
- Process customer orders
- Manage user accounts

## Contributors

This project was developed as a graduation project at the Information Technology Institute (ITI) by:

- [Ahmed Elbrmbaly](https://github.com/ahmedelbrmbaly)
- [Hashim Sobhi](https://github.com/Hashim-Sobhi)
- [Ibrahim Diab](https://github.com/ibrahim0diab)
- [Nada Mohammed](https://github.com/nadamohammed01123)
- [Yusuf Salah ElDin](https://github.com/Yusuf-Salah-ElDin)

## License

This project is licensed under the MIT License - see the LICENSE file for details.
