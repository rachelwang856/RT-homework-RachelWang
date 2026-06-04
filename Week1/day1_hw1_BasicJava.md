
# Homework1
## List vs Set
List is ordered and allows duplicate elements.
Set does not allow duplicate elements. HashSet do not maintain order, while LinkedHashSet maintains insertion order and TreeSet keeps elements sorted.

## LinkedList vs ArrayList
ArrayList is backed by a dynamic array. It is fast for random access because we can get elements by index in O(1). However, inserting or deleting elements in the middle can be slower because elements may need to be shifted.
LinkedList is backed by a doubly linked list. It is efficient for adding or removing elements, but random access is slower because it needs to traverse nodes.

## What is Map Interface
Map is an interface that stores data in k-v pairs. 
Common implementations include HashMap, LinkedHashMap, TreeMap, Hashtable (deprecated), and ConcurrentHashMap.

## How does HashMap work
key -> hashcode() = hash value -> array index (bucket head) -> compared original keys by equal() -> hash collision or value update

## What is hash collision
A hash collision happens when two different keys generate the same hash value. 
In this case, multiple entries are stored in the same bucket. In Java HashMap, collisions are handled by using a linked list. If the number of entries in a bucket becomes large enough, the linked list can be converted into a red-black tree to improve lookup performance.

## What is Collections used for
Collections Framework is a powerful sets of classes and interfaces that provides a foundation for working with groups of objects, like List, Queue, Deque, Set, sortedSet,


## What is immutable class
An immutable class is a class whose object state cannot be changed after creation. 
String is a common immutable class in Java.

## HashTable vs HashMap vs ConcurrentHashmap
HashMap is not thread-safe. It allows one null key and multiple null values.
Hashtable is thread-safe because its methods are synchronized, but it is legacy and slower. It does not allow null keys or null values.
ConcurrentHashMap is thread-safe and designed for concurrent access. It has better performance than Hashtable in multi-threaded environments. It does not allow null keys or null values.

## String vs StringBuilder vs StringBuffer
String -> immutable -> constant string pool
StringBuilder -> mutable -> not thread safe
StringBuffer -> mutable -> thread safe (synchronized)

## why we need to override the hashcode and equals method at the same time
Hash-based collections such as HashMap and HashSet use hashCode() to find the bucket and equals() to compare objects inside the bucket. If we override equals() but not hashCode(), these collections may not work correctly.

```java
import java.util.*;

class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Only override hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    // No equals() override
}

public class Main {
    public static void main(String[] args) {
        //Set<Employee> employees = new HashSet<>();

        Employee e1 = new Employee("Alice", 1);
        Employee e2 = new Employee("Alice", 1);

        System.out.println(e1.hashCode() == e2.hashCode()); // true
        System.out.println(e1.equals(e2)); // false

        // employees.add(e1);
        // employees.add(e2);

        // System.out.println(employees.size()); // 2，出错
    }
}
```

## play around the common data structure apis (map, set, queue, list), write some practice codes
refer to ```day1.java```


# Homework2

## String vs StringBuilder vs StringBuffer
String -> immutable -> constant string pool
StringBuilder -> mutable -> not thread safe
StringBuffer -> mutable -> thread safe (synchronized)

## Comparator vs Comparable, when to use which one
Comparable is an interface implemented directly within a class template to provide a default, built-in sorting strategy by overriding the `compareTo` method 

Comparator: external comparison rule, custom sorting
students.sort((s1, s2) -> s2.age - s1.age); // negative return s1,s2; 0 no change; positive return s2,s1

## Overriding vs overloading
Overloading: same class, diffirent signatures (same method name and different parameters), compile-time polymorphism
Overriding: happens between parent class and child class, same signature but different logic, runtime polymorphism

## Java 8 basic data types
byte, short, int, long, float, double, char, boolean

## Primitive type, reference type
Primitive types store actual values, such as int, double, and boolean.
Reference types store references to objects, such as String, arrays, and custom classes.
Primitive types cannot be null, while reference types can be null.

