-- =========================================================
-- TOPS Technologies - Assessment File
-- =========================================================

-- =========================================================
-- SECTION A : CONCEPT APPLICATION
-- =========================================================

/*
1. Relational databases maintain accuracy by organizing data into related
   tables and enforcing relationships using primary and foreign keys.

2. Constraints are important because they ensure valid and reliable data.
   Example:
   - PRIMARY KEY prevents duplicate records
   - FOREIGN KEY maintains valid relationships
   - UNIQUE prevents duplicate emails

3. GROUP BY helps analyze spending patterns by grouping records and
   applying aggregate functions like SUM() and COUNT().

4. ROLLBACK is required when incorrect expense entries are made during
   a transaction and changes need to be undone.

5. Views simplify complex queries and help users efficiently track
   monthly or category-wise expenses.

6. Triggers automate actions such as updating balances or assigning
   categories after INSERT, UPDATE, or DELETE operations.
*/


-- =========================================================
-- SECTION B : SQL HANDS-ON
-- =========================================================

CREATE TABLE users (
    user_id INT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100),
    created_at DATE
);

CREATE TABLE categories (
    category_id INT PRIMARY KEY,
    category_name VARCHAR(50)
);

CREATE TABLE expenses (
    expense_id INT PRIMARY KEY,
    user_id INT,
    category_id INT,
    amount DECIMAL(10,2),
    expense_date DATE,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);


-- =========================================================
-- 1. DDL UNDERSTANDING
-- =========================================================

/*
Why are foreign keys used?

Foreign keys maintain referential integrity between tables.
The user_id in expenses must exist in users table and
category_id must exist in categories table.

Issue if foreign keys are removed:

Orphaned records may occur.
Example:
An expense may exist for a user_id that no longer exists
in the users table.
*/


-- =========================================================
-- 2. DML OPERATIONS
-- =========================================================


-- ---------------------------------------------------------
-- INSERT 5 USERS
-- ---------------------------------------------------------

INSERT INTO users VALUES
(1, 'Amit Shah', 'amit@gmail.com', '2026-01-10'),
(2, 'Neha Patel', 'neha@gmail.com', '2026-01-12'),
(3, 'Raj Mehta', 'raj@gmail.com', '2026-01-15'),
(4, 'Priya Desai', 'priya@gmail.com', '2026-01-20'),
(5, 'Karan Joshi', 'karan@gmail.com', '2026-01-25');


-- ---------------------------------------------------------
-- INSERT 3 CATEGORIES
-- ---------------------------------------------------------

INSERT INTO categories VALUES
(1, 'Food'),
(2, 'Rent'),
(3, 'Entertainment');


-- ---------------------------------------------------------
-- INSERT 10 EXPENSE RECORDS
-- ---------------------------------------------------------

INSERT INTO expenses VALUES
(101, 1, 1, 250.00, '2026-02-01'),
(102, 1, 2, 5000.00, '2026-02-02'),
(103, 2, 1, 300.00, '2026-02-03'),
(104, 2, 3, 800.00, '2026-02-04'),
(105, 3, 2, 4500.00, '2026-02-05'),
(106, 3, 1, 150.00, '2026-02-06'),
(107, 4, 3, 1200.00, '2026-02-07'),
(108, 4, 1, 400.00, '2026-02-08'),
(109, 5, 2, 6000.00, '2026-02-09'),
(110, 5, 3, 900.00, '2026-02-10');


-- ---------------------------------------------------------
-- UPDATE ONE INCORRECT EXPENSE
-- ---------------------------------------------------------

UPDATE expenses
SET amount = 350.00
WHERE expense_id = 103;


-- ---------------------------------------------------------
-- DELETE ONE EXPENSE
-- ---------------------------------------------------------

DELETE FROM expenses
WHERE amount < 200;


-- =========================================================
-- 3. DATA RETRIEVAL
-- =========================================================


-- ---------------------------------------------------------
-- DISPLAY ALL EXPENSES WITH DETAILS
-- ---------------------------------------------------------

