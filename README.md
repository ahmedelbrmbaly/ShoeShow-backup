# ShoeShow E-commerce Web Application Backend

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21-blue)](https://adoptopenjdk.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen)](https://spring.io/projects/spring-boot)
[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/ahmedelbrmbaly/ShoeShow-backup)

---

## 🛍️ Overview
ShoeShow is a modern, full-featured e-commerce backend for managing products, users, orders, and more. Built with Spring Boot, it provides robust REST APIs, secure authentication, and seamless database migrations.

---

## ✨ Features
- User authentication & JWT-based security
- Product catalog with images and sizes
- Shopping cart & wishlist management
- Order processing & admin dashboard
- RESTful API with OpenAPI/Swagger docs
- Database migrations with Flyway
- File upload support for product images
- Logging and monitoring (Spring Actuator)

---

## 🛠️ Tech Stack
- **Java 21**
- **Spring Boot 3.4.5**
- **Spring Data JPA**
- **Spring Security**
- **JWT (io.jsonwebtoken)**
- **MySQL**
- **Flyway** (DB migrations)
- **MapStruct** (DTO mapping)
- **Lombok**
- **OpenAPI/Swagger** (API docs)
- **JUnit/Testcontainers** (testing)

---

## 📁 Project Structure
```
backend/
├── src/main/java/iti/jets/
│   ├── controllers/         # REST controllers (user, admin, auth, product, order, wishlist, cart)
│   ├── model/entities/      # JPA entities (Product, User, Order, etc.)
│   ├── repositories/        # Spring Data JPA repositories
│   ├── services/            # Business logic
│   └── ...
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/        # Flyway migration scripts
├── uploads/                 # Product images
├── logs/                    # Application logs
├── pom.xml                  # Maven build file
└── README.md
```

---

## 🚀 Getting Started
### Prerequisites
- Java 21+
- Maven 3.8+
- MySQL 8+

### Setup & Run
```bash
# Clone the repo
$ git clone <repo-url>
$ cd backend

# Configure DB in src/main/resources/application.properties
# (default: root/root, db: iti_grad)

# Run migrations & start server
$ mvn clean spring-boot:run
```

### API Documentation
- Swagger UI: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- OpenAPI Docs: [http://localhost:8081/api-docs](http://localhost:8081/api-docs)

---

## 🗄️ Database & Migrations
- MySQL database: `iti_grad`
- Flyway migration scripts: `src/main/resources/db/migration/`
- Sample data: `script_data.sql`, `db_script.sql`

---

## 🧪 Testing
```bash
$ mvn test
```
- Uses JUnit, Testcontainers, and H2 for integration tests.

---

## 📦 Deployment
- Build JAR: `mvn clean package`
- Run: `java -jar target/ITI-Graduation-Project-1.0.jar`

---

## 🤝 Contributing
Pull requests are welcome! For major changes, please open an issue first.

---

## 📚 More Info
- [DeepWiki Project Wiki](https://deepwiki.com/ahmedelbrmbaly/ShoeShow-backup)

---

## 📝 License
This project is licensed under the MIT License.

