
# Singleton Pattern

  

The Singleton pattern ensures that only one instance of a class exists throughout the application. 

It is commonly used for shared resources such as configuration managers, caches, and logging systems. Singleton helps maintain consistency and reduce resource consumption.

## Eager vs Lazy

### Eager Initialization

Eager initialization creates the Singleton instance when the class is loaded. 

We declare a static final instance inside the class, initialize it immediately, make the constructor private to prevent external object creation, and provide a public static `getInstance()` method to return the instance.

### Lazy Initialization

Lazy initialization delays the creation of the Singleton instance until it is first needed. 

To implement it, we declare the instance as `null`, make the constructor `private` to prevent external object creation, and expose a public static `getInstance()` method. In a multithreaded environment, we typically use **Double-Checked Locking** together with the **volatile** keyword, where the first `if (instance == null)` improves performance by avoiding unnecessary synchronization, the second `if (instance == null)` ensures thread safety by preventing multiple threads from creating multiple instances, and `volatile` guarantees visibility and prevents instruction reordering.

```java
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (instance == null) {                 // First Check

            synchronized (Singleton.class) {

                if (instance == null) {         // Second Check
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
```


### Banking

#### Fraud Rule Manager

A banking system can use a Singleton to maintain a single fraud detection rule engine shared across all transaction services.

### E-Commerce

#### Inventory Cache Manager

An e-commerce platform can use a Singleton cache manager to maintain a consistent view of product inventory.

### Health Insurance

#### Claim Rules Engine

A health insurance system can use a Singleton to manage claim validation rules across the entire application.

---

# Factory Pattern

  

The Factory pattern creates objects without exposing the creation logic to the client. 

It improves flexibility by allowing the application to create different implementations based on business requirements. This pattern is useful when multiple object types share the same interface.

### Banking

#### Payment Processor Factory

Create different payment processors such as ACH, Wire Transfer, or Credit Card Processor.

### E-Commerce

#### Shipping Provider Factory

Create UPS, FedEx, or USPS shipping services dynamically.

### Health Insurance

#### Insurance Plan Factory

Create HMO, PPO, or EPO insurance plans based on customer selection.

---

# Builder Pattern

  

The Builder pattern constructs complex objects step by step and is useful when an object contains many optional fields. 

It improves readability and avoids creating constructors with too many parameters. Builder is commonly used for request objects and domain models.

### Banking

#### Loan Application Builder

Build loan applications with optional fields such as collateral, co-signer, and repayment plans.

### E-Commerce

#### Order Builder

Build customer orders containing products, coupons, shipping methods, and payment information.

### Health Insurance

#### Insurance Policy Builder

Build insurance policies with optional coverage, deductible, beneficiaries, and riders.

---

# Proxy Pattern

  

The Proxy pattern provides a surrogate object that controls access to another object. 

It is commonly used for security, caching, logging, and lazy loading. Proxy allows additional functionality without modifying the original object.

### Banking

#### Security Proxy

Verify customer identity and permissions before allowing access to account information.

### E-Commerce

#### Product Cache Proxy

Cache frequently accessed product information to reduce database queries.

### Health Insurance

#### Medical Record Access Proxy

Control access to sensitive patient medical records and ensure compliance with privacy regulations.

---


# What is Microservice Architecture?

  

Microservice architecture is a distributed system design where an application is split into multiple small and independent services, each responsible for a specific business function. 

Each service can be developed, deployed, and scaled independently, which helps solve traffic bottlenecks and improves fault isolation. If one service fails, other services can continue running without bringing down the entire system.

---

# Why Microservice Architecture?

  

Microservice architecture was introduced to solve the scalability limitations of monolithic applications. 

It naturally supports horizontal scaling by allowing companies to deploy multiple instances of heavily used services during peak traffic periods. 

It also improves fault tolerance because failures are isolated to individual services instead of affecting the entire application.

---

# What are the Main Components of a Microservice Architecture?

  

A typical microservice architecture consists of API Gateway, Service Discovery, Config Server, Load Balancer, Distributed Tracing, Monitoring Systems, and multiple independent business services. 

These components work together to support service communication, scalability, monitoring, and fault tolerance. Spring Cloud provides many of these capabilities out of the box.

```text
Browser
    ↓
API Gateway
    ↓
Service Discovery
    ↓
Microservices
    ↓
Database

Supporting Components:
- Config Server
- Load Balancer
- Zipkin/Sleuth
- ELK
- Prometheus/Grafana
- Circuit Breaker
```

---

# Config Server

  

A Config Server centralizes application configuration for all microservices, such as database URLs, service endpoints, and credentials. This allows configuration changes without modifying every individual service. In Spring Cloud, Config Server helps reduce configuration management complexity across large distributed systems.

---

# Service Discovery (Eureka, Zookeeper, Consul)

  

Service Discovery allows microservices to dynamically find each other without hardcoding IP addresses. Services register themselves with a registry and periodically send heartbeats to indicate they are alive. If a service stops sending heartbeats, the registry removes it from the available service list.

### Common Tools

- Eureka
- Consul
- Zookeeper
- Nacos

---

# API Gateway

  

The API Gateway acts as the entry point for all client requests in a microservice architecture. It handles responsibilities such as authentication, routing, load balancing, and rate limiting before forwarding requests to the appropriate service. This simplifies client communication and centralizes cross-cutting concerns.

---

# Zipkin and Sleuth

  

Sleuth automatically generates unique trace IDs and span IDs for requests, while Zipkin collects and visualizes request flows across multiple services. Together they help developers trace requests through distributed systems and quickly identify performance bottlenecks or failures. They are essential tools for debugging microservice applications.

---

# Load Balancer (Ribbon)

  

A load balancer distributes incoming traffic across multiple instances of a service to improve scalability and availability. Ribbon is a client-side load balancer that works together with Service Discovery to select available service instances. Common load balancing strategies include round robin, latency-based routing, and geographic routing.

---

# ELK Stack

  

The ELK Stack is a centralized logging solution consisting of Elasticsearch, Logstash, and Kibana. Logstash collects logs, Elasticsearch stores and indexes them, and Kibana provides dashboards and search capabilities. ELK helps developers troubleshoot issues across distributed microservices.

---

# Monitoring System

  

Monitoring systems collect application metrics and visualize system health in real time. In Spring Boot, Actuator collects metrics, Prometheus stores time-series data, and Grafana provides dashboards. Together they help teams monitor performance, resource usage, and system availability.

```text
Actuator
    ↓
Prometheus
    ↓
Grafana
```

---

# Circuit Breaker

  

A Circuit Breaker prevents cascading failures when a downstream service becomes unavailable. Instead of continuously sending requests to a failing service, it returns a fallback response or friendly error message. Modern Spring Boot applications typically use Resilience4j to implement the Circuit Breaker pattern.

### Example

```text
Order Service
      ↓
Payment Service Down
      ↓
Fallback Response
```