# syntax=docker/dockerfile:1

# Use the JDK for building the application
FROM eclipse-temurin:21-jdk-jammy AS builsder

# Set the working directory
WORKDIR /build

# Copy the Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn/ .mvn/
COPY pom.xml .

# Copy the application source code
COPY src ./src

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build the application
RUN ./mvnw package -DskipTests

# Use the JRE to run the application
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=prod

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
