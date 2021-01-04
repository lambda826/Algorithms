--Write a SQL query to get the second highest salary from the Employee table.
--
--+----+--------+
--| Id | Salary |
--+----+--------+
--| 1  | 100    |
--| 2  | 200    |
--| 3  | 300    |
--+----+--------+
--For example, given the above Employee table, the query should return 200 as the second highest salary. If there is no second highest salary, then the query should return null.
--
--+---------------------+
--| SecondHighestSalary |
--+---------------------+
--| 200                 |
--+---------------------+




# Write your MySQL query statement below
-- LIMIT start, offset
SELECT IFNULL ((SELECT DISTINCT SALARY FROM EMPLOYEE ORDER BY SALARY DESC LIMIT 1,1), NULL) AS 'SecondHighestSalary';
