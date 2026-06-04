
# Homework 2


## JRE vs JDK vs JVM ##

The JVM is the Java Virtual Machine that runs Java bytecode and manages memory. The JRE is the runtime environment needed to run Java programs, and it includes the JVM and standard libraries. The JDK is used for development and includes the JRE, compiler, and development tools.


## How does JVM work ##

The JVM loads compiled .class files, verifies the bytecode, and executes it on the operating system. It also manages memory, garbage collection, threads, and runtime exceptions. This is why Java can be platform-independent: the same bytecode can run on different systems with different JVM implementations.

## JVM memory data model ##

The JVM memory model includes areas such as the Method Area, Heap, Stack, Program Counter Register, and Native Method Stack. The heap stores objects, the stack stores method frames and local variables, and the method area stores class-level information such as static variables and class metadata. Understanding this model helps developers debug memory issues and optimize performance.

## How does GC work ##

Garbage Collection automatically reclaims memory from objects that are no longer reachable. The JVM usually starts from GC roots, finds reachable objects, and removes unreachable ones. This helps developers avoid manual memory management, but GC still consumes CPU and memory resources, so poor object management can affect performance.

## young/old/perm generation ##

In the traditional generational GC model, the young generation stores newly created objects, and the old generation stores objects that survive multiple GC cycles. The permanent generation, or PermGen, used to store class metadata before Java 8, but it was replaced by Metaspace in Java 8. This design improves GC efficiency because most objects die young.

## difference types of GC ##

Different garbage collectors are designed for different performance goals. For example, Serial GC is simple and suitable for small applications, Parallel GC focuses on throughput, G1GC balances throughput and pause time, and ZGC or Shenandoah are designed for very low pause times. In real systems, we choose a GC based on application size, latency requirements, and memory usage.

# Homework 3

## Java modifier scope: public, private, protected, default scope ##

public means the member can be accessed from anywhere. private means it can only be accessed inside the same class, while protected allows access within the same package and subclasses. Default scope means no modifier is used, and the member can only be accessed within the same package.

## What is static scope ##

static means a variable, method, or block belongs to the class rather than a specific object instance. Static members are loaded with the class and can be accessed through the class name. For example, Math.max() is a static method because we do not need to create a Math object to use it.

## how does classloader work ##

The classloader loads .class files into the JVM when they are needed. It follows a delegation model, where a classloader first asks its parent classloader to load the class before trying to load it itself. This design helps ensure core Java classes are loaded safely and consistently.

## Describe the difference between unchecked and checked exceptions in Java. ##

Checked exceptions are checked by the compiler, so the developer must either handle them with try-catch or declare them using throws. Unchecked exceptions happen at runtime and usually extend RuntimeException, so the compiler does not force us to handle them. For example, IOException is checked, while NullPointerException is unchecked.

## What is the difference between finally, final, and finalize in Java? ##

final is a keyword used to prevent reassignment, overriding, or inheritance. finally is a block used with try-catch to execute cleanup logic whether an exception happens or not. finalize() was a method called by GC before object destruction, but it is deprecated and should not be used in modern Java.

## Define try-with resource. How can you say that it differs from an ordinary try? ##

Try-with-resources is a special try statement that automatically closes resources that implement AutoCloseable. It is different from an ordinary try because we do not need to manually close the resource in a finally block. It is commonly used for files, database connections, and streams.

```java
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    System.out.println(br.readLine());
} catch (IOException e) {
    e.printStackTrace();
}

```

## Define Runtime Exception. Describe it with the help of an example. ##

A runtime exception is an unchecked exception that occurs during program execution. The compiler does not force us to catch it, but we should still prevent it through good validation. For example, accessing a method on a null object can cause a NullPointerException.

```java
String name = null;
System.out.println(name.length()); // NullPointerException
```

## What is the difference between NoClassDefFoundError and ClassNotFoundException in Java ##

ClassNotFoundException happens when code tries to load a class dynamically using methods like Class.forName(), but the class cannot be found. NoClassDefFoundError happens when a class was available during compilation but is missing at runtime. The first one is a checked exception, while the second one is an error.

## Why should we clean up activities such as I/O resources in the finally block? ##

I/O resources such as files, sockets, and database connections use system resources outside normal Java objects. If we do not close them, they may cause resource leaks and performance issues. A finally block ensures cleanup logic runs even when an exception occurs.

## Describe OutofMemoryError in exception handling. ##

OutOfMemoryError happens when the JVM cannot allocate enough memory for new objects. It is an Error, not a normal exception, so applications usually cannot fully recover from it. Common causes include memory leaks, too many objects, or incorrect JVM heap settings such as a small -Xmx value.

## What is Generics in Java? What are the advantages of using Generics? ##

Generics allow us to write classes, interfaces, and methods with type parameters. They improve type safety because many type errors can be caught at compile time instead of runtime. For example, List<String> ensures that the list only stores strings.

## How does Generics works in Java? What is type erasure? ##

Generics work at compile time by checking type correctness. At runtime, Java uses type erasure, which means generic type information is removed and replaced with raw types or bounds. For example, List<String> and List<Integer> are both treated as List at runtime.

## What is the difference between List<? extends T> and List<? super T>? ##

List<? extends T> means the list can contain objects of type T or its subclasses, so it is mainly used for reading. List<? super T> means the list can contain objects of type T or its parent classes, so it is mainly used for writing. A simple rule is PECS: Producer Extends, Consumer Super.

## what is Optional class (write a demo code to use ofNullable, orElse, orElseThrow method) ##

