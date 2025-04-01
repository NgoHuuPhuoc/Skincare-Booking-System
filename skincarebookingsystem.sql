CREATE DATABASE skincare_booking;
USE skincare_booking;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    service_id INT,
    appointment_time DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (service_id) REFERENCES services(id)
);
SHOW TABLES;
 INSERT INTO users (username, password, email) VALUES 
('user1', 'password1', 'user1@example.com'),
('user2', 'password2', 'user2@example.com'),
('user3', 'password3', 'user3@example.com');
INSERT INTO services (name, price) VALUES 
('Dịch vụ chăm sóc da mặt', 200000),
('Dịch vụ massage mặt', 150000),
('Dịch vụ tẩy tế bào chết', 100000),
('Dịch vụ đắp mặt nạ', 120000);
INSERT INTO appointments (user_id, service_id, appointment_time) VALUES 
(1, 1, '2023-10-01 10:00:00'),
(2, 2, '2023-10-01 11:00:00'),
(1, 3, '2023-10-02 09:00:00'),
(3, 4, '2023-10-02 14:00:00');
SELECT * FROM users;
SELECT * FROM appointments;
SELECT * FROM services;
