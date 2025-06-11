# Use Eclipse Temurin as base image with Java 21
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY ./target/ITI-Graduation-Project-1.0.jar app.jar

# Create directory for uploads (though we'll be using Cloudinary in production)
RUN mkdir -p /app/uploads

# Environment variables with defaults
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
# Command to run the application
ENTRYPOINT ["java", \
           "-Djava.security.egd=file:/dev/./urandom", \
           "-jar", \
           "/app/app.jar"]
