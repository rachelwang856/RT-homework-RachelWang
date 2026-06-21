## how to write restapi in spring boot

In Spring Boot, I create REST APIs using `@RestController`, which combines `@Controller` and `@ResponseBody` to automatically return JSON responses. 

I use `@RequestMapping` to define the base URL and HTTP method annotations such as `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping` to implement RESTful endpoints. 

I use `@RequestBody`, `@PathVariable` for url path variables, `@RequestParam`, and `@RequestHeader` to process request data, return `ResponseEntity` to control both the response payload and HTTP status code, 

and use validation annotations such as `@Valid`, `@NotNull`, `@Min`, and `@Max` to validate incoming requests.

## What is fair lock

By default, Java locks are **unfair**, meaning there is no guarantee that threads will acquire the lock in the order they requested it. A **fair lock** enforces FIFO (First-In-First-Out) ordering, so the thread that has been waiting the longest gets the lock next. Fair locks help prevent thread starvation, although they usually have lower throughput than unfair locks.

Java's `ReentrantLock` supports both fair and unfair locking strategies.

```java

//fair lock
Lock lock = new ReentrantLock(true); 

//unfair lock
Lock lock = new ReentrantLock();
// is equal to
Lock lock = new ReentrantLock(false);

```

# What is a Sealed Class?


A Sealed Class is a Java 17 feature that restricts which classes are allowed to extend or implement a class. 

It uses the `permits` keyword to explicitly define the permitted subclasses, giving developers more control over the class hierarchy. 

Sealed classes improve type safety and make domain models more predictable.



```java

public sealed class Shape

    permits Circle, Square, Triangle {

}

```

---

# Introduce Spring Framework

## Interview Answer

Spring Framework is a Java framework used to build enterprise applications efficiently. Its core concepts are Inversion of Control (IoC), Dependency Injection (DI), and Aspect-Oriented Programming (AOP), which help reduce coupling and improve maintainability. 


---

## Detailed Answer

Spring Framework is a Java framework that simplifies enterprise application development.

When introducing Spring, the most important concepts are:

```text
1. IoC
2. Dependency Injection
3. AOP
```

---

### 1. IoC (Inversion of Control)

IoC means Spring takes over the responsibility of creating and managing objects.

Benefits: Loose coupling, Better maintainability, Easier testing

---

### 2. Dependency Injection (DI)

Dependency Injection is the implementation mechanism of IoC.

Instead of creating dependencies manually, Spring injects them automatically.

#### Constructor Injection (Recommended)

#### Setter Injection

#### Field Injection

---

### 3. Bean Scope

Bean Scope determines how many instances Spring creates.

#### Singleton (Default)

#### Prototype

#### Request Scope

#### Session Scope

#### Application Scope

---

### 4. AOP (Aspect-Oriented Programming)

AOP separates cross-cutting concerns from business logic.

Instead of placing logging, security, and transaction code inside every method, Spring allows developers to add them through aspects.

AOP works by wrapping existing business logic rather than modifying the original code.

Common use cases:

```text
Logging
Security
Transaction Management
Performance Monitoring
```

Common advice types:

#### @Before

Runs before method execution.

#### @After

Runs after method execution.

#### @Around

Runs before and after method execution.

---

### Summary

```text
Spring Framework

├── IoC
│
├── Dependency Injection
│   ├── Constructor Injection
│   ├── Setter Injection
│   └── Field Injection
│
├── Bean Scope
│   ├── Singleton
│   ├── Prototype
│   ├── Request
│   ├── Session
│   └── Application
│
└── AOP
    ├── Logging
    ├── Security
    └── Transaction Management
```


# How Does Spring IoC Work? What Are the Common Annotations, Dependency Injection Types, and Bean Scopes?

## Interview Answer

Spring IoC (Inversion of Control) is a mechanism where the Spring container manages object creation, dependency injection, and bean lifecycle instead of the application creating objects manually. 

Spring uses annotations such as `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController`, `@Autowired`, and `@Bean` to register and manage beans. 

Dependencies can be injected through constructor, setter, or field injection, 

and Spring supports bean scopes such as singleton, prototype, request, session, and application.

---

## Detailed Answer

### How Spring IoC Works

IoC stands for Inversion of Control, means Spring creates and manages the object.
The Spring IoC Container is responsible for:

- Creating objects (Beans)
- Managing lifecycle
- Injecting dependencies
- Managing bean scopes

Workflow:

```text
Application Starts
        ↓
Spring Container Starts
        ↓
Component Scan
        ↓
Create Beans
        ↓
Resolve Dependencies
        ↓
Inject Dependencies
        ↓
Application Ready
```

---

# Common Spring Annotations

### @Component

Generic Spring-managed bean.

```java
@Component
public class EmailUtil {
}
```

---

### @Service

Business logic layer.

```java
@Service
public class EmployeeService {
}
```

---

### @Repository

Data access layer.

```java
@Repository
public class EmployeeRepository {
}
```

Additional benefit:

```text
Database Exception Translation
```

---

### @Controller

Used in Spring MVC applications that return views.

```java
@Controller
public class HomeController {
}
```

---

### @RestController

Used for REST APIs.

```java
@RestController
public class EmployeeController {
}
```

Equivalent to:

```java
@Controller
@ResponseBody
```

Returns JSON automatically.

---

### @Configuration

Configuration class.

```java
@Configuration
public class AppConfig {
}
```

---

### @Bean

Registers an object manually.

```java
@Bean
public ObjectMapper objectMapper() {
    return new ObjectMapper();
}
```

Used when the source code cannot be modified.

---

### @Autowired

Injects dependencies automatically.

```java
@Autowired
private EmployeeRepository repository;
```

---

# Dependency Injection Types

### Constructor Injection (Recommended)

```java
@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(
            EmployeeRepository repository) {
        this.repository = repository;
    }
}
```

Advantages:

- Explicit dependencies
- Easier testing
- Supports immutable fields

Most recommended by Spring.

---

### Setter Injection

```java
@Autowired
public void setRepository(
        EmployeeRepository repository) {
    this.repository = repository;
}
```

Used for optional dependencies.

---

### Field Injection

```java
@Autowired
private EmployeeRepository repository;
```

Simple but harder to test.

Generally not recommended in modern Spring applications.

---

# Bean Scopes

### Singleton (Default)

```java
@Scope("singleton")
```

One bean instance for the entire Spring container.

```text
All Requests
      ↓
Same Bean Instance
```

Common for:

- Service
- Repository
- Controller

---

### Prototype

```java
@Scope("prototype")
```

Creates a new bean every time.

```text
Request 1 → Bean A
Request 2 → Bean B
```

Useful for stateful objects.

---

### Request Scope

```java
@Scope("request")
```

One bean per HTTP request.

```text
Request A → Bean A
Request B → Bean B
```

---

### Session Scope

```java
@Scope("session")
```

One bean per user session.

```text
User Session
      ↓
Same Bean Instance
```

Example:

- Shopping Cart
- User Preferences

---

### Application Scope

```java
@Scope("application")
```

One bean shared across the entire web application.

---

# Summary

```text
Spring Framework

├── IoC Container
│
├── Common Annotations
│   ├── @Component
│   ├── @Service
│   ├── @Repository
│   ├── @Controller
│   ├── @RestController
│   ├── @Configuration
│   ├── @Bean
│   └── @Autowired
│
├── Dependency Injection
│   ├── Constructor Injection
│   ├── Setter Injection
│   └── Field Injection
│
└── Bean Scopes
    ├── Singleton
    ├── Prototype
    ├── Request
    ├── Session
    └── Application
```