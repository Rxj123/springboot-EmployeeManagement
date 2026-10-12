# Employee Management REST API (Spring Boot)

A RESTful API for managing employee records, built with **Spring Boot**. It supports full CRUD operations (Create, Read, Update, Delete) with input validation and an in-memory H2 database, so it runs with zero setup.

The project follows a layered architecture: **Controller → Service → Repository → Database**.

## Features

- Create, read, update and delete employees
- Fetch a single employee by ID, with a proper `404 Not Found` when it doesn't exist
- Request validation (first name and last name must not be blank, email must be valid)
- Service layer defined as an interface with a separate implementation
- In-memory H2 database (no installation needed)
- JSON request and response bodies

## Tech Stack

| Area | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot |
| Web | Spring Web MVC |
| Persistence | Spring Data JPA (Hibernate) |
| Validation | Jakarta Bean Validation |
| Database | H2 (in-memory) |
| Build tool | Maven |

## Project Structure

```
employee-management/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/employee/
    │   │   ├── Application.java               # Entry point (@SpringBootApplication)
    │   │   ├── model/Employee.java            # JPA entity with validation rules
    │   │   ├── repository/EmployeeRepository.java
    │   │   ├── service/EmployeeService.java   # Service contract (interface)
    │   │   ├── service/EmployeeServiceImpl.java   # Business logic
    │   │   └── controller/EmployeeController.java # REST endpoints
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/example/employee/
```

> Adjust the package name (`com.example.employee`) to match your own Group and Artifact.

## Prerequisites

- JDK 21 (or the version set in your `pom.xml`)
- Maven 3.9.x (or use the included Maven wrapper)
- Git

Make sure your `pom.xml` includes these dependencies:

- `spring-boot-starter-web` (or `spring-boot-starter-webmvc` on newer Spring Boot versions)
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-validation` (required for `@NotBlank`, `@Email` and `@Valid` to work)
- `h2` (runtime scope)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>
```

### 2. Run the application

Using the Maven wrapper:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# macOS / Linux
./mvnw spring-boot:run
```

Or run the `Application` class directly from your IDE.

### 3. Call the API

The API is available at <http://localhost:8080/employees>.

> **Port conflict?** If port 8080 is already used by another program (for example Oracle Database XE), add this line to `src/main/resources/application.properties` and use the new port in all URLs:
>
> ```properties
> server.port=8081
> ```

## API Reference

Base URL: `/employees`

| Method | Endpoint | Description | Success response |
|---|---|---|---|
| `GET` | `/employees` | Get all employees | `200 OK` + JSON list |
| `GET` | `/employees/{id}` | Get one employee by ID | `200 OK` or `404 Not Found` |
| `POST` | `/employees` | Create an employee | Created employee as JSON |
| `PUT` | `/employees/{id}` | Update an existing employee | `200 OK` or `404 Not Found` |
| `DELETE` | `/employees/{id}` | Delete an employee | Plain-text confirmation message |

### Example requests

Create an employee:

```bash
curl -X POST http://localhost:8080/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName": "Asha", "lastName": "Rao", "email": "asha.rao@example.com"}'
```

Get all employees:

```bash
curl http://localhost:8080/employees
```

Get one employee:

```bash
curl http://localhost:8080/employees/1
```

Update an employee:

```bash
curl -X PUT http://localhost:8080/employees/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName": "Asha", "lastName": "Rao", "email": "asha.new@example.com"}'
```

Delete an employee:

```bash
curl -X DELETE http://localhost:8080/employees/1
```

### Example response

```json
{
  "id": 1,
  "firstName": "Asha",
  "lastName": "Rao",
  "email": "asha.rao@example.com"
}
```

## Data Model

**Employee** (table: `employees`)

| Field | Type | Rules |
|---|---|---|
| `id` | `Long` | Auto-generated primary key |
| `firstName` | `String` | Required, must not be blank |
| `lastName` | `String` | Required, must not be blank |
| `email` | `String` | Must be a valid email format |

## Validation

Requests to `POST /employees` and `PUT /employees/{id}` are validated with `@Valid`. Sending a blank first or last name, or a malformed email, is rejected by Spring with a `400 Bad Request`.

## H2 Console (Optional)

To inspect the in-memory database in your browser, add this to `application.properties`:

```properties
spring.h2.console.enabled=true
```

Then open <http://localhost:8080/h2-console>. The JDBC URL is printed in the startup log (look for `jdbc:h2:mem:...`).

> **Note:** H2 is in-memory, so all data is lost when the application stops.

## Running Tests

```bash
./mvnw test
```

## Building a Runnable JAR

```bash
./mvnw clean package
java -jar target/<artifact-name>-0.0.1-SNAPSHOT.jar
```


## Acknowledgements

Based on the article [Building a Robust REST API with Spring Boot (Employee Management)](https://medium.com/@mouhssinazelmat99/building-a-robust-rest-api-with-spring-boot-employee-management-8afbd788b42f) by Mouhcine Azelmat.


## Author

**Your Name**
GitHub: [@your-username](https://github.com/your-username)
