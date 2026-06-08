
# Homework 7

## Optimized Singleton Version

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```


### public class Singleton

This defines a class named Singleton. The goal is to make sure only one object of this class can be created.

### private static volatile Singleton instance;

This declares a static variable to store the single instance. volatile prevents visibility issues and instruction reordering in a multithreaded environment.

### private Singleton() {}

The constructor is private, so other classes cannot directly create objects using new Singleton(). This is necessary to control object creation inside the class.

### public static Singleton getInstance()

This method provides global access to the single instance. Because it is static, it can be called without creating an object first.

### if (instance == null)

This first check avoids synchronization after the instance has already been created. It improves performance because locking is expensive.

### synchronized (Singleton.class)

This lock ensures that only one thread can create the instance at a time. It prevents multiple objects from being created in a concurrent situation.

### if (instance == null)

This second check is necessary because multiple threads may pass the first check before one thread creates the instance. It guarantees that the object is created only once.

### instance = new Singleton();

This creates the singleton object. Because of the lock and double-checking, only one instance will be created.

### return instance;

This returns the single shared instance. All callers will get the same object.

---

## What Is Reflection

Reflection is a Java feature that allows a program to inspect and modify classes, methods, fields, and constructors at runtime. It is useful for frameworks because they can create objects or call methods dynamically. For example, Spring uses reflection to create beans and inject dependencies.

---

## What Are HTTP Status Codes

HTTP status codes are numbers returned by the server to describe the result of a request. They help the client understand whether the request succeeded, failed, or needs another action. For example, 200 means success, while 404 means the requested resource was not found.

---

## 200 OK

200 OK means the request was successful and the server returned the expected result. It is commonly used for successful GET, PUT, or POST requests. For example, retrieving a user profile successfully can return 200.

---

## 201 Created

201 Created means the request succeeded and a new resource was created. It is commonly used after a successful POST request. For example, creating a new user account can return 201.

---

## 202 Accepted

202 Accepted means the request has been accepted but has not finished processing yet. It is useful for asynchronous operations. For example, submitting a large file processing task can return 202.

---

## 204 No Content

204 No Content means the request succeeded, but the server does not return any response body. It is often used for successful DELETE or update operations. For example, deleting a record successfully can return 204.

---

## 307 Temporary Redirect

307 Temporary Redirect means the requested resource is temporarily available at another URL. The client should use the new URL but keep the same HTTP method. For example, a POST request redirected with 307 should still remain a POST request.

---

## 301 Moved Permanently

301 Moved Permanently means the resource has permanently moved to a new URL. The client should use the new URL in the future. For example, when a website changes its domain, it may return 301.

---

## 400 Bad Request

400 Bad Request means the request is invalid or malformed. It usually happens when the client sends wrong parameters or missing required data. For example, submitting a form without a required email field may return 400.

---

## 401 Unauthorized

401 Unauthorized means the user has not been authenticated. The client usually needs to log in or provide a valid token. For example, accessing a protected API without a JWT token can return 401.

---

## 403 Forbidden

403 Forbidden means the user is authenticated but does not have permission to access the resource. It is an authorization problem. For example, a normal user trying to access an admin page may get 403.

---

## 404 Not Found

404 Not Found means the requested resource does not exist. It may happen when the URL is wrong or the record is not found in the database. For example, requesting a deleted product page can return 404.

---

## 500 Internal Server Error

500 Internal Server Error means something went wrong on the server side. It is usually caused by bugs, exceptions, or server failures. For example, a database connection failure may cause a 500 response.

---

## What Is HTTP

HTTP stands for Hypertext Transfer Protocol. It is an application-layer protocol used for communication between clients and servers. For example, browsers use HTTP to request web pages and APIs from backend servers.

---

## GET Method

GET is used to retrieve data from the server. It should not change the server state, so it is considered safe and idempotent. For example, getting a user profile can use GET.

---

## POST Method

POST is used to create a new resource or submit data to the server. It is not idempotent because sending the same request multiple times may create duplicate records. For example, placing an order usually uses POST.

---

## PUT Method

PUT is used to update or replace an entire resource. It is idempotent because sending the same PUT request multiple times results in the same final state. For example, replacing a full user profile can use PUT.

---

## DELETE Method

DELETE is used to remove a resource from the server. It is idempotent because deleting the same resource multiple times has the same final result: the resource no longer exists. For example, deleting a comment can use DELETE.

---

## PATCH Method

PATCH is used to partially update a resource. It is useful when only one or a few fields need to be changed. For example, updating only a user’s phone number can use PATCH.

---

## POST vs PATCH

POST is mainly used to create a new resource or submit an action. PATCH is used to partially update an existing resource. For example, creating a new user uses POST, while updating only the user’s nickname uses PATCH.

---

## POST vs PUT

POST usually creates a new resource and is not idempotent. PUT usually replaces an existing resource and is idempotent. For example, submitting a new order uses POST, while replacing a full user profile uses PUT.

---

## What Is Idempotent

Idempotent means making the same request multiple times has the same effect as making it once. This matters because network retries should not accidentally create duplicate data. GET, PUT, and DELETE are generally idempotent, while POST is usually not.

---

# Homework 8

## TCP Three-Way Handshake

TCP three-way handshake is the process used to establish a reliable connection between a client and a server. 

**The client sends SYN, the server replies with SYN-ACK, and the client sends ACK. **

This ensures both sides are ready before data transmission starts.

---

## TCP vs UDP

TCP is connection-oriented and reliable, meaning it guarantees data delivery, order, and error checking. UDP is connectionless and faster, but it does not guarantee delivery or order. For example, file transfer usually uses TCP, while live video streaming or gaming may use UDP.

---

## What Is Tomcat

Tomcat is a Java web server and servlet container. It runs Java web applications and handles HTTP requests, servlets, and responses. In Spring Boot, an embedded Tomcat server is often used to run the application.

---

## Basic Components of Tomcat

Tomcat has several basic components, such as Server, Service, Connector, Engine, Host, Context, and Servlet. The Connector receives HTTP requests, and the Servlet container routes them to the correct Java logic. These components work together to process web requests and return responses.

---

## What Is Web Server

A web server handles HTTP requests from clients and returns HTTP responses. It can serve static files like HTML, CSS, and images, or forward dynamic requests to application logic. For example, Tomcat can work as a web server for Java web applications.

---

## Three-Tier Architecture

Three-tier architecture separates an application into Controller, Service, and DAO layers. The Controller handles requests, the Service handles business logic, and the DAO communicates with the database. This design improves code organization and keeps each layer focused on one responsibility.

---

## OSI Model

The OSI model is a seven-layer model that explains how network communication works. The layers are Physical, Data Link, Network, Transport, Session, Presentation, and Application. It helps developers understand where different protocols and technologies work.

---

## Physical Layer

The Physical layer handles the actual transmission of raw bits through hardware. It includes cables, signals, Wi-Fi, and network cards. For example, Ethernet cables work at this layer.

---

## Data Link Layer

The Data Link layer handles communication between devices on the same local network. It uses MAC addresses and helps detect transmission errors. For example, Ethernet and Wi-Fi protocols work at this layer.

---

## Network Layer

The Network layer handles routing data between different networks. It uses IP addresses to decide where packets should go. For example, the IP protocol works at this layer.

---

## Transport Layer

The Transport layer manages end-to-end communication between applications. It provides protocols like TCP and UDP. For example, TCP ensures reliable data delivery, while UDP focuses on speed.

---

## Session Layer

The Session layer manages sessions or connections between applications. It helps establish, maintain, and close communication sessions. For example, it can help manage a login session between a client and server.

---

## Presentation Layer

The Presentation layer handles data format, encoding, encryption, and compression. It makes sure data is readable by the application layer. For example, SSL/TLS encryption and data serialization are related to this layer.

---

## Application Layer

The Application layer is the layer closest to users and applications. It provides network services such as HTTP, FTP, DNS, and email protocols. For example, when a browser sends an HTTP request, it works at the application layer.