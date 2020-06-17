create table ORDERS
(
    id            int primary key auto_increment,
    location_code varchar(5) unique,
    location_name varchar(100),
    address_id    int not null
);

create table ITEMS
(
    id         int primary key auto_increment,
    product_id varchar(50) not null,
    number     int,
    order_id   int         not null
);

create table PRODUCTS
(
    id       int primary key auto_increment,
    category varchar(100),
    name     varchar(100),
    amount   int,
    info     varchar(100)
);