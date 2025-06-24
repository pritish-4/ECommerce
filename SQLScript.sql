CREATE DATABASE ecommjava;
USE ecommjava;
CREATE TABLE products (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE,
    stock INT
);
CREATE TABLE orders (
    order_id VARCHAR(20) PRIMARY KEY,
    user_id VARCHAR(20),
    total_amount DOUBLE,
    status VARCHAR(20)
);
CREATE TABLE users (
    user_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);
INSERT INTO products (id, name, price, stock) VALUES
('P001', 'Boat Rockerz 255 Pro+ Earphones', 1299.00, 50),
('P002', 'MI Power Bank 20000mAh', 1999.00, 30),
('P003', 'Safari Backpack 35L', 899.00, 25),
('P004', 'Ambrane Wireless Mouse', 499.00, 100),
('P005', 'HP USB Keyboard', 799.00, 60),
('P006', 'Campus Running Shoes', 1599.00, 40),
('P007', 'Prestige Electric Kettle', 1149.00, 20),
('P008', 'Tata Tea Gold 1kg Pack', 450.00, 80),
('P009', 'Aashirvaad Atta 10kg', 425.00, 70),
('P010', 'Classmate Spiral Notebook A4', 85.00, 200);
INSERT INTO users (user_id, name, email) VALUES
('U10001', 'Pritish Raj', 'pritish@example.com'),
('U10002', 'Sayandip Bhattacharyya', 'sayandip.bhattacharyya@example.com');
SELECT * FROM users;