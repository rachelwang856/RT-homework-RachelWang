# Mock Recording

<https://rachel-java-dev-s3-20260526.s3.us-east-2.amazonaws.com/Jun15_MockRecoring.mov>

## How do you handle exceptions in Java?

In Java, I handle checked exceptions explicitly using try-catch, throws, or try-with-resources when working with resources such as files, streams, or database connections. try-with-resources is useful because it automatically closes resources after execution, even if an exception occurs. For unchecked exceptions, I usually do not catch them blindly; instead, I fix the root cause, such as null checks, input validation, or logic errors.

## How can you use Optional?

Optional is used to represent a value that may or may not be present, which helps avoid NullPointerException. We can use Optional.of(value) when the value must not be null, Optional.ofNullable(value) when the value may be null, and Optional.empty() to create an empty Optional. To get a default value, we can use orElse(defaultValue).

## Why do you use POST instead of PUT?

I use POST when I want to create or insert a new resource. POST is generally not idempotent, meaning sending the same request multiple times may create multiple records. 

PUT is usually used to update or replace an existing resource and is idempotent, meaning sending the same request multiple times should produce the same result.

## What is HashMap?

HashMap is a key-value data structure in Java. 

Internally, it uses the key’s hashCode() to calculate the bucket index, and if multiple keys are placed in the same bucket, it uses equals() to compare the actual keys. Therefore, if we override hashCode(), we should also override equals() to make HashMap work correctly.

Hashmap, Linkedhashmap, Treemap, Hashtable, Concurrenthashmap
