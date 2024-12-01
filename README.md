# Java Spring Boot Crop Monitoring System Backend (Green Shadow)

## API Documentation
The full API documentation is available via Postman. It includes details on all endpoints, request/response formats, and status codes. ⬇️

https://documenter.getpostman.com/view/36189309/2sAYBYfVNf


## Frontend Repository
https://github.com/KusalDemo/green-shadow-frontend.git

---

## Description
**This project** is developed using **Java Spring Boot** for the backend, **MySQL** as the database, and **Hibernate** for **ORM (Object-Relational Mapping)**. The system includes **role-based access control (RBAC)** with roles such as **ADMINISTRATIVE**, **MANAGER**, and **SCIENTIST**. It uses **JWT-based authentication** for secure login and token refresh mechanisms. **AJAX** (or **Fetch API**) is employed for dynamic, asynchronous communication between the client and server. **Hibernate JPQL** is used to facilitate efficient database operations. Proper **logging** is implemented to ensure maintainability and traceability, adhering to **industry best practices**.


---

## Tech Stack
- **Java-Spring Boot** - A modern and lightweight framework for building Java-based enterprise applications.
- **Hibernate** - An ORM framework for simplifying database operations by mapping Java objects to database tables.
- **MySQL** - A reliable relational database management system for data storage.
- **AJAX/Fetch API** - For dynamic updates to web pages without full-page reloads.
- **JWT** - JSON Web Tokens for secure authentication and authorization.

---

## Features
- **Spring Boot Backend** - Simplifies configuration and development of the backend system.
- **Role-Based Access Control** - Secure access management for different user roles (`ADMINISTRATIVE`, `MANAGER`, `SCIENTIST`).
- **JWT Authentication** - Secure sign-in, sign-up, and token refresh functionalities.
- **Hibernate ORM** - Streamlined database operations with JPQL for efficient query handling.
- **AJAX/Fetch API** - Enables dynamic content loading for a seamless user experience.
- **Comprehensive Logging** - Provides detailed logging for debugging, monitoring, and maintenance.

---

## Setup & Installation

### 1. Clone the Repository
bash
git clone https://github.com/KusalDemo/green-shadow-backend.git


### 2. Configure the Database

- *Step 1*: Set up your MySQL database.
  - Create a database for the system. For example
    sql -
    `CREATE DATABASE greenShadowDB;`


- *Step 2*: Update the database connection details in the `application.properties` file
  properties
  # MySQL Database Configuration
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/greenShadowDB?createDatabaseIfNotExist=true
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  ```

  # Hibernate Configuration
  ```
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.generate_statistics=true
  ```


- *Step 3*: Run the application to auto-generate tables.
  - If you are using Hibernate with the `spring.jpa.hibernate.ddl-auto=update` setting, the necessary database schema (tables, constraints) will be generated automatically when the application starts for the first time.

---

With this version, all the necessary code snippets and SQL commands are included for database configuration, making the steps clearer and more actionable.


---

## Logging Configuration

text
The system uses a robust logging strategy with appropriate levels:

- `INFO` - For general application flow and milestones.
- `DEBUG` - For detailed debugging and tracking variable states.
- `ERROR` - For error events that may affect the system's stability.
- `WARN` - For potentially harmful situations or system anomalies.

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## Contact

For any inquiries or suggestions, please contact,

**Name** - Kusal Gunasekara

**Email** - kusalgunasekara2002@gmail.com