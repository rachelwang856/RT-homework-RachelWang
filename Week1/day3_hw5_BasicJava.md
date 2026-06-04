# Homework 5: Java Multithreading Interview Scripts


## 1. How to Create a Thread in Java

There are four common ways to create a thread in Java: extending Thread, implementing Runnable, implementing Callable, and using ExecutorService. In real backend development, I would usually use a thread pool because it reuses threads and avoids the overhead of creating new threads repeatedly. Callable is useful when the task needs to return a result, while Runnable is enough when no result is needed.

```java
// 1. Extend Thread
class MyThread extends Thread {
    public void run() {
        System.out.println("Running");
    }
}

// 2. Implement Runnable
Runnable task = () -> System.out.println("Running");

// 3. Callable with Future
Callable<Integer> callable = () -> 100;

// 4. Thread Pool
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(task);
```


---

## 2. Thread Lifecycle

A Java thread has six main states: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, and TERMINATED. A thread moves from NEW to RUNNABLE after calling start(), may become BLOCKED when waiting for a lock, and becomes TERMINATED when the run() method finishes. Understanding the lifecycle helps debug concurrency issues such as deadlocks, long blocking, and thread resource waste.

NEW --start()--> RUNNABLE --run() finishes--> TERMINATED 
RUNNABLE --waiting for lock--> BLOCKED 
RUNNABLE --wait()/join()--> WAITING 
RUNNABLE --sleep()/wait(timeout)--> TIMED_WAITING 
---

## 3. How Does a Thread Pool Work?

A thread pool manages and reuses a group of worker threads to execute submitted tasks. When a task comes in, the pool first tries to use a core thread; if all core threads are busy, the task goes into a queue, and if the queue is full, the pool may create extra threads up to the maximum pool size. Thread pools matter because they reduce thread creation overhead and help control system resources.

submit task 
→ use core thread 
→ if core threads are busy, enter queue 
→ if queue is full, create extra thread 
→ if max threads are reached, reject task 

---

## 4. what is the potential problem for the newCachedThreadPool and newFixedThreadPool and why

newCachedThreadPool is risky because its maximum thread count is Integer.MAX_VALUE, so under high traffic it may create too many threads and cause OutOfMemoryError. newFixedThreadPool has a fixed number of threads, but it uses an unbounded queue, so too many pending tasks may accumulate in memory. In production, I would prefer a custom ThreadPoolExecutor with bounded thread count and bounded queue size.

| Thread Pool | Problem | Reason |
|---|---|---|
| newCachedThreadPool | Too many threads | Maximum thread count is too large |
| newFixedThreadPool | Too many queued tasks | Queue is unbounded |
| Custom ThreadPoolExecutor | Safer | Thread count and queue size are controlled |

---

## 5. What Is Future?

Future represents the result of an asynchronous task. It allows the main thread to submit a task and get the result later, but calling get() is blocking. Because of this, Future is useful for simple async tasks, but not ideal for complex non-blocking workflows.


---

## 6. What Is CompletableFuture?

CompletableFuture is a Java class for asynchronous and non-blocking programming. It supports task chaining, result transformation, combining multiple tasks, and exception handling. It is commonly used in web applications when calling databases, remote APIs, or multiple services in parallel.

---

## 7. Future vs CompletableFuture

Future can run a task asynchronously, but getting the result usually requires get(), which blocks the current thread. CompletableFuture is more powerful because it supports non-blocking chaining, result transformation, task combination, and exception handling. In modern backend development, CompletableFuture is usually better for complex asynchronous workflows.

| Feature | Future | CompletableFuture |
|---|---|---|
| Get result | Blocking get() | Non-blocking chaining |
| Transform result | Hard | thenApply() |
| Combine tasks | Hard | thenCombine(), allOf() |
| Exception handling | Limited | exceptionally(), handle() |
| Use case | Simple async task | Async workflow |

---

## 8. Lock vs synchronized

Both Lock and synchronized are used to protect shared resources and ensure thread safety. synchronized is simpler because the JVM automatically releases the lock, while Lock is more flexible because it supports tryLock(), interruptible locking, and multiple conditions. For simple critical sections, I would use synchronized; for complex concurrency control, I would use ReentrantLock.

| Feature | synchronized | Lock |
|---|---|---|
| Release lock | Automatic | Manual |
| Simplicity | Simple | More flexible |
| tryLock() | Not supported | Supported |
| Interruptible lock | Not supported | Supported |
| Multiple conditions | Limited | Supported |

---

## 9. wait(), notify(), notifyAll(), and join()

wait(), notify(), and notifyAll() are used for thread communication and must be called inside a synchronized block or method. wait() releases the lock and makes the current thread wait, while notify() wakes one waiting thread and notifyAll() wakes all waiting threads. join() is different because it makes one thread wait for another thread to finish.

