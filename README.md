# Dockerized Spring Boot App with Docker Compose

This repository contains a Docker Compose setup for dockerizing a Spring Boot application that interacts with a MySQL database. The Docker Compose configuration consists of two services: `mysql` and `springboot-app`.

## Prerequisites

Before you begin, make sure you have the following installed:

- Docker: [Install Docker](https://docs.docker.com/get-docker/)
- Docker Compose: [Install Docker Compose](https://docs.docker.com/compose/install/)

## Getting Started

To get the Spring Boot application up and running in a Docker container along with a MySQL database, follow these steps:

1. Clone this repository:

   ```bash
   git clone https://github.com/arunabhsepaha/dockerization_of_SB_REST_ERAIL_API_APP.git

2. Navigate to the cloned directory:
   ```bash
   cd SB_REST_ERAIL_API

3. Build and start the Docker containers using Docker Compose:
   ```bash
   docker-compose up -d

4. This command will build the Docker images and start the containers in the background.

Access the Spring Boot application:

Once the application and database containers are up and running, you can access the application through the following URL:

Spring Boot Application: http://localhost:8080
Additionally, you can access a specific API endpoint as an example:

Sample API Endpoint: http://localhost:8080/tickets
Replace localhost with the appropriate IP address if you are accessing the application from a remote server.


## Configuration

### `docker-compose.yml`

- Defines two services: `mysql` and `springboot-app`.
- `mysql` service runs a MySQL database container.
- `springboot-app` service builds a Docker image using `Dockerfile`, and sets up environment variables for database connectivity.
- The services are connected through the `springboot-network` bridge network.
- MySQL container exposes port 3306 for communication and uses a volume named `mysql_data` for data persistence.

### `Dockerfile`

- Uses the official OpenJDK 11 image as a base.
- Sets the working directory within the container to `/app`.
- Copies project files into the container.
- Builds the Spring Boot application using Maven with tests skipped (`-DskipTests`).
- Defines the command to run the Spring Boot application.

### `Dockerfile.test`

- Uses the official Maven 3.8.4 image with OpenJDK 11 as a base.
- Sets the working directory within the container to `/app`.
- Copies project files into the container.
- Builds the application with Maven for running tests, skipping the tests.
- Specifies the command to run tests using Maven.

### `application.yaml`

- Defines configuration properties for the Spring Boot application, including the database connection settings.
- The values for database URL, username, and password are passed through environment variables from the Docker Compose configuration.

## Dependencies and Test Skipping

- Both `springboot-app` and `springboot-test` services depend on the `mysql` service using `depends_on`. This ensures that the database container is up before the Spring Boot application starts.
- Tests are skipped during the Maven build process (`-DskipTests`) in `Dockerfile` and `Dockerfile.test` due to potential issues with the database not being ready during the build, which could lead to test failures.

## Customization

If you need to customize the application further or make changes to the Docker configuration, you can modify the appropriate files in this repository.

## Cleanup

To stop and remove the Docker containers, network, and volumes, run the following command:

```bash
docker-compose down

