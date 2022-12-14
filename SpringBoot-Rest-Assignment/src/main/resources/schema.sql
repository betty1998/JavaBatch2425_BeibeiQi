create table IF NOT EXISTS product
(
   id integer not null auto_increment,
   name varchar(255) not null,
   quantity integer not null,
   itemprice double not null,
   primary key(id)
);

create table IF NOT EXISTS lookup
(
   id integer not null,
   type varchar(255) not null,
   name varchar(255) not null,
   value varchar(255) not null,
   primary key(id)
);