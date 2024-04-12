create SCHEMA IF NOT EXISTS `boards` DEFAULT CHARACTER SET utf8mb4;

grant all on *.* to 'root'@'localhost' IDENTIFIED BY 'root' with grant OPTION;
grant all on boards.* TO 'root'@'localhost';
grant all on comments.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

USE `boards`;

drop table if exists `boards`;
create table `boards` (
        is_delete boolean not null,
        board_id bigint auto_increment primary key,
        created_at timestamp,
        last_modified_at timestamp,
        content varchar(255),
        title varchar(255)
    ) engine=InnoDB DEFAULT CHARSET=utf8mb4;

drop table if exists `comments`;
create table `comments` (
        is_delete boolean not null,
        board_id bigint,
        comment_id bigint auto_increment primary key,
        content varchar(255),
        foreign key (board_id) references `boards`(board_id)
    ) engine=InnoDB DEFAULT CHARSET=utf8mb4;
