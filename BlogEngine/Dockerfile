# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file built by Maven
COPY target/*.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
