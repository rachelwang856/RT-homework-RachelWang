# 1. What is an Index?

## Interview Answer

An **index** is a data structure that improves the speed of data retrieval by allowing the database to find rows without scanning the entire table. It works like a book index, helping the database locate data efficiently.

### Example

```sql
CREATE INDEX idx_email
ON users(email);
```

Without index:

```text
Full Table Scan
O(N)
```

With index:

```text
Index Lookup
O(log N)
```

### Benefits

- Faster SELECT queries
- Faster WHERE filtering
- Faster JOIN operations
- Faster ORDER BY and GROUP BY

### Trade-offs

- Extra storage space
- Slower INSERT/UPDATE/DELETE because indexes must be maintained

---

# 2. Clustered Index vs Non-Clustered Index

## Interview Answer

A **clustered index** determines the physical order of data in the table, while a **non-clustered index** stores a separate structure containing indexed columns and pointers to the actual rows.

### Clustered Index

```text
Clustered Index (Primary Key)

1 Alice
2 Bob
3 Charlie
4 David
```

Characteristics:

- Only one per table
- Usually created on the Primary Key
- Excellent for range queries

Example:

```sql
SELECT *
FROM employees
WHERE id BETWEEN 100 AND 200;
```

### Non-Clustered Index

```text
Index:
alice@gmail.com → Row 101
bob@gmail.com   → Row 205
```

Characteristics:

- Multiple per table
- Additional storage required
- May require an extra lookup to fetch the row

Example:

```sql
CREATE INDEX idx_email
ON employees(email);
```

### Comparison

| Feature | Clustered | Non-Clustered |
|----------|----------|----------|
| Physical data order | Yes | No |
| Number per table | One | Multiple |
| Range query performance | Excellent | Good |
| Storage overhead | Lower | Higher |

---

# 3. What Data Structure Is Used for Indexes?

## Interview Answer

Most relational databases use a **B+ Tree** for indexes because it supports efficient lookups, insertions, deletions, and range scans.

### Why Not Hash Table?

Hash indexes are efficient for equality searches:

```sql
WHERE id = 100
```

But inefficient for range queries:

```sql
WHERE id BETWEEN 100 AND 200
```

### Why B+ Tree?

- O(log N) lookup
- Ordered data
- Efficient range queries
- Optimized for disk I/O

Structure:

```text
Root
 |
 +-- Internal Node
 |      |
 |      +-- Leaf
 |
 +-- Internal Node
        |
        +-- Leaf
```

Leaf nodes are linked:

```text
Leaf1 <-> Leaf2 <-> Leaf3
```

which makes range scans very efficient.

---

# 4. View vs Stored Procedure

## Interview Answer

A **View** is a virtual table based on a SQL query, while a **Stored Procedure** is a reusable database program that can contain SQL statements and business logic.

### View

```sql
CREATE VIEW active_users AS
SELECT *
FROM users
WHERE status = 'ACTIVE';
```

Usage:

```sql
SELECT *
FROM active_users;
```

Characteristics:

- Virtual table
- Simplifies complex queries
- Usually read-only

### Stored Procedure

```sql
CREATE PROCEDURE GetUser(IN uid INT)
BEGIN
    SELECT *
    FROM users
    WHERE id = uid;
END;
```

Characteristics:

- Supports parameters
- Can contain IF, LOOP, and business logic
- Can modify data

### Comparison

| Feature | View | Stored Procedure |
|----------|----------|----------|
| Returns table | Yes | Can |
| Accept parameters | No | Yes |
| Contains logic | No | Yes |
| Update data | Usually No | Yes |

---

# 5. View vs Materialized View

## Interview Answer

A **View** executes the underlying query every time it is accessed, while a **Materialized View** stores the query result physically and refreshes periodically.

### View

```sql
SELECT *
FROM sales_view;
```

The underlying query executes every time.

### Materialized View

```sql
CREATE MATERIALIZED VIEW sales_summary AS
SELECT department,
       SUM(amount)
FROM sales
GROUP BY department;
```

