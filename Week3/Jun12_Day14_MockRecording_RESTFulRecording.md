# Mock Link
<https://rachel-java-dev-s3-20260526.s3.us-east-2.amazonaws.com/Jun12_MockRecord_SpringMock2.mov>

# Coding Recording Link
<https://rachel-java-dev-s3-20260526.s3.us-east-2.amazonaws.com/Jun12_Day14_RESTfulCoding.mov>


# Spring Boot / Spring MVC Interview Scripts

# 1. Introduce what is Spring Framework

Spring Framework is a Java enterprise framework used to build backend applications with features like IOC, Dependency Injection, AOP, transaction management, and Spring MVC. It helps developers reduce boilerplate code and build loosely coupled, maintainable applications.

In early Spring, configuration was more XML-based and not fully annotation-driven. Starting from Spring MVC and especially Spring Boot, we introduced annotations like @Controller, @Service, @Repository, and @Autowired. So now in springboot, we develop via annotation-driven style


## 2. Spring Boot version you used

I have used Spring Boot 3 in my recent Spring Boot project. Since Spring Boot 3 requires Java 17 or above, I used Java 17 as the runtime version.

One important change in Spring Boot 3 is the migration from `javax.*` packages to `jakarta.*` packages. `javax` originally came from Java Extension APIs, and after the Jakarta EE transition, many enterprise Java APIs now use the `jakarta` namespace, such as `jakarta.persistence` and `jakarta.validation`.


# 3. How do you define profile?

Spring Profile is used to manage environment-specific configurations, such as DEV, QA, UAT, staging, and production. We can define different files like application-dev.properties, application-qa.properties, or application-prod.yaml, and activate one profile using spring.profiles.active.

For example, in DEV we may use a local PostgreSQL database, while in production we use a remote database and different credentials. We can also use @Profile("dev") or @Profile("!dev") to control whether a bean is created under a specific environment.


# 4. What discovery service implementation have you used before?

In a microservices architecture, a discovery service is used to register and discover service instances dynamically. I have used or learned Eureka as a common implementation in the Spring Cloud ecosystem.

With Eureka, we can annotate the main Spring Boot application with @SpringBootApplication and enable service discovery with @EnableEurekaClient or related Spring Cloud configuration. This allows services to find each other without hardcoding host and port information.


# 5. What is AOP?

AOP stands for Aspect-Oriented Programming. It is used to separate cross-cutting concerns, such as logging, security, exception handling, validation, and performance monitoring, from core business logic.

In Spring, there are two common styles related to AOP usage. One is @RestControllerAdvice for global exception handling at the controller layer, and the other is @Aspect with pointcuts and advice to define where and when extra logic should be executed.


# 6. How to write Spring Boot to call from frontend to backend and save data to database?

The flow usually follows a 3-tier architecture: frontend sends an HTTP request to the backend **Controller layer**, the Controller exposes RESTful endpoints, the **Service layer** processes business logic, and the **DAO or Repository layer** saves data into the database. After the database operation finishes, the response goes back from Repository to Service, then Controller, then through Tomcat back to the frontend.

//For example, the frontend can send a POST /employees request with a JSON request body. The Spring Boot controller receives it with @RequestBody, calls the service, the service calls the repository, and Spring Data JPA or another ORM framework persists the data into SQL or NoSQL databases.


# 7. Describe Spring MVC

Spring MVC stands for Model-View-Controller. It separates a web application into Model, View, and Controller layers so that request handling, business data, and presentation logic are separated.

In backend RESTful services, the Controller receives HTTP requests, calls the Service layer for business logic, and returns response data. //For example, GET /employee/1 can retrieve employee information, while PUT /employee/1 can update employee information.


# 8. How do you validate input data in Spring Boot?

Input validation in Spring Boot usually has two steps. First, we define validation rules on DTO, model, or entity fields using annotations like @NotNull, @NotEmpty, @NotBlank, @Min, @Max, @Email, @Size, and @Pattern.

Second, we enable validation in the controller by adding @Valid before @RequestBody or request object parameters. If validation fails, Spring throws a validation exception, and we can handle it globally with @RestControllerAdvice and return 400 Bad Request.


# 9. Spring Boot Actuator

Spring Boot Actuator is used to expose production-ready monitoring endpoints for a Spring Boot application. It can show health status, metrics, beans, caches, environment information, and other runtime data.

To use it, we (1) import the actuator dependency, (2) configure exposed endpoints in application.properties or application-dev.properties, for example management.endpoints.web.exposure.include=*, and then access endpoints such as /actuator/health or /actuator/metrics. In production, (3) metrics are often persisted in Prometheus and (4) visualized in Grafana.


# 10. How does Spring MVC work?

Spring MVC works through the DispatcherServlet, which acts as the front controller. When a client sends an HTTP request, the request first reaches DispatcherServlet, then it finds the correct controller method through handler mapping.

The controller handles the request and calls the service layer, the service layer processes business logic, and the repository layer communicates with the database. Finally, the result is returned back through the controller as a response, usually JSON in a Spring Boot REST API.


