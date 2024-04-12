CREATE DATABASE IF NOT EXISTS `project` DEFAULT CHARACTER SET utf8mb4;

CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL ON `project`.* TO 'root'@'localhost' WITH GRANT OPTION;
GRANT ALL ON `boards`.* TO 'root'@'localhost';
GRANT ALL ON `comments`.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

USE `project`;

CREATE TABLE IF NOT EXISTS `boards` (
        board_id bigint auto_increment primary key,
        is_delete boolean not null,
        created_at timestamp,
        last_modified_at timestamp,
        content varchar(255),
        title varchar(255)
    );

CREATE TABLE IF NOT EXISTS `comments` (
        comment_id bigint auto_increment primary key,
        is_delete boolean not null,
        board_id bigint,
        content varchar(255),
        foreign key (board_id) references `boards`(board_id)
    );
