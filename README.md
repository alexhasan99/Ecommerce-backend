# E-commerce Backend

This repository contains the backend microservices for an e-commerce application. Each service is designed to handle a specific domain of the application and is built using **Spring Boot**.

---

## Microservices

1. **Cart Service**: Manages user shopping carts and their contents.
2. **Order Service**: Handles order processing, including creating and retrieving orders.
3. **Product Service**: Manages product data, including inventory and product details.
4. **User Service**: Manages user profiles, authentication, and authorization.

---

## Technologies Used

- **Spring Boot**: For building RESTful APIs.
- **MySQL**: For relational database management.
- **Keycloak**: For user authentication and authorization.
- **JWT (JSON Web Token)**: For secure communication between services.
- **MapStruct**: For mapping between DTOs and entities.
- **Maven**: For dependency management.
- **GitHub Actions**: For CI/CD pipelines.

---

## Authentication with Keycloak

This project uses **Keycloak** as the identity and access management (IAM) solution. Keycloak handles user authentication, token generation, and role-based access control.

### Keycloak Setup

1. **Install Keycloak**:
   - Download and install Keycloak from the [official website](https://www.keycloak.org/downloads).
   - Start the Keycloak server:
     ```bash
     ./bin/kc.sh start-dev
     ```

2. **Create a Realm**:
   - Log in to the Keycloak admin console (default: `http://localhost:8080/admin`).
   - Create a new realm, e.g., `ecommerce-realm`.

3. **Create a Client**:
   - Go to the `Clients` tab in your realm.
   - Create a new client, e.g., `ecommerce-client`.
   - Set the `Access Type` to `confidential`.
   - Add valid redirect URIs for your services (e.g., `http://localhost:808*/`).

4. **Create Roles**:
   - Define roles such as `USER` and `ADMIN` under the `Roles` tab.

5. **Create Users**:
   - Add users under the `Users` tab and assign them roles.

6. **Update Services**:
   - Add Keycloak configuration to each service in `application.properties` or `application.yml`:
     ```properties
     spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/ecommerce-realm
     spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/realms/ecommerce-realm/protocol/openid-connect/certs
     ```

---

## Installation

### Prerequisites

- **Java 17** or later
- **Maven**
- **MySQL Database**
- **Keycloak** (as explained above)
