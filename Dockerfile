# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN mkdir -p /app/uploads

# Environment variables
ENV SPRING_PROFILES_ACTIVE=prod \
    CLOUDINARY_CLOUD_NAME=Root \
    CLOUDINARY_API_KEY=775712753115847 \
    CLOUDINARY_API_SECRET=tOPqiiLjaQmCmteCyefaGPHNj3s \
    DATABASE_URL=jdbc:mysql://hopper.proxy.rlwy.net:34464/railway?sslMode=REQUIRED \
    DATABASE_USERNAME=root \
    DATABASE_PASSWORD=MadkKyfLCfPHzraigxlPpgUcLcYsPIYh \
    FRONTEND_URL=https://shoe-show-client.netlify.app \
    ADMIN_URL=https://shoe-show-admin.netlify.app \
    PORT=8080

# Make sure to expose the same port that Railway expects
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=8080", "-jar", "app.jar"]
