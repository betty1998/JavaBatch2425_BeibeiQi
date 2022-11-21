truncate table product;
truncate table lookup;
insert into product (name, quantity, totalprice)
values('Orange',200, 5.98);

insert into product (name, quantity, totalprice)
values('Apple',203, 5.97);

insert into product (name, quantity, totalprice)
values('Banana',300, 3.99);

insert into product (name, quantity, totalprice)
values('Tomatoes',180, 13.96);

insert into product (name, quantity, totalprice)
values('Squash',165, 3);

insert into product (name, quantity, totalprice)
values('Celery',132, 3.98);

insert into product (name, quantity, totalprice)
values('Cucumber',59, 2.29);

insert into product (name, quantity, totalprice)
values('Mushroom',211, 1.14);

insert into product (name, quantity, totalprice)
values('Milk',161, 7.98);

insert into product (name, quantity, totalprice)
values('Cheese',139, 9.99);

insert into product (name, quantity, totalprice)
values('Eggs',140, 7.00);

insert into product (name, quantity, totalprice)
values('Lemon',0, 0.90);

insert into lookup (id, type, name, value)
values(1, 'product_MESSAGE', 'product_CREATED', 'Successfully Created product');

insert into lookup (id, type, name, value)
values(2, 'product_MESSAGE', 'product_NOT_FOUND', 'Cannot Find product');

insert into lookup (id, type, name, value)
values(3, 'product_MESSAGE', 'product_DELETED', 'product is Deleted');