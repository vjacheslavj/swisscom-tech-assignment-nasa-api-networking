# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Install Maven (to build the project)
RUN apt-get update && apt-get install -y maven

# Build the project using Maven
RUN mvn clean install

# Command to run the tests
CMD ["mvn", "test"]