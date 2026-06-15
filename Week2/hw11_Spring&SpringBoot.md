# Homework 11 Part 2: Interview Questions

## 1. Why Spring Boot? Pros and Cons

Spring Boot simplifies Spring application development by providing auto-configuration, embedded Tomcat, and starter dependencies. It allows developers to create production-ready RESTful applications quickly without writing a lot of manual XML or servlet configuration.

The main advantages are fast setup, fewer configurations, easy dependency management, and good integration with monitoring tools like Actuator. The downside is that auto-configuration can hide internal details, so developers still need to understand Spring core concepts to debug complex issues.

⸻

## 2. How to Start a Spring Boot Project from Scratch?

To start a Spring Boot project, I can use Spring Initializr or IntelliJ to create a Maven project and select dependencies such as Spring Web, Lombok, Spring Data JPA, PostgreSQL Driver, and Spring Boot DevTools. Then I configure application.properties, create the Controller, Service, Repository, Entity, and DTO layers, and run the main class with @SpringBootApplication.

After the application starts, I usually test the APIs with Postman and verify the database connection. In a professional workflow, I would also push the project to GitHub through a feature branch and create a pull request.

⸻

## 3. @Controller vs. @RestController

@Controller is used in Spring MVC when the application returns views, such as HTML pages. @RestController is used for REST APIs and returns data directly, usually in JSON format.

@RestController is basically a combination of @Controller and @ResponseBody. In backend RESTful services, we usually use @RestController.

⸻

## 4. @PathVariable vs. @RequestParam

@PathVariable gets data from the URL path and is usually used to identify a specific resource. For example, /employees/1 uses 1 as a path variable.

@RequestParam gets data from query parameters and is usually used for filtering, searching, pagination, or optional conditions. For example, /employees?page=1&size=10 uses page and size as request parameters.

⸻

## 5. @RequestBody vs. @ResponseBody

@RequestBody is used to map the HTTP request body into a Java object. It is commonly used in POST, PUT, and PATCH requests when the client sends JSON data.

@ResponseBody is used to write the Java return object directly into the HTTP response body, usually as JSON. In @RestController, @ResponseBody is included automatically, so we do not need to add it to every method.

⸻

## 6. How to Use @GetMapping, @PutMapping, @PostMapping, @DeleteMapping, and @RequestMapping?

@RequestMapping is usually used at the controller class level to define the parent URL path. Then @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping are used at the method level to handle specific HTTP operations.

For example, @GetMapping("/{id}") retrieves a record, @PostMapping creates a record, @PutMapping("/{id}") updates a full record, and @DeleteMapping("/{id}") deletes a record. These annotations help design clean RESTful endpoints.

⸻

## 7. What Is Spring Actuator?

Spring Actuator is a Spring Boot feature used to monitor and manage an application. It provides endpoints such as health, metrics, info, and Prometheus metrics.

It is useful in production because developers can check application health, JVM memory, request metrics, and other runtime information. In real projects, Actuator is often connected with Prometheus and Grafana for long-term monitoring and visualization.

⸻

## 8. How to Achieve Async in a Spring Boot Application?

In Spring Boot, async processing can be achieved by adding @EnableAsync to a configuration class and adding @Async to the method that should run asynchronously. The async method usually runs in a separate thread from a thread pool.

For example, email notifications, report generation, or non-critical logging can be handled asynchronously. For production systems, it is better to configure a custom thread pool instead of relying on the default executor.

⸻

## 9. How Does Spring Handle Exceptions?

Spring can handle exceptions globally using @RestControllerAdvice and @ExceptionHandler. This allows developers to define one centralized class to catch exceptions and return consistent error responses.

For example, a ResourceNotFoundException can be mapped to 404 Not Found, and validation errors can be mapped to 400 Bad Request. This makes controller code cleaner and improves API consistency.

⸻

## 10. How Does Spring Validate Data?

Spring validates data using validation annotations on DTO fields, such as @NotNull, @NotBlank, @Size, @Email, and @Pattern. In the controller method, we add @Valid before @RequestBody to trigger validation.

If validation fails, Spring throws an exception, and we can handle it globally with @RestControllerAdvice. Backend validation is important because clients may bypass frontend validation and send invalid data directly to the API.

⸻

## 11. How Does Spring Do Logging?

Spring Boot uses logging frameworks such as SLF4J and Logback by default. Developers can create a logger in each class and log important information, warnings, and errors.

Logging can also be implemented with AOP for cross-cutting concerns. For example, we can use an aspect to log method entry, exit, execution time, and exceptions without repeating logging code in every service method.

⸻

## 12. Cache Hit vs. Cache Miss

A cache hit means the requested data is found in the cache, so the application can return it quickly without querying the database. A cache miss means the requested data is not found in the cache, so the application needs to query the database and may store the result back into the cache.

Cache hit improves performance and reduces database load. Cache miss is slower because it requires accessing the original data source.

⸻

## 13. Basic Understanding of Redis

Redis is an in-memory key-value database commonly used for caching, distributed locks, counters, rankings, and session storage. Because it stores data in memory, it is much faster than traditional disk-based databases for many read and write operations.

In Spring Boot projects, Redis is often used to cache frequently accessed data and reduce database pressure. Common Redis data structures include String, Hash, List, Set, Sorted Set, and Stream.

⸻

## 14. Why Use @RestControllerAdvice Instead of @ControllerAdvice?

@ControllerAdvice is used for global controller-level exception handling, but it is often used with view-based Spring MVC applications. @RestControllerAdvice is a combination of @ControllerAdvice and @ResponseBody, so it returns response data directly, usually as JSON.

For RESTful APIs, @RestControllerAdvice is more convenient because error responses should be returned in the response body rather than rendering an HTML page. That is why backend REST services usually prefer @RestControllerAdvice.

⸻