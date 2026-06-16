# Homework 13 — Database Interview Scripts and SQL Practice


## 1. SQL vs NoSQL Database

SQL databases are relational databases that store data in structured tables with rows and columns. They use SQL to query data and usually support strong consistency, transactions, and relationships between tables.

NoSQL databases are non-relational databases, such as document databases, key-value stores, wide-column stores, or graph databases. They are usually more flexible and easier to scale horizontally, so they are often used for large-scale, high-throughput, or semi-structured data.

Example:

SQL: PostgreSQL, MySQL, Oracle
NoSQL: MongoDB, Redis, Cassandra, DynamoDB



## 2. What is Database Normalization?

Database normalization is the process of organizing database tables to reduce data redundancy and improve data integrity. It usually means splitting large tables into smaller related tables and using foreign keys to connect them.

For example, instead of storing department name repeatedly in every employee row, we can create a separate Department table and store only dept_id in the Employee table. This makes updates easier and prevents inconsistent data.



## 3. Vertical Scaling vs Horizontal Scaling

Vertical scaling means increasing the resources of one server, such as adding more CPU, memory, or storage. It is simple, but it has a physical limit because one machine cannot be upgraded forever.

Horizontal scaling means adding more server instances to handle more traffic. It is more flexible and commonly used in distributed systems, but it introduces complexity such as load balancing, data partitioning, and distributed consistency.



## 4. What is ACID?

ACID describes four important properties of database transactions: Atomicity, Consistency, Isolation, and Durability. These properties ensure that database operations are reliable and data remains correct even when errors or concurrent operations happen.

Atomicity means a transaction either fully succeeds or fully rolls back. Consistency keeps data valid, Isolation controls how concurrent transactions interact, and Durability means committed data will not be lost even after a system crash.



## 5. What is CAP?

CAP theorem says that in a distributed system, it is difficult to guarantee **Consistency, Availability, and Partition Tolerance** all at the same time. When a network partition happens, the system usually has to choose between consistency and availability.

Consistency means every read gets the latest data. Availability means every request gets a response, and Partition Tolerance means the system continues working even when network communication between nodes fails.


#  SQL Query Practice — PostgreSQL Console

## 1. Create Tables

```sql
CREATE TABLE Department (
    dept_id SERIAL PRIMARY KEY, /* SERIAL == AUTO_INCREMENT */
    name VARCHAR(100) NOT NULL
);
CREATE TABLE Employee (
    emp_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dept_id INT,
    age INT,
    salary NUMERIC(10, 2),
    /* Foreign Key Constraint: The dept_id in the Employee table 
                            must come from the dept_id in the Department table. */
    CONSTRAINT fk_department 
        FOREIGN KEY (dept_id)
        REFERENCES Department(dept_id)
);
```

## 2. Insert Dummy Data
```sql
/* (col_name) */
/* insert 4 departments to department table */
INSERT INTO Department (name) VALUES
('Engineering'),
('Finance'),
('Human Resources'),
('Marketing');

INSERT INTO Employee (name, dept_id, age, salary) VALUES
('Alice', 1, 28, 75000),
('Bob', 1, 35, 95000),
('Charlie', 2, 32, 85000),
('David', 2, 45, 120000),
('Eva', 3, 26, 65000),
('Frank', 4, 31, 80000),
('Grace', 1, 29, 95000),
('Helen', 4, 38, 110000);
```

## 3. Query All Data


### a. Get Only Employee Names and Ages

```sql
SELECT name, age
FROM Employee;
```


### b. Find Employees Older Than 30

```sql
SELECT *
FROM Employee
WHERE age > 30;
```

### c. Find Employees Whose Salary Is Greater Than 80,000

```sql
SELECT *
FROM Employee
WHERE salary > 80000;
```


### d. List Employees Ordered by Age Ascending

```sql
SELECT *
FROM Employee
ORDER BY age ASC; /* ASC DEC */
```


### e. Get the Top 3 Highest-Paid Employees

```sql
SELECT *
FROM Employee
ORDER BY salary DESC
LIMIT 3;
```


### f. Count Total Number of Employees

```sql
/* total_employees is an alias for the result column */
SELECT COUNT(*) AS total_employees
FROM Employee;
```


### g. Find the Average Salary of All Employees

```sql
SELECT AVG(salary) AS average_salary
FROM Employee;
```


### h. List Employee Name with Department Name

```sql
SELECT e.name AS employee_name,
       d.name AS department_name
FROM Employee e
JOIN Department d
ON e.dept_id = d.dept_id;
```

JOIN ON == INNER JOIN ON: returns only records that match in both tables.

LEFT JOIN ON: keeps all records from the left table. If no matching record is found in the right table, the right table columns will be NULL.

RIGHT JOIN ON: keeps all records from the right table. If no matching record is found in the left table, the left table columns will be NULL.


### i. Find Employees Earning the Highest Salary

```sql
SELECT *
FROM Employee
WHERE salary = (
    SELECT MAX(salary)
    FROM Employee
);
```

This query also handles ties. If multiple employees have the same highest salary, it returns all of them.

```sql
SELECT *
FROM Employee
ORDER BY salary DESC, emp_id ASC
LIMIT 1;
```

This query returns only one result. If multiple records have the same salary, it chooses the one with the largest emp_id.


### j. Find Employees Earning the Second Highest Salary

Method 1: Using DENSE_RANK()

```sql
SELECT emp_id, name, dept_id, age, salary
FROM (
    SELECT *,
           DENSE_RANK() OVER (ORDER BY salary DESC) AS salary_rank
    FROM Employee
) ranked
WHERE salary_rank = 2;
```

This handles duplicate salaries correctly. If two employees share the highest salary, the next different salary is still ranked second.

Method 2: Using Subquery
```sql
SELECT *
FROM Employee
WHERE salary = (
    SELECT MAX(salary)
    FROM Employee
    WHERE salary < (
        SELECT MAX(salary)
        FROM Employee
    )
);
```


### k. Find Employees Earning the Third Highest Salary

Method 1: Using DENSE_RANK()

```sql
SELECT emp_id, name, dept_id, age, salary
FROM (
    SELECT *,
           DENSE_RANK() OVER (ORDER BY salary DESC) AS salary_rank
    FROM Employee
) ranked
WHERE salary_rank = 3;
```

Method 2: Using OFFSET

```sql
SELECT DISTINCT salary
FROM Employee
ORDER BY salary DESC
LIMIT 1 OFFSET 2;
```

This only returns the third highest **distinct** salary. DISTINCT: remove duplicate

To return employees with the third highest salary:

```sql
SELECT *
FROM Employee
WHERE salary = (
    SELECT DISTINCT salary
    FROM Employee
    ORDER BY salary DESC
    LIMIT 1 OFFSET 2
);
```


