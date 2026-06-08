-- Create Database
CREATE DATABASE IF NOT EXISTS crud_db;
USE crud_db;

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    address VARCHAR(500),
    phone_number VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert Sample Data
INSERT INTO users (username, email, first_name, last_name, address, phone_number) 
VALUES 
('john_doe', 'john@example.com', 'John', 'Doe', '123 Main St', '1234567890'),
('jane_smith', 'jane@example.com', 'Jane', 'Smith', '456 Oak Ave', '0987654321'),
('bob_johnson', 'bob@example.com', 'Bob', 'Johnson', '789 Pine Rd', '5555551234'),
('alice_williams', 'alice@example.com', 'Alice', 'Williams', '321 Elm St', '5555555678'),
('charlie_brown', 'charlie@example.com', 'Charlie', 'Brown', '654 Maple Dr', '5555559999');

-- Show created table
DESCRIBE users;
