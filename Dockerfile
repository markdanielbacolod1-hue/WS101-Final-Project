# Use Java 17 (Spring Boot 3 requires Java 17+)
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies first (faster builds)
RUN ./mvnw dependency:go-offline

# Copy the rest of the project
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/*.jar"]
