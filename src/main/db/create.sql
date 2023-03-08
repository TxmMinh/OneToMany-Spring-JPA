create database onetomany;

use onetomany;

create table cart (
    cart_id int auto_increment primary key
);

create table items  (
    id int auto_increment primary key,
    name varchar(50),
    cart_id int,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id)
)