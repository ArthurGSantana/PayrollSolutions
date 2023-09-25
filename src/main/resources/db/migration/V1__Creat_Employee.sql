CREATE TABLE Employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    document VARCHAR(20) UNIQUE,
    job_title VARCHAR(50),
    base_salary DECIMAL(10, 2),
    phone VARCHAR(20),
    birth_date DATE,
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES Address(id)
);