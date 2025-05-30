# Customer Management API – Spring Boot Coding Challenge

## Overview

This project is a RESTful API built with Spring Boot to manage customer data. It supports standard CRUD operations and includes dynamic membership tier calculation based on `annualSpend` and `lastPurchaseDate`. The application is built using Maven and uses an in-memory H2 database for ease of testing and demonstration.

## Features

- Create, retrieve, update, and delete customer records
- Auto-generation of UUIDs for customer IDs
- Dynamic membership tier calculation (Silver, Gold, Platinum)
- Email format validation
- H2 in-memory database
- OpenAPI documentation via Swagger
- Unit tests for CRUD, validation, and business logic

## Tech Stack

- Java 11+
- Spring Boot
- Maven
- H2 Database
- JUnit / Mockito
- Swagger/OpenAPI

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Maven 3.6+

### Build & Run the Application

```bash
# Clone the repository
git clone <your-repo-url>
cd <project-directory>

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```
## API Documentation
Swagger UI is available at:
```
http://localhost:8080/swagger-ui/index.html
```
This includes all available endpoints, request/response schemas, and error examples.

## Accessing H2 Console

You can access the H2 database console at:
```
http://localhost:8080/h2-console
```

## Configuration:

JDBC URL: jdbc:h2:mem:testdb

- Username: sa
- Password: (leave blank)

## API Endpoints

Method	Endpoint	Description
- POST	/customers	Create a new customer
- GET	/customers/{id}	Get customer by ID
- GET	/customers?name=	Get customer by name
- GET	/customers?email=	Get customer by email
- PUT	/customers/{id}	Update existing customer
- DELETE	/customers/{id}	Delete a customer

## Membership Tier Rules
- Silver: annualSpend < 1000
- Gold: 1000 ≤ annualSpend < 10000 and purchase within last 12 months
- Platinum: annualSpend ≥ 10000 and purchase within last 6 months
Note: Tier is calculated at runtime and is not stored in the database.

## Unit Testing
Run the tests using:

```
mvn test
```
Test coverage includes:
CRUD operations
Tier calculation logic
Email validation

## Assumptions
- The id field is auto-generated and not provided during customer creation.
- Only name and email are mandatory fields.
- lastPurchaseDate is in ISO 8601 format.
- Tier is computed dynamically and returned only in GET responses.
