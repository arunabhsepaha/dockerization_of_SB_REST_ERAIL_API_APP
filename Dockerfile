# Use an official OpenJDK runtime as a parent image
FROM  openjdk:11

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . /app


# Build the application with Maven, skipping tests during the build
RUN ./mvnw clean package -DskipTests

# Set the command to run the Spring Boot application
CMD ["java", "-jar", "target/13_SB_REST_ERAIL_API_1-0.0.1-SNAPSHOT.jar"]

