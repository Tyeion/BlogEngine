# Optionally add these improvements:
# Stage 1: Build
FROM maven:3.8.7-jdk-17 AS build
WORKDIR /app
# Copy just the POM first for better layer caching
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre-jammy  # Using JRE instead of JDK for production
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Add non-root user for security
RUN useradd -m myuser && chown -R myuser:myuser /app
USER myuser
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]