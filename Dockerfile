# ============================
# Stage 1: Build the application
# ============================
FROM maven:3.9.3-eclipse-temurin-21 AS build
WORKDIR /app

# Copy source code and pom.xml
COPY . .

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests


# ============================
# Stage 2: Run the application
# ============================
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/ITI-Graduation-Project-1.0.jar app.jar

# Create directory for uploads
RUN mkdir -p /app/uploads

# Environment variables (these should ideally be set in Railway's environment tab)
ENV PORT=8081 \
    SPRING_PROFILES_ACTIVE=prod \
    CLOUDINARY_CLOUD_NAME=Root \
    CLOUDINARY_API_KEY=775712753115847 \
    CLOUDINARY_API_SECRET=tOPqiiLjaQmCmteCyefaGPHNj3s \
    DATABASE_URL=jdbc:mysql://hopper.proxy.rlwy.net:34464/railway?sslMode=REQUIRED \
    DATABASE_USERNAME=root \
    DATABASE_PASSWORD=MadkKyfLCfPHzraigxlPpgUcLcYsPIYh \
    FRONTEND_URL=https://your-frontend-url.com \
    ADMIN_URL=https://your-admin-url.com

# Expose the application port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
