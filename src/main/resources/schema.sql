CREATE TABLE IF NOT EXISTS employees (
employee_id INT PRIMARY KEY ,
employee_name VARCHAR(50),
age INT
);

CREATE TABLE IF NOT EXISTS m_user (
user_id VARCHAR(50) PRIMARY KEY,
password VARCHAR(100),
user_name VARCHAR(100),
birthday DATE,
age INT,
marriage BOOLEAN,
role VARCHAR(50)
);

INSERT INTO employees (employee_id, employee_name, age)
VALUES(1, "kenji-kondo", 20);
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('kenji-kondo@gmail.com', 'password', 'kenji-kondo-name', '2999-01-01', 28, false, 'ROLE_ADMIN');