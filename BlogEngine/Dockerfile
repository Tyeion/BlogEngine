# Use Java 17 base image with JDK
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Give executable permission to the Maven wrapper script
RUN chmod +x mvnw

# Build the Spring Boot application (skip tests to speed up)
RUN ./mvnw clean package -DskipTests

# Set the port (optional if already in application.properties)
EXPOSE 8080

# Run the generated JAR file
CMD ["java", "-jar", "target/BlogEngine-0.0.1-SNAPSHOT.jar"]
