# Ecom Backend using Microservice Architecture with Spring Boot

## Overview:
This project is a microservice architecture designed using Spring Boot framework, which consists of multiple services that work together to provide a complete solution. The services include Order Service, Inventory Service, Product Service, API Gateway, Discovery Server, Notification Service, and Keycloak.

### Technologies Used:

- Spring Boot
- Spring Cloud Netflix
- Docker Compose
- Apache Kafka
- Keycloak

### Services:

#### API Gateway:
API Gateway acts as the front door for all the requests to the microservices. It provides a single entry point and enables dynamic routing of requests to the appropriate service. This helps to reduce the coupling between the client and the individual microservices, making it easier to update and maintain the services independently.

#### Discovery Server:
Discovery Server plays a crucial role in the architecture by providing a central registry of all the microservices and their instances. This allows the API Gateway to locate the services and route requests to the correct instance of the service. The Discovery Server also provides load balancing and fault tolerance by monitoring the availability of the services and redirecting traffic to healthy instances.

#### Order Service:
This service manages the orders placed by customers. It includes APIs to create, update, and cancel orders. It communicates with the Inventory and Product Services to ensure availability and pricing of products.

#### Inventory Service:
This service manages the inventory of products available for sale. It includes APIs to retrieve available inventory and update inventory after an order has been placed.

#### Product Service:
This service manages the details of the products available for sale. It includes APIs to retrieve product details and pricing information.

#### Notification Service:
Notification Service uses Apache Kafka to send notifications to customers about their orders. Kafka is a distributed messaging system that enables reliable and scalable communication between services. This service is designed to work in conjunction with the Order Service and can be extended to support other use cases as well.

#### Keycloak:
Keycloak provides secure authentication and authorization for the microservices. It includes APIs to create and manage user accounts, roles, and permissions. This helps to protect the microservices from unauthorized access and ensures that only authorized users can access the resources.

### Deployment:
Deployment is made easy with Docker Compose, which simplifies the deployment process by allowing multiple services to be deployed as a single unit. The Docker Compose file includes all the necessary configurations for each service, including ports, environment variables, and dependencies. This enables easy deployment and scaling of the microservices as the traffic grows.

### Conclusion:
The Microservice Architecture with Spring Boot project is a robust and scalable solution for managing orders, inventory, and products. Its use of Docker Compose and Keycloak allows for easy deployment and secure authentication and authorization. The combination of services enables flexibility and easy maintenance, making it an ideal choice for businesses looking to optimize their processes.
