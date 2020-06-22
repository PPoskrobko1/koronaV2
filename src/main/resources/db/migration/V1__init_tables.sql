create table ORDERS
(
    id      int primary key auto_increment,
    point_x int not null,
    point_y int not null
);

create table ITEMS
(
    id             int auto_increment,
    product_code   varchar(50) not null,
    quantity       int,
    warehouse_code varchar(30),
    order_id       int,
    primary key (id),
    foreign key (order_id) references ORDERS(id)

);
