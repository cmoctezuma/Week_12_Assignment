

CREATE DATABASE IF NOT EXISTS dominoesDB;

USE dominoesDB;

create table if not exists customer (
id int not null auto_increment,
customer_name varchar(120) not null,
address varchar(120) not null,
phone_number varchar(120) not null,
primary key(id)
);
​
create table restaurant(
id int not null auto_increment,
order_id int not null,
location_name varchar(120) not null,
location varchar(120) not null,
phone varchar(120) not null,
primary key(id)
-- foreign key(order_id) references customerOrder(id)
);
alter table restaurant add foreign key (order_id) REFERENCES customerOrder(id);

create table customerOrder (
id int not null auto_increment,
customer_id int not null,
restaurant_id int not null,
item_name varchar(120) not null,
quantity int not null,
price double not null,
primary key (id),
foreign key (customer_id) references customer(id),
foreign key (restaurant_id) references restaurant(id)
);

create table items (
id int not null auto_increment,
customer_order_id int not null,
item varchar(120) not null,
description varchar(120) not null,
price double not null,
primary key (id),
foreign key (customer_order_id) references customerOrder(id)
);

show tables;