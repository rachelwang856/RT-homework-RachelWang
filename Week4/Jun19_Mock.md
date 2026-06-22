## Link

https://rachel-java-dev-s3-20260526.s3.us-east-2.amazonaws.com/Jun19.mov

## What is the Singleton design pattern?
 
Singleton guarantees a class has only one instance across the whole application, with one global access point. The best real example is Spring beans, which are singletons by default. To build it safely I make the constructor private and hold one static instance, and the cleanest thread-safe way in Java is an enum or a static holder class. It fits shared, stateless things like configuration or a connection pool.
 
 
## Where can we set CORS — backend, frontend, or both?
 
CORS is enforced by the browser but configured on the backend — the frontend can't grant itself permission, the server decides which origins are allowed through response headers. In Spring Boot I set it with the CrossOrigin annotation locally, or globally with a CORS config bean. In microservices I centralize it at the API gateway so I don't repeat it in every service.
 
 
## Can you write a hint in Hibernate?
 
Yes. JPA and Hibernate support query hints. I use the QueryHints annotation on a repository method or call setHint on a query, for things like timeout, read-only mode, or enabling the query cache. If I need a database-level optimizer hint, I drop to a native query and write the hint as an SQL comment.
 
 
## Monolithic vs Microservices
 
A monolith packages everything into one deployable app — simple to deploy and fast with in-process calls, but tightly coupled and hard to scale one part alone. Microservices split the system by business capability into independently deployable, independently scalable services, but you pay with distributed complexity: network calls, data consistency, and harder debugging. So I'd start with a monolith for a small team and move to microservices when scale or team size demands independent deployment.
 
 
## Stored procedures or Java/Hibernate logic?
 
I put business logic in the Java and Hibernate layer by default — it's easier to maintain, unit test, version control, and debug, and it keeps the app database-independent. Stored procedures shine for data-heavy batch work where I want to avoid network round trips. So my rule is normal logic in Java, stored procedures only for heavy, performance-critical data operations.
 
 
## Eureka annotations & configuration in Spring Boot
 
On the Eureka server I add the EnableEurekaServer annotation and configure it, not to register with itself. On each client I set the application name and point its service-url to the Eureka server, so it auto-registers. Then for service-to-service calls I use a load-balanced RestTemplate or OpenFeign, which resolves other services by their registered name instead of a hard-coded URL.
 
 
## What is your responsibility in the microservices?
 
I owned one service end to end — for example the student service. That meant designing its REST endpoints, writing the business logic, modeling entities with Hibernate, and handling how it talks to other services through REST or Feign. I also added validation, centralized exception handling, and monitoring with Actuator and Prometheus so the service was observable in production.
 
 
## A → B → C, some return 500/errors — what do you do?
 
The key is fault tolerance so one failing service doesn't cascade and take down the whole chain. I add timeouts so callers don't hang, retries for idempotent calls, and a circuit breaker with Resilience4j that trips when a downstream keeps failing and returns a fallback instead. I also use distributed tracing with a trace ID so I can quickly see which hop — A, B, or C — is actually returning the 500.
 
 
## How do you secure communication in microservices?
 
First I encrypt traffic with TLS, and for strong trust use mutual TLS so both sides authenticate. For authorization I pass a JWT or OAuth2 token between services and validate it, often centrally at the gateway. I keep internal services off the public network, so only the gateway is exposed, and store secrets in a vault instead of code. At larger scale a service mesh like Istio can handle mutual TLS automatically.
 
 
## When to use a message queue between services?
 
I use a message queue when services don't need an immediate response and I want to decouple them — async tasks like sending emails or notifications, smoothing out traffic spikes by buffering, and event-driven broadcasts where one event fans out to many consumers. 

It also gives reliable delivery with retries. I avoid it when the caller needs a strong, immediate, synchronous result; for that, a direct REST call is better.
 
 