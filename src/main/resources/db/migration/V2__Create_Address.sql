CREATE TABLE Address (
    id SERIAL PRIMARY KEY,
    street_address VARCHAR(255),
    city VARCHAR(100),
    postal_code VARCHAR(10),
    neighborhood VARCHAR(100),
    house_number VARCHAR(10)
);
