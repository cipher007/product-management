# Product Management - SpringBoot REST Application

## Overview

The sample application demonstrates microservices using REST API with Swagger UI allows you to describe the structure of your APIs so that machines can read them. The ability of APIs to describe their own structure

> This application took proximately 3 hours, many components can be added and enhanced for better endpoint documentation, unit test, and probably create relational tables, for sake of demonstration.
<ul>Also javadoc needs many work!</ul>

## Project Structure

#### A typical top-level directory layout

to be included

## API

> - Access the UI Application API on the address: [http://localhost:8080/swagger-ui.html#/Product](http://localhost:8080/swagger-ui.html#/Product)
> - This page documents the entire application API
> - You can also interact with Application API UI to request the endpoints
> - There is a call sample for each endpoint like the images below

## Run Tests and Application

- Build and compile application: ```mvn clean install```

- Run all tests: ```mvn test```

- Run application on Spring Boot: ```mvn spring-boot:run```

## Database

> - This application uses a memory database.  and the
> - The chosen database is: **H2**
> - UI database can be access [here](http://localhost:8080/h2/login.do)

## Test

We have a Product Entity represents the product table

> ! not enough time to apply more test scenarios

##### 1º Build a Restful service using JAX-RS to perform CRUD operations of Product

##### 2º Your API classes should perform these operations

1. Create, update and delete products
2. Get all products
3. Get products using specific product identity

##### 3º Build JPA/Hibernate classes using annotations to persist these objects in the database

###### Technical Specification

1. Maven must be used to build, run tests and start the application.
2. The tests starts with the mvn test command.
3. The application starts with a Maven command: mvn exec:java, mvn jetty:run, mvn spring-boot:run, etc.
4. The application has a stateless API and use a database to store data.
5. An embedded in-memory database should be used: H2 in memory.
6. The database and tables creation should be done by Maven or by the application.
7. README.txt (plain text) or a README.md (Markdown) file at the root of your repository, explaining:
    - How to compile and run the application with an example for each call.
    - How to run the suite of automated tests.
    - Mention anything that was asked but not delivered and why, and any additional comments.