# 11. What is Controller? How do you use and implement Controller?

Controller is the top layer in a three-tier backend architecture. It exposes RESTful endpoints to the frontend or client and is responsible for receiving requests, reading path variables, request parameters, headers, and request bodies, then returning responses with proper status codes.

In Spring Boot, I usually implement a controller with @RestController and define a parent path with @RequestMapping. Then I use @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping for different RESTful operations, and I keep business logic in the Service layer instead of putting it directly in the Controller.


# 12. What is WebFlux? Have you used it in your project?

Spring WebFlux is a reactive web framework in Spring. Compared with Spring MVC’s traditional thread-per-request blocking model, WebFlux supports asynchronous and non-blocking request processing.

It commonly uses Mono for zero or one object and Flux for multiple objects. I have not used WebFlux deeply in my main CRUD Spring Boot project because that project used traditional Spring MVC with blocking JPA, but I understand WebFlux is more suitable for high-concurrency, reactive, I/O-heavy applications.


# 13. How do you connect the database in Spring Boot?

To connect a database in Spring Boot, I first import the required dependency, such as Spring Data JPA and the PostgreSQL driver. Then I configure database properties in application.properties or YAML, including JDBC URL, username, password, driver, connection pool size, and timeout settings.

Spring Boot can auto-configure the DataSource, but if we need custom data sources or third-party connection pools, we can define them using @Configuration and @Bean. If multiple data sources exist, we can use @Qualifier or @Primary to tell Spring which one to inject.


# 14. How do you handle global exception in Spring Boot?

In Spring Boot, I handle global exceptions with @RestControllerAdvice and @ExceptionHandler. This allows me to centralize exception handling logic instead of writing repeated try-catch blocks in every controller.

For example, I can handle a ResourceNotFoundException and return 404 Not Found, or handle validation exceptions and return 400 Bad Request. This makes API error responses consistent and keeps controller code clean.


# 15. Spring Boot annotation

Common Spring Boot annotations include @SpringBootApplication, @RestController, @Service, @Repository, @Component, @Configuration, @Bean, @Autowired, @Qualifier, @Primary, @Lazy, and @RestControllerAdvice. These annotations help register beans, inject dependencies, define RESTful endpoints, and handle cross-cutting concerns.

For example, @SpringBootApplication includes @SpringBootConfiguration, @EnableAutoConfiguration, and @ComponentScan. @Configuration with @Bean is often used to register third-party objects that we cannot annotate directly.


# 16. How does Spring IOC work? Include annotations, injection, and bean types.

Spring IOC means the Spring container controls object creation, dependency injection, and lifecycle management instead of developers manually creating objects with new. During startup, Spring reads metadata from annotations like @SpringBootApplication, scans classes with @ComponentScan, creates beans, injects dependencies, and manages the bean lifecycle.

Common bean registration annotations include @Component, @Controller, @RestController, @Service, @Repository, @Configuration, and @Bean. 

Common injection types are constructor injection, field injection, and setter injection, and common bean scopes include singleton, prototype, request, session, and application.


# 17. How many ways to inject bean in Spring and which one do we use most?

There are three common ways to inject beans in Spring: constructor injection, field injection, and setter injection. Field injection uses @Autowired directly on a field, setter injection injects dependencies through setter methods, and constructor injection injects dependencies through the constructor.

In modern Spring Boot development, constructor injection is the most recommended approach. It makes dependencies explicit, supports final fields, prevents many NullPointerException issues, and is easier for unit testing with mock objects.


# 18. By name vs. by type

Spring usually injects dependencies by type first. That means if a class requires a PaymentService, Spring looks for a bean whose type matches PaymentService.

If there are multiple beans of the same type, Spring cannot decide which one to inject, so we need to resolve the conflict by name using @Qualifier. For example, @Qualifier("stripePaymentService") tells Spring to inject the bean with that specific name.


# 19. Why constructor injection?

Constructor injection is recommended because it makes required dependencies explicit when the object is created. If a dependency is missing, the application can fail fast during startup instead of failing later with a NullPointerException.

It also supports immutable fields with final and makes unit testing easier because we can directly pass mock dependencies into the constructor. That is why constructor injection is generally preferred over field injection in professional Spring Boot projects.


# 20. What Java version can we use with Spring Boot 3?

Spring Boot 3 requires Java 17 or above. This is because Spring Boot 3 is based on newer versions of the Spring Framework and Jakarta EE APIs.

For example, many packages moved from javax.* to jakarta.*, where javax originally meant Java extension and later Jakarta became the new namespace. So when using Spring Boot 3, Java 17 is the minimum required version.



# 21. What is DispatcherServlet?

DispatcherServlet is the front controller in Spring MVC. It receives incoming HTTP requests and dispatches them to the correct controller method based on URL and mapping information.

The typical flow is: client request goes to DispatcherServlet, then handler mapping finds the controller, the controller calls the service layer, the service calls the repository layer, and the response is returned back to the client. In Spring Boot, much of the DispatcherServlet setup is auto-configured, so developers usually focus on writing RESTful controllers and business logic.

