create table if not exists products
(
    product_id varchar(200) primary key,
    description varchar(200),
    title varchar(200),
    price varchar(10),
    discount varchar(20),
    final_price varchar(100)
    );