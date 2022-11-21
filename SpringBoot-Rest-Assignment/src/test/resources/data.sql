truncate table product;
truncate table lookup;
insert into product (name, quantity, totalprice)
values('Orange',200, 5.98);

insert into product (name, quantity, totalprice)
values('Lemon',0, 0.90);

insert into lookup (id, type, name, value)
values(1, 'product_MESSAGE', 'product_CREATED', 'Successfully Created product');

insert into lookup (id, type, name, value)
values(2, 'product_MESSAGE', 'product_NOT_FOUND', 'Cannot Find product');

insert into lookup (id, type, name, value)
values(3, 'product_MESSAGE', 'product_DELETED', 'product is Deleted');