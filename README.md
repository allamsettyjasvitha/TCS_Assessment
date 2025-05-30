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
