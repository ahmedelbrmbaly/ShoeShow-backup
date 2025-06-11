# Stage 1: Build the application
FROM maven:3.9.3-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN mkdir -p /app/uploads

# Environment variables
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

EXPOSE 8081

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
