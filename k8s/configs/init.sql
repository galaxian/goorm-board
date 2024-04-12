CREATE SCHEMA IF NOT EXISTS `project` DEFAULT CHARACTER SET utf8mb4;

GRANT ALL ON *.* TO 'root'@'localhost' IDENTIFIED BY 'root' WITH GRANT OPTION;
GRANT ALL ON `boards`.* TO 'root'@'localhost';
GRANT ALL ON `comments`.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

USE `project`;

CREATE TABLE IF NOT EXISTS `boards` (
    `is_delete` BOOLEAN NOT NULL,
    `board_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `last_modified_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `content` VARCHAR(255),
    `title` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `comments` (
    `is_delete` BOOLEAN NOT NULL,
    `board_id` BIGINT,
    `comment_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `content` VARCHAR(255),
    FOREIGN KEY (`board_id`) REFERENCES `boards`(`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
