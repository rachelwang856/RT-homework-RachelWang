# Homework 6

## Client-Server Model

The client-server model is an architecture where the client sends requests and the server processes them and returns responses. It separates the user interface from backend business logic. For example, a browser is the client, and a Spring Boot application is the server.

## Application Service

An application service is the backend part of a system that handles business logic. It usually receives requests, processes data, calls databases or other services, and returns results. For example, an order service may check inventory, create an order, and trigger payment.

## HTTP Request / Response

HTTP request and response are the basic communication format between a client and a server. The client sends a request with a method, URL, headers, and optional body, and the server returns a response with a status code and data. For example, a GET request can retrieve user information from the backend.

## Horizontal Scaling vs Vertical Scaling

Vertical scaling means adding more resources, such as CPU or memory, to one server. Horizontal scaling means adding more server instances to share the traffic. In large systems, horizontal scaling is usually preferred because it is more flexible and improves availability.

## Load Balancer

A load balancer distributes incoming requests across multiple server instances. It improves performance, availability, and fault tolerance. For example, if one server is busy or down, the load balancer can send traffic to another healthy server.

## Microservice

A microservice is an architectural style where a large application is split into smaller independent services. Each service focuses on one business capability and can be developed, deployed, and scaled separately. For example, an e-commerce system may have separate user, product, order, and payment services.

## Microfrontend

Microfrontend applies the microservice idea to the frontend. It splits a large frontend application into smaller independent frontend modules. 

For example, the product page, shopping cart, and checkout page can be developed separately and combined into one website.

## Relational Database / SQL Database

A relational database stores data in structured tables with rows and columns. It supports SQL and is good for data with clear relationships and strong consistency requirements. 

For example, MySQL, PostgreSQL, and Oracle are relational databases.

## Nonrelational Database / NoSQL Database

A nonrelational database stores data in a more flexible format, such as documents, key-value pairs, or graphs. It is useful when the data structure changes often or when the system needs high scalability. 

For example, MongoDB can store JSON-like documents.

## API Gateway

An API gateway is the entry point for client requests in a microservices system. It routes requests to different backend services and can also handle authentication, rate limiting, and logging. 

For example, requests to `/users` may go to the user service, while requests to `/orders` may go to the order service.

## Message Queue

A message queue allows services to communicate asynchronously. It helps decouple services and improves reliability when one service is temporarily slow or unavailable. 

For example, after a user places an order, the order service can send a message to the notification service.

## Log

A log records important events, errors, and system behaviors during application execution. Logs are useful for debugging, monitoring, and understanding what happened in production. 

For example, if an API fails, logs can show the request information and error message.

## Monitor

Monitoring means continuously checking the health and performance of a system. It helps engineers detect issues early, such as high latency, high error rate, or server downtime. For example, a monitoring system can alert developers when CPU usage becomes too high.

## Deployment with AWS / Azure / GCP

Deployment means putting an application onto a cloud platform so users can access it. Cloud providers like AWS, Azure, and GCP offer servers, storage, databases, and networking tools. 

For example, we can deploy a Spring Boot application on AWS EC2 or Kubernetes.

## Security: Authentication and Authorization

Authentication checks who the user is, while authorization checks what the user is allowed to do. Both are important for protecting system resources and user data. 

For example, login verifies the user's identity, and role-based access control decides whether the user can access an admin page.

## Why Testing

Testing helps verify that software works correctly and prevents bugs from reaching production. It also makes future code changes safer because developers can detect problems earlier. For example, unit tests can check business logic, while integration tests can check whether APIs and databases work together.