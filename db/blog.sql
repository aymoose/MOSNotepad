drop database if exists blogdemo;

create database blogdemo default charset utf8mb4;

use blogdemo;

drop table if exists user;

create table user
(
    id          int primary key auto_increment,
    name        varchar(20),
    username    varchar(20),
    password    varchar(20),
    create_time timestamp
);


drop table if exists article;

create table article
(
    id          int primary key auto_increment,
    title       varchar(50) not null,
    content     mediumtext  not null,
    user_id     int,
    create_time timestamp,
    foreign key (user_id) references user (id)
);