SELECT 
    e.expense_date,
    e.amount,
    u.name,
    c.category_name
FROM expenses e
INNER JOIN users u
ON e.user_id = u.user_id
INNER JOIN categories c
ON e.category_id = c.category_id;


-- ---------------------------------------------------------
-- SHOW TOTAL EXPENSE AMOUNT PER CATEGORY
-- ---------------------------------------------------------

SELECT 
    c.category_name,
    SUM(e.amount) AS total_expense
FROM expenses e
INNER JOIN categories c
ON e.category_id = c.category_id
GROUP BY c.category_name;


-- ---------------------------------------------------------
-- DISPLAY USERS SORTED BY TOTAL SPENDING
-- ---------------------------------------------------------

SELECT 
    u.name,
    SUM(e.amount) AS total_spending
FROM users u
INNER JOIN expenses e
ON u.user_id = e.user_id
GROUP BY u.name
ORDER BY total_spending DESC;


-- =========================================================
-- 4. VIEWS
-- =========================================================


-- ---------------------------------------------------------
-- CREATE VIEW : ActiveUsersView
-- ---------------------------------------------------------

CREATE VIEW ActiveUsersView AS
SELECT 
    u.name,
    u.email
FROM users u
INNER JOIN expenses e
ON u.user_id = e.user_id
GROUP BY u.user_id, u.name, u.email
HAVING COUNT(e.expense_id) > 5;


-- ---------------------------------------------------------
-- QUERY THE VIEW
-- ---------------------------------------------------------

SELECT * FROM ActiveUsersView;


-- =========================================================
-- SECTION C : MINI PROJECT
-- Expense Tracker DB
-- =========================================================


-- =========================================================
-- CRUD OPERATIONS
-- =========================================================


-- ---------------------------------------------------------
-- CREATE
-- ---------------------------------------------------------

INSERT INTO expenses VALUES
(111, 1, 1, 500.00, '2026-02-15');


-- ---------------------------------------------------------
-- READ
-- ---------------------------------------------------------

SELECT * FROM expenses;


-- ---------------------------------------------------------
-- UPDATE
-- ---------------------------------------------------------

UPDATE expenses
SET amount = 700.00
WHERE expense_id = 111;


-- ---------------------------------------------------------
-- DELETE
-- ---------------------------------------------------------

DELETE FROM expenses
WHERE expense_id = 111;


-- =========================================================
-- STORED PROCEDURE
-- CALCULATE MONTHLY USER EXPENSE
-- =========================================================

DELIMITER //

CREATE PROCEDURE GetMonthlyExpense(
    IN p_user_id INT,
    IN p_month INT,
    IN p_year INT
)
BEGIN

    SELECT 
        u.name,
        SUM(e.amount) AS total_monthly_expense
    FROM users u
    INNER JOIN expenses e
        ON u.user_id = e.user_id
    WHERE u.user_id = p_user_id
        AND MONTH(e.expense_date) = p_month
        AND YEAR(e.expense_date) = p_year
    GROUP BY u.name;

END //

DELIMITER ;


-- ---------------------------------------------------------
-- EXECUTE STORED PROCEDURE
-- ---------------------------------------------------------

CALL GetMonthlyExpense(1, 2, 2026);


-- =========================================================
-- COMMIT EXAMPLE
-- =========================================================

START TRANSACTION;

INSERT INTO expenses VALUES
(112, 2, 1, 650.00, '2026-02-20');

COMMIT;


-- =========================================================
-- ROLLBACK EXAMPLE
-- =========================================================

START TRANSACTION;

INSERT INTO expenses VALUES
(113, 3, 2, 9999.00, '2026-02-21');

ROLLBACK;


-- =========================================================
-- OPTIONAL TRIGGER EXAMPLE
-- =========================================================

/*
This trigger automatically prevents negative expense amounts.
*/

DELIMITER //

CREATE TRIGGER check_expense_amount
BEFORE INSERT ON expenses
FOR EACH ROW
BEGIN

    IF NEW.amount < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Expense amount cannot be negative';

    END IF;

END //

DELIMITER ;

