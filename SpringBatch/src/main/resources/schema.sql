create table if not exists products
(
    product_id int primary key,
    description varchar(200),
    title varchar(200),
    price varchar(10),
    discount varchar(2),
    discounted_price varchar(10)
    );