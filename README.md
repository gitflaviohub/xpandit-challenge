# Xpandit Challenge

Welcome to the Xpandit Challenge repository. This project aims to solve a set of proposed technical tasks to showcase my skills and knowledge.

## Overview

This repository contains the implementation of a RESTful API for managing movies. The API provides endpoints for creating, retrieving, updating, and deleting movies. Additionally, the project includes unit tests and integration tests to ensure the correctness of the implementation.

## Technologies Used

- Java
- Spring Boot
- JPA (Java Persistence API)
- H2 Database
- JUnit
- Mockito
- Swagger-ui

## Project Structure

The project is structured as follows:

- `src/main/java`: Contains the main application code.
  - `com.xpandit.movie_service`: Main package for the movie service application.
    - `controller`: Contains the REST controllers.
    - `domain.model`: Contains the entity classes.
    - `repository`: Contains the JPA repositories.
    - `service`: Contains the service classes.
- `src/test/java`: Contains the test code.
  - `com.xpandit.movie_service`: Main package for the test cases.
    - `test.controller`: Contains the tests for the REST controllers.

## Features

- Full CRUD for the "Movie" resource.
- Filtering movies by release date.
- Insert multiple objects in the same POST request.
- Delete all records in the database.
- Attribute validation to ensure that the `rank` is between 0 and 10.

## Endpoints

The API exposes the following endpoints:

- `GET /movie`: Retrieve all movies.
- `GET /movie/{id}`: Retrieve a movie by its ID.
- `GET /movie/filter`: Returns movies filtered by release date. Example: http://localhost:8080/api/movie/filter?launchDate=2016-12-13
- `POST /movie`: Create a new movie.
- `PUT /movie/{id}`: Update an existing movie by its ID.
- `DEL /movie/{id}`: Delete a movie by its ID.
- `DEL /movie/all`: Delete all movies.
- `POST /movie/collection`: Creates one or many movies, you can indicate a JSON with one or many objects.
- 

## Running the Project

To run the project, follow these steps:

1. Clone the repository: `git clone https://github.com/gitflaviohub/xpandit-challenge.git`
2. Navigate to the project directory: `cd xpandit-challenge`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. The API will be available at `http://localhost:8080`

## Testing

To run the tests, use the following command:

```bash
mvn test
