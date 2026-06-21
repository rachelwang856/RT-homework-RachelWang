## Link

https://rachel-java-dev-s3-20260526.s3.us-east-2.amazonaws.com/Jun16.mov

## If we override hashCode but not equals?
 
If you override hashCode but not equals, hash-related data structures like HashMap, HashSet, and ConcurrentHashMap will have issues during hash collision or data update. When two objects hash to the same bucket, the data structure uses equals to determine if they are the same key. 

Since equals is not overridden, it falls back to the default reference comparison from Object, so two objects with identical content are treated as different keys. This causes incorrect behavior — you may end up with duplicate entries in a HashSet, or a HashMap get may fail to find a value even though the key looks identical. The rule is: always override equals and hashCode together.
 
## How do you write a REST API in Spring Boot?
 
In Spring Boot, I create REST APIs using `@RestController`, which combines `@Controller` and `@ResponseBody` to automatically return JSON responses. 

I use `@RequestMapping` to define the base URL and HTTP method annotations such as `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping` to implement RESTful endpoints. 

I use `@RequestBody`, `@PathVariable` for url path variables, `@RequestParam`, and `@RequestHeader` to process request data, return `ResponseEntity` to control both the response payload and HTTP status code, 

and use validation annotations such as `@Valid`, `@NotNull`, `@Min`, and `@Max` to validate incoming requests.
 
 
## What is CORS?
 
CORS stands for Cross-Origin Resource Sharing. It is a browser security mechanism that enforces the same-origin policy, which blocks requests from a web page to a different domain, protocol, or port. 

For example if Angular runs on localhost 3000 and the Spring Boot backend runs on localhost 8080, the browser blocks the request by default because the ports differ. 

In Spring Boot I handle it by adding @CrossOrigin on specific controllers, or by configuring a global CORS policy using WebMvcConfigurer to specify allowed origins and HTTP methods. 

On the Angular side, developers must also handle the difference between synchronous and asynchronous modes when making HTTP calls, using async and await for non-blocking requests.
 
## Map vs filter?
 
Both are Stream API operations. Filter takes a predicate and keeps only elements that match the condition, reducing the number of elements but keeping the same type. Map takes a function and transforms each element, keeping the same number of elements but potentially changing the type. 

For example, filter on a list of employees keeps only those older than 30, while map transforms each Employee into just their name, producing a stream of Strings. They are commonly chained: filter first to narrow the data, then map to transform it, then collect to produce the final result.
 

 
## Spring Boot Actuator?
 
Spring Boot Actuator is a production-ready monitoring module that exposes HTTP endpoints for health checks, metrics, and application state. You add the spring-boot-starter-actuator dependency and configure which endpoints to expose in application.properties. Key endpoints are /health, /metrics, /loggers, /beans, and /caches. You can also create custom endpoints using @Endpoint with @ReadOperation, @WriteOperation, or @DeleteOperation. Micrometer is used to collect metrics, which are stored in Prometheus as a time-series database, and then visualized in Grafana as dashboards. In production only expose the endpoints you need, because endpoints like /env can leak sensitive configuration.