Optional is a container object used to represent a value that may or may not be present. It helps reduce direct null checks and makes null-handling more explicit. For example, we can use ofNullable(), orElse(), and orElseThrow() to safely handle possible null values.

```java
String name = null;

Optional<String> optionalName = Optional.ofNullable(name);

String result1 = optionalName.orElse("Unknown");
System.out.println(result1);

String result2 = optionalName.orElseThrow(() -> new IllegalArgumentException("Name cannot be null"));
System.out.println(result2);
```

## what is OOP ##

OOP stands for Object-Oriented Programming, a programming style based on objects and classes. Its main principles are encapsulation, inheritance, polymorphism, and abstraction. For example, a Student class can encapsulate fields like name and score, and different subclasses can override methods to provide different behaviors.

# Homework 4

## what is functional interface ##

A functional interface is an interface with exactly one abstract method. It can be used with lambda expressions or method references. For example, Runnable, Predicate, Consumer, and Function are common functional interfaces in Java.

## what is default method ##

A default method is a method in an interface that has a method body. It was introduced in Java 8 so interfaces can add new methods without breaking existing implementations. For example, an interface can provide a default log() method that implementing classes can use directly.

```java
interface MyInterface {
    default void log() {
        System.out.println("Default log method");
    }
}
```

## what is the difference between Predicate, Supplier, Consumer, Function? ##

Predicate<T> takes one input and returns a boolean. Supplier<T> takes no input and returns a value, while Consumer<T> takes one input and returns nothing. Function<T, R> takes one input and returns another value.

## write a piece of code to use the Predicate, Supplier, Consumer, Function interface ##

```java
import java.util.function.*;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        Predicate<Integer> isAdult = age -> age >= 18;
        Supplier<String> supplier = () -> "Hello Java";
        Consumer<String> printer = message -> System.out.println(message);
        Function<String, Integer> lengthFunction = str -> str.length();

        System.out.println(isAdult.test(20));
        System.out.println(supplier.get());
        printer.accept("This is a consumer");
        System.out.println(lengthFunction.apply("Rachel"));
    }
}
```

## what is method reference ##

A method reference is a shorter way to write a lambda expression that only calls an existing method. It improves readability when the lambda body simply delegates to another method. For example, System.out::println is a method reference for x -> System.out.println(x).

```java
List<String> names = Arrays.asList("Alice", "Bob");
names.forEach(System.out::println);
```

## what is CompleteableFuture ##

CompletableFuture is a Java class used for asynchronous programming. It allows us to run tasks in the background and combine or handle results later without blocking the main thread. For example, it can be used to call a remote API asynchronously and process the response when it returns.

```java
CompletableFuture.supplyAsync(() -> "Hello")
        .thenApply(result -> result + " Java")
        .thenAccept(System.out::println);
```

## Default keyword vs Java default scope ##

The default keyword in Java 8 interfaces is used to define a method with a body inside an interface. Default scope means package-private access when no access modifier is written. They are different concepts: one is a keyword for interface methods, and the other is an access level.

## Coding: create a list of students, Student Class has name, age, score three fields. List<Student> list = new ArrayList<>(); ##

```java
import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private int age;
    private int score;
    private String gender;

    public Student(String name, int age, int score, String gender) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    public String getGender() {
        return gender;
    }
}

public class StreamDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();

        list.add(new Student("Alice", 20, 90, "girl"));
        list.add(new Student("Amy", 21, 85, "girl"));
        list.add(new Student("Bob", 20, 55, "boy"));
        list.add(new Student("David", 22, 70, "boy"));
        list.add(new Student("Alex", 21, 60, "boy"));

        // use stream api to find all the students’ name starting with ‘A’
        List<String> namesStartingWithA = list.stream()
                .map(Student::getName)
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        // use stream api to get the sum of all the students score
        int totalScore = list.stream()
                .mapToInt(Student::getScore)
                .sum();

        // use stream api to find all the students whose score >= 60
        List<Student> passedStudents = list.stream()
                .filter(student -> student.getScore() >= 60)
                .collect(Collectors.toList());

        // use stream api to retrieve all students name
        List<String> allNames = list.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        // use stream api to count the frequency of each age
        Map<Integer, Long> ageFrequency = list.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));

        // use stream api to count the number of boys girls
        Map<String, Long> genderCount = list.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));

        System.out.println(namesStartingWithA);
        System.out.println(totalScore);
        System.out.println(passedStudents.size());
        System.out.println(allNames);
        System.out.println(ageFrequency);
        System.out.println(genderCount);
    }
}
```

## intermediate operation vs terminal operation ##

Intermediate operations return another stream and are lazy, meaning they do not execute immediately. Terminal operations produce a final result or side effect and trigger the stream pipeline execution. For example, filter() and map() are intermediate operations, while collect(), count(), and forEach() are terminal operations.

## Coding: given a char array, use stream api to count the frequency of each char ##

```java
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharFrequencyDemo {
    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'a', 'c', 'b', 'a'};

        Map<Character, Long> frequency = new String(chars)
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println(frequency);
    }
}
```
## Steam API: map() vs flatmap(); ##

map() transforms each element into one new element. flatMap() transforms each element into a stream and then flattens all streams into one stream. For example, map() can convert names to their lengths, while flatMap() can convert a list of sentence lists into one list of words.

```java
List<List<String>> nestedList = Arrays.asList(
        Arrays.asList("A", "B"),
        Arrays.asList("C", "D")
);

List<String> flatList = nestedList.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());

System.out.println(flatList);
```
