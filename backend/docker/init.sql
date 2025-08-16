CREATE DATABASE IF NOT EXISTS chat_app_db;

-- Drop user if already exists to avoid conflicts
DROP USER IF EXISTS 'chatapp'@'%';

-- Create user and allow connections from any host
CREATE USER 'chatapp'@'%' IDENTIFIED BY 'chatapppwd';

-- Grant full access to chatapp for the database
GRANT ALL PRIVILEGES ON chat_app_db.* TO 'chatapp'@'%' WITH GRANT OPTION;

-- Apply changes
FLUSH PRIVILEGES;

USE chat_app_db;

CREATE TABLE chat_conversation (
    conversation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE message (
    message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    role ENUM('USER', 'MODEL') NOT NULL,
    conversation_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (conversation_id) REFERENCES chat_conversation(conversation_id) ON DELETE CASCADE
);

CREATE TABLE chat_app_logs (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    conversation_id BIGINT,
    chat_request TEXT NULL,
    chat_response TEXT NULL,
    error_response TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    display_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50) DEFAULT 'USER',
    created_at DATETIME,
    updated_at DATETIME
);


