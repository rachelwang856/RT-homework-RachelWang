# Homework 10 — AOP

## What is AOP?

AOP stands for Aspect-Oriented Programming. It is used to separate cross-cutting concerns, such as logging, security, exception handling, and performance monitoring, from the main business logic.

In Spring, AOP helps us avoid repeating the same logic in many service or controller methods. For example, instead of writing logging code in every method, we can define one aspect and apply it automatically to selected methods.

⸻

## What is JoinPoint and Aspect in AOP?

A JoinPoint is a specific point during program execution where AOP logic can be applied, usually a method execution in Spring AOP. An Aspect is a class that contains the cross-cutting logic, such as logging before or after a method runs.

For example, when a service method is called, that method execution can be a JoinPoint, and a logging Aspect can intercept it to print logs or measure execution time.

⸻

## What are Aspect, JoinPoint, Pointcut, Advice, and Target?

An Aspect is the class that contains cross-cutting logic, while a JoinPoint is a specific execution point, such as a method call. A Pointcut defines which JoinPoints should be intercepted, and Advice defines what logic should run and when it should run.

The Target is the original object or method being intercepted by the AOP proxy. For example, if we apply logging to all service methods, the service class is the target, the method execution is the JoinPoint, the matching rule is the Pointcut, and the logging logic is the Advice.

⸻

## ApplicationContext vs. BeanFactory

BeanFactory is the basic Spring container that creates and manages beans, usually with lazy initialization. ApplicationContext is a more advanced container built on top of BeanFactory, and it provides additional features such as event publishing, internationalization, and easier integration with Spring applications.

In real Spring Boot projects, we usually use ApplicationContext because it provides a complete container environment. BeanFactory is more lightweight, but ApplicationContext is more commonly used in enterprise applications.

⸻

## How is Spring MVC Working Flow?

In Spring MVC, the client first sends an HTTP request to the DispatcherServlet, which works as the front controller. The DispatcherServlet finds the correct controller method through handler mapping, then the controller calls the service layer to process business logic.

After the service layer returns the result, the controller prepares the response body or view, and the response is sent back to the client. In a typical Spring Boot REST API, the flow is: Client → DispatcherServlet → Controller → Service → Repository → Database → Response.

⸻

## How does @Autowired work?

@Autowired tells Spring to inject a dependency automatically from the Spring container. By default, Spring resolves dependencies by type, meaning it looks for a bean whose type matches the required field, constructor parameter, or setter parameter.

If there is exactly one matching bean, Spring injects it automatically. If there are multiple matching beans, we need to use @Qualifier or @Primary to tell Spring which bean should be used.

⸻

## @Autowired and @Qualifier

@Autowired is used to inject a Spring bean automatically, usually by type. @Qualifier is used together with @Autowired when there are multiple beans of the same type and we need to specify which one should be injected by name.

For example, if we have two implementations of PaymentService, such as paypalPaymentService and stripePaymentService, @Qualifier("stripePaymentService") tells Spring to inject the Stripe implementation. This avoids bean conflict errors during dependency injection.

⸻

## Two Use Cases for @PostConstruct and @PreDestroy

@PostConstruct is used to run initialization logic after a bean is created and its dependencies are injected. For example, we can use it to load cache data, validate configuration, or initialize a connection after the Spring bean is ready.

@PreDestroy is used to run cleanup logic before the bean is destroyed. For example, we can use it to close resources, shut down thread pools, release database connections, or flush remaining logs before the application stops.