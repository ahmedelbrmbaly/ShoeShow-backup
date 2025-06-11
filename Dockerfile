# ---------- Stage 1: Build the application ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the application ----------
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Debug: List files to ensure app.jar is present
RUN ls -l /app

# Create the uploads directory (ephemeral on Railway)
RUN mkdir -p /app/uploads

# Expose port 8080 (used by Spring Boot and expected by Railway)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=8080", "-jar", "app.jar"]
