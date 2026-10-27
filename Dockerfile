
# Use a Java 17 runtime image to run the Spring Boot application.
FROM eclipse-temurin:17-jre

# Set the working directory inside the container.
WORKDIR /app

# Copy the packaged application JAR into the image.
COPY target/campus-equipment-booking-1.0.0.jar app.jar

# Document the port used by the Spring Boot application.
EXPOSE 8080

# Start the application when the container launches.
ENTRYPOINT ["java", "-jar", "app.jar"]
