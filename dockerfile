# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle build files
COPY build.gradle ./
COPY settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./gradlew
COPY src ./src

# Build application
RUN ./gradlew build -x test

# Expose app port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "build/libs/automacorp-0.0.1-SNAPSHOT.jar"]

