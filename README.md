# Swisscom Tech Assignment - NASA API and Networking Validation

This project is an implementation of network stack validation through **Cucumber** and **RestAssured** for the following tasks:

1. **NASA API Interaction**: Retrieve and validate the Astronomy Picture of the Day (APOD) from the NASA API.
2. **Networking Validation**: Perform networking-related tests such as validating public IP, DNS resolution, and traceroute.

### Project Structure

```
swisscom-tech-assignment-nasa-api-networking/
├── src/
│   ├── main/                # Main code for utility functions and constants
│   ├── test/                # Test code for step definitions and feature files
│   ├── resources/           # Feature files for Cucumber tests
├── target/                  # Directory where the compiled classes and reports will be stored
├── Dockerfile               # Docker file for containerizing the application
├── .dockerignore            # List of files to ignore when building the Docker image
├── pom.xml                  # Maven configuration file
└── README.md                # Project documentation (this file)
```

### Requirements

Before running the tests, make sure you have the following dependencies installed:

- **Java 17 or higher** (since the project uses Java 17)
- **Maven** (for building and running the project)
- **IntelliJ IDEA** (or any other Java IDE of your choice)

### Running Tests in IntelliJ IDEA

To run the tests in IntelliJ IDEA, follow these steps:

1. **Ensure the project is imported correctly**:
    - If you are using IntelliJ IDEA, open the project using the `pom.xml` file.
    - IntelliJ IDEA should automatically recognize the Maven project and download the necessary dependencies.

2. **Run the tests using `TestRunner`**:
    - Open the `TestRunner.java` file located in the `src/test/java/runner/` directory.
    - Modify the `@CucumberOptions` annotation to include the desired tags (if not already configured). For example, to run tests with the tags `@NASA_API` or `@NETWORKING`, ensure the following:

   ```java
   @CucumberOptions(
       features = "src/test/resources/features",  // Path to feature files
       glue = {"steps"},  // Path to step definition classes
       tags = "@NASA_API or @NETWORKING",  // Tags for filtering tests
       plugin = {"pretty", "html:target/cucumber-reports.html"}  // Reporting format
   )
   ```

3. **Running the tests**:
    - Right-click on the `TestRunner.java` class file.
    - Select **Run 'TestRunner'** to start the test execution.

   IntelliJ IDEA will run the tests tagged with either `@NASA_API` or `@NETWORKING`, depending on the configuration.

4. **Viewing the test results**:
    - After the tests are executed, you can check the results in the **Console Output** in IntelliJ IDEA.
    - Additionally, a detailed HTML report will be generated at `target/cucumber-reports.html`. Open this file in your browser to view the test summary.

### Running the Tests in Docker

This project can be containerized using Docker to run the tests in an isolated environment.

#### 1. **Building the Docker Image**

Make sure you have Docker installed on your machine. Then, in the project root directory, run the following command to build the Docker image:

```bash
docker build -t swisscom-tech-assignment-nasa-api-networking .
```

#### 2. **Running the Docker Container**

After the image is built, run the container with the following command:

```bash
docker run --rm swisscom-tech-assignment-nasa-api-networking
```

This will run the tests inside the Docker container. Since the `Dockerfile` is configured to run the Maven tests (`mvn clean install` and `mvn test`), it will execute the tests on container startup.

### DockerHub Instructions

If you'd like to run the project directly from Docker Hub, follow these steps:

1. **Pull the Docker Image from Docker Hub**:
   ```bash
   docker pull vjaceslavsjer/swisscom-tech-assignment-nasa-api-networking
   ```

2. **Run the Docker Image**:
   ```bash
   docker run --rm vjaceslavsjer/swisscom-tech-assignment-nasa-api-networking
   ```

3. **Check the Results**:
   As with the local Docker build, the results will be shown in the console output. You can also map the `target` directory using the `-v` option to access the generated report on your local machine.