The result is stored physically.

### Benefits

- Much faster reads
- Useful for analytics and reporting

### Trade-offs

- Consumes storage
- Data may become stale

Refresh manually:

```sql
REFRESH MATERIALIZED VIEW sales_summary;
```

### Comparison

| Feature | View | Materialized View |
|----------|----------|----------|
| Stores data physically | No | Yes |
| Query speed | Slower | Faster |
| Always up-to-date | Yes | Not necessarily |
| Storage required | No | Yes |

---

# 6. How Do You Tune a SQL Query?

## Interview Answer

I typically use **EXPLAIN** to analyze the execution plan, identify bottlenecks such as full table scans or expensive joins, and then optimize using indexes, query rewrites, or schema improvements.

### Common Techniques

### 1. Add Indexes

```sql
CREATE INDEX idx_email
ON users(email);
```

### 2. Avoid SELECT *

Bad:

```sql
SELECT *
FROM users;
```

Good:

```sql
SELECT id, name
FROM users;
```

### 3. Filter Early

```sql
SELECT *
FROM orders
WHERE status = 'PAID';
```

### 4. Optimize JOINs

Ensure join columns are indexed.

```sql
users(id)
orders(user_id)
```

### 5. Analyze EXPLAIN Output

Bad:

```text
type = ALL
```

means Full Table Scan.

Better:

```text
type = const
type = ref
type = range
```

### Typical Optimization Workflow

```text
EXPLAIN
    ↓
Identify bottleneck
    ↓
Add index / Rewrite SQL
    ↓
Run EXPLAIN again
    ↓
Compare performance
```

---

# 7. Saga vs 2PC

## Interview Answer

Both Saga and 2PC are used to maintain consistency across distributed systems.

### 2PC (Two-Phase Commit)

Process:

```text
Coordinator
    |
Prepare
    |
Commit
```

Phase 1:

```text
Can everyone commit?
```

Phase 2:

```text
Commit all
```

Advantages:

- Strong consistency
- Atomic transactions

Disadvantages:

- Blocking protocol
- Poor scalability
- Coordinator can become a bottleneck

### Saga

Break a distributed transaction into multiple local transactions.

Example:

```text
Create Order
    ↓
Reserve Inventory
    ↓
Charge Payment
```

If payment fails:

```text
Compensate Inventory
    ↓
Cancel Order
```

Advantages:

- High scalability
- Better availability
- Common in microservices

Disadvantages:

- Eventual consistency
- More complex business logic

### Comparison

| Feature | Saga | 2PC |
|----------|----------|----------|
| Consistency | Eventual | Strong |
| Scalability | High | Low |
| Blocking | No | Yes |
| Microservices | Preferred | Rare |

---

# 8. Average Salary Per Department + EXPLAIN

## SQL

```sql
SELECT department,
       AVG(salary) AS avg_salary
FROM employees
WHERE age > 30
GROUP BY department
ORDER BY avg_salary DESC;
```

## Logical Execution Order

```text
FROM employees
    ↓
WHERE age > 30
    ↓
GROUP BY department
    ↓
AVG(salary)
    ↓
ORDER BY avg_salary DESC
    ↓
SELECT
```

## Possible Execution Plan

```sql
EXPLAIN
SELECT department,
       AVG(salary) AS avg_salary
FROM employees
WHERE age > 30
GROUP BY department
ORDER BY avg_salary DESC;
```

Possible output:

```text
1. Index Range Scan on age
2. Filter age > 30
3. Group By department
4. Compute AVG(salary)
5. Sort avg_salary DESC
6. Return result
```

Without an index:

```text
Full Table Scan
    ↓
Filter
    ↓
Group By
    ↓
Sort
```

### Optimization

Create a composite index:

```sql
CREATE INDEX idx_age_department
ON employees(age, department);
```

Benefits:

- Faster filtering on age
- Faster grouping on department
- Fewer rows scanned