| Method | Meaning |
|---|---|
| wait() | Releases lock and waits |
| notify() | Wakes one waiting thread |
| notifyAll() | Wakes all waiting threads |
| join() | Waits for another thread to finish |

Important difference:

wait() releases the lock. sleep() does not release the lock. 

---
## 10.commonly used CompletableFuture api and write demo code for each of them

### runAsync and supplyAsync

runAsync and supplyAsync are used to start asynchronous tasks. runAsync is used when the task does not return a value, while supplyAsync is used when the task returns a result. In production, I would usually pass a custom thread pool instead of using the default common pool.
```java
CompletableFuture<Void> f1 =
    CompletableFuture.runAsync(() -> sendEmail());

CompletableFuture<String> f2 =
    CompletableFuture.supplyAsync(() -> getUserInfo());
```


### thenApply and thenApplyAsync

thenApply is used to transform the result of a previous asynchronous task. thenApply may run in the same thread that completed the previous stage, while thenApplyAsync usually runs in another thread from a thread pool. They are useful when we need to process or convert an async result.

```java 
CompletableFuture<Integer> future =
    CompletableFuture.supplyAsync(() -> 10)
        .thenApply(num -> num * 2);

CompletableFuture<Integer> future =
    CompletableFuture.supplyAsync(() -> 10, executor)
        .thenApplyAsync(num -> num * 2, executor);
```

### handle and exceptionally

exceptionally is used to handle an exception and return a fallback result. handle is more flexible because it receives both the result and the exception, so it can handle both success and failure. These APIs are important because async workflows need proper error handling.

```java 
CompletableFuture<Integer> future =
    CompletableFuture.supplyAsync(() -> 10 / 0)
        .exceptionally(ex -> -1);

CompletableFuture<Integer> future =
    CompletableFuture.supplyAsync(() -> 10 / 0)
        .handle((result, ex) -> ex == null ? result : -1);
```

### thenCompose

thenCompose is used when the next asynchronous task depends on the result of the previous task. It avoids nested CompletableFuture objects and creates a clean sequential async workflow. For example, after getting a user ID, we can use it to fetch that user’s orders.

```java
CompletableFuture<String> future =
    getUserId()
        .thenCompose(userId -> getOrders(userId));
```

### thenCombine

thenCombine is used when two independent asynchronous tasks can run in parallel and their results need to be combined. It improves performance because the tasks do not need to run sequentially. For example, we can fetch user information and order information at the same time, then combine them into one response.

```java
CompletableFuture<String> result =
    userFuture.thenCombine(orderFuture,
        (user, order) -> user + order);
```


### allOf

allOf is used when we need to wait for multiple asynchronous tasks to finish. It returns a CompletableFuture<Void>, so if we need actual results, we usually call join() on each original future after all tasks complete. It is useful for parallel service calls or batch processing.

```java 
CompletableFuture<Void> all =
    CompletableFuture.allOf(future1, future2, future3);

all.join();
```


### anyOf

anyOf is used when we only need the first completed result among multiple asynchronous tasks. It is useful for timeout handling, backup service calls, or racing multiple tasks. One thing to remember is that it returns CompletableFuture<Object>, so type conversion may be needed.

```java 
CompletableFuture<Object> first =
    CompletableFuture.anyOf(future1, future2, future3);

Object result = first.join();
```

### thenAccept and thenRun

thenAccept is used when we want to consume the previous result without returning a new value. thenRun is used when we do not need the previous result and only want to run another action after the previous task finishes. They are commonly used for final actions such as logging, cleanup, or sending notifications.

| API | Use Case |
|---|---|
| thenAccept | Use previous result, return nothing |
| thenRun | Ignore previous result, run next action |

```java
CompletableFuture
    .supplyAsync(() -> "data")
    .thenAccept(data -> System.out.println(data));

CompletableFuture
    .supplyAsync(() -> "data")
    .thenRun(() -> System.out.println("Done"));
```

### join() vs get()

Both join() and get() can be used to get the result from a CompletableFuture. The difference is that get() throws checked exceptions, while join() throws unchecked exceptions. In many examples, join() is more convenient because it avoids extra try-catch syntax.

| Method | Exception Type |
|---|---|
| get() | Checked exceptions |
| join() | Unchecked exceptions |


### CompletableFuture API Summary

| API | Purpose |
|---|---|
| runAsync | Run async task without return value |
| supplyAsync | Run async task with return value |
| thenApply | Transform result |
| thenApplyAsync | Transform result asynchronously |
| thenAccept | Consume result |
| thenRun | Run next action without using previous result |
| thenCompose | Chain dependent async tasks |
| thenCombine | Combine two independent async results |
| allOf | Wait for all tasks |
| anyOf | Wait for first completed task |
| exceptionally | Handle exception with fallback |
| handle | Handle both success and failure |

---
