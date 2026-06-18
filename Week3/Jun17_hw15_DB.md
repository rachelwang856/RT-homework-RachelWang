# JDBC vs Hibernate

JDBC is a low-level Java API that allows developers to execute SQL directly and interact with the database manually. Hibernate is an ORM framework built on top of JDBC that automatically maps Java objects to database tables, reducing boilerplate code and improving developer productivity.

---

# Statement vs PreparedStatement vs CallableStatement


`Statement` executes static SQL queries, `PreparedStatement` executes precompiled parameterized SQL queries, and `CallableStatement` is used to execute stored procedures. In practice, `PreparedStatement` is preferred because it improves performance through query precompilation and helps prevent SQL injection attacks.

---

# How to Prevent SQL Injection

 

SQL injection occurs when user input is concatenated directly into SQL statements, allowing attackers to modify the query. The most common prevention technique is using `PreparedStatement`, which separates SQL code from user input and treats parameters as data rather than executable SQL.

---

# What is ORM?

 

ORM (Object-Relational Mapping) is a technique that maps Java objects to database tables and table rows to object instances. It allows developers to work with objects instead of writing SQL for every database operation, improving maintainability and development speed.

---

# JPA vs Hibernate

 

JPA (Java Persistence API) is a specification that defines standard ORM interfaces, while Hibernate is a popular implementation of the JPA specification. In most Spring Boot applications, developers program against JPA interfaces while Hibernate performs the actual persistence operations underneath.

---

# What Are the Persistent States in the Entity Lifecycle?

 

A JPA entity typically goes through four states: Transient, Persistent, Detached, and Removed. Understanding these states is important because they determine whether Hibernate is tracking changes and whether updates will be synchronized to the database automatically.

```text
Transient
    ↓ persist()
Persistent
    ↓ detach() / session close
Detached
    ↓ remove()
Removed
```

---

# Mapping Relationships

 

JPA supports relationships between entities through annotations such as `@OneToOne`, `@OneToMany`, `@ManyToOne`, and `@ManyToMany`. These mappings allow Hibernate to automatically manage foreign key relationships and simplify database operations.

### Common Relationships

```text
One-to-One
User ↔ Profile

One-to-Many
Department → Employees

Many-to-One
Employee → Department

Many-to-Many
Students ↔ Courses
```

---

# What is Cascade Type?

 

Cascade types define whether operations performed on a parent entity should automatically propagate to its related child entities. For example, `CascadeType.PERSIST` automatically saves child entities when the parent entity is saved.

### Common Cascade Types

```java
CascadeType.PERSIST
CascadeType.MERGE
CascadeType.REMOVE
CascadeType.ALL
```

Example:

```text
Save Department
      ↓
Automatically Save Employees
```

---

# What is Fetch Type?

 

Fetch type determines when related entities are loaded from the database. `EAGER` loads related data immediately, while `LAZY` delays loading until the relationship is actually accessed, which can significantly improve performance.

### Example

```text
Department
    ↓
Employees
```

EAGER:

```text
Load Department
Load Employees Immediately
```

LAZY:

```text
Load Department
Load Employees Only When Needed
```

---

# What is the First-Level / Second-Level Cache?

 

The first-level cache is the Session-level cache that is enabled by default and exists for the lifetime of a Hibernate Session. The second-level cache is shared across multiple Sessions and can reduce database access by reusing frequently accessed data.

### Cache Hierarchy

```text
Application
    ↓
Second-Level Cache
    ↓
Session Cache (First-Level Cache)
    ↓
Database
```

---

# LEFT JOIN vs RIGHT JOIN vs INNER JOIN vs OUTER JOIN vs CROSS JOIN

 

Joins combine rows from multiple tables based on a relationship. The main difference is how unmatched rows are handled.

### INNER JOIN

Returns only matching rows.

```text
A ∩ B
```

### LEFT JOIN

Returns all rows from the left table and matching rows from the right table.

```text
All A + Matching B
```

### RIGHT JOIN

Returns all rows from the right table and matching rows from the left table.

```text
All B + Matching A
```

### FULL OUTER JOIN

Returns all rows from both tables, including unmatched rows.

```text
All A + All B
```

### CROSS JOIN

Returns the Cartesian product of both tables.

```text
A × B
```

Every row in A is combined with every row in B.

---

# UNION vs UNION ALL

 

Both `UNION` and `UNION ALL` combine the results of multiple SELECT statements. `UNION` removes duplicate rows, while `UNION ALL` keeps duplicates and is usually faster because no deduplication step is required.

### Example

Table A:

```text
1
2
3
```

Table B:

```text
3
4
5
```

UNION:

```text
1
2
3
4
5
```

UNION ALL:

```text
1
2
3
3
4
5
```