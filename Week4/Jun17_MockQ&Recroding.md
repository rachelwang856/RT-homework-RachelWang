## Link

https://rachel-java-dev-s3-20260526.s3.us-east-2.amazonaws.com/Jun17.mov

## How to inject a bean with the same type?
 
When multiple beans of the same type exist, Spring first tries by type, then falls back to by name using the variable name. If that also fails, it throws NoUniqueBeanDefinitionException. To resolve this, use @Primary on one bean definition to mark it as the global default, so Spring picks it automatically when there is ambiguity. Or use @Qualifier at the injection point to specify exactly which bean you want by name. @Primary is a global default while @Qualifier gives precise control at each individual injection site.
 
## OOP 4 principles?
 
The four principles of OOP are Encapsulation, Inheritance, Polymorphism, and Abstraction. Encapsulation means hiding internal state and exposing only necessary behavior — achieved through access modifiers like private, protected, and public, combined with getter and setter methods to control access to fields. 

Inheritance means a subclass extends a parent class using the extends keyword and reuses its fields and methods, reducing code duplication — a class can also implement multiple interfaces using the implements keyword. 

Polymorphism has two forms: runtime polymorphism through method overriding, where a subclass provides its own implementation of a parent method and the correct version is resolved at runtime based on the actual object type; and compile-time polymorphism through method overloading, where multiple methods share the same name but differ in signature, resolved at compile time. 

Abstraction means hiding implementation details and exposing only the essential contract — achieved through abstract classes, which can have both abstract and concrete methods but cannot be instantiated, and interfaces, which define a pure contract that implementing classes must fulfill, supporting multiple implementation in Java.
 
## What is the Executor library?
 
The Executor framework manages thread pools so you don't have to manually create and destroy threads. The core interface is ExecutorService, and the main implementation is ThreadPoolExecutor where you configure core pool size, max pool size, keep alive time, and the task queue. When a task is submitted, if core threads are available it runs immediately, otherwise it goes into the queue, and if the queue is full a new thread is created up to the max pool size — beyond that a rejection policy is triggered. In Spring Boot I use ThreadPoolTaskExecutor with @Async, configuring separate pools for primary tasks like order processing and auxiliary tasks like email notifications so they never compete for the same resources.
 
## Java 21 features?
 
Java 21 is an LTS release with several major features. Virtual Threads allow the JVM to manage millions of lightweight threads with minimal overhead, dramatically improving concurrency without changing application code. Pattern matching for switch allows type-based case handling directly in switch statements without manual casting. Record patterns enable deconstruction of record types in pattern matching. Sequenced Collections adds a unified interface for ordered collections with methods like getFirst() and getLast(). Virtual threads are the most impactful feature — combined with modernized Tomcat, they reduce the need for reactive programming with WebFlux in many high-concurrency scenarios.
 
## What is ThreadLocal?
 
ThreadLocal provides each thread with its own independent copy of a variable so threads do not interfere with each other. The most common use case is storing the current logged-in user per request thread — you set it at the start of the request and any code in that thread can access it without passing it as a parameter. The critical rule is to always call remove() at the end of the request because web applications use thread pools and threads are reused. Failing to clean up causes the next request on the same thread to see the previous user's data, which is a data leak.
 
## Java versions?
 
The major LTS versions are Java 8, 11, 17, and 21. Java 8 introduced Lambda, Stream API, Optional, and the new Date/Time API. Java 11 added the standardized HTTP Client, ZGC, and Epsilon GC. Java 17 introduced Records, Sealed classes, Switch expressions, and Text blocks — it is also the minimum version required by Spring Boot 3. Java 21 introduced Virtual Threads, Pattern matching for switch, and Record patterns. In my project I used Spring Boot 3 with Java 17, and I understand the javax to jakarta package rename is the most significant breaking change when upgrading from Spring Boot 2 to 3.
 