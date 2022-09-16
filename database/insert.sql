use appleShopingMall;

/* member table 문제점 email 유니크 속성을 못 걸음...*/
desc member;

insert into member(member_firstname, member_address, member_birthday, member_email, member_pwd, member_phonenumber)
values ('test', '경기도 수원시', now(), 'test', '1234', '000-0000-0000');

select * from member;

/* product_catagory table */
select * from product_catagory;

insert into product_catagory(catagory_subject)
values ('MAC'),
       ('iPad'),
       ('iPhone'),
       ('Watch'),
       ('airPods');

/* product table */
desc product;

insert into product (product_name, product_price, product_data, product_category, product_ap, product_ram,
                     product_storage, product_img01)
value('M1 MacBook Air', 1390000, '2020-11-10', 1, 'M1', '8GB', '256GB', 'm1.jpeg');

insert into product (product_name, product_price, product_data, product_category, product_color, product_ap, product_ram,
                     product_storage, product_img01)
value('M1 MacBook Air', 1390000, '2020-11-10', 1, 'silver', 'M1', '8GB', '256GB', 'm1.jpeg');

insert into product (product_name, product_price, product_data, product_category, product_color, product_ap, product_ram,
                     product_storage, product_img01)
value('M1 MacBook Air', 1390000, '2020-11-10', 1, 'gold', 'M1', '8GB', '256GB', 'm1.jpeg');

insert into product (product_name, product_price, product_data, product_category, product_ap, product_ram,
                     product_storage, product_img01)
value('M1 MacBook PRO', 1690000, '2020-11-10', 1, 'M1', '8GB', '256GB', 'm1.jpeg');

insert into product (product_name, product_price, product_data, product_category, product_color, product_ap, product_ram,
                     product_storage, product_img01)
value('M1 MacBook PRO', 1690000, '2020-11-10', 1, 'silver' , 'M1', '8GB', '256GB', 'm1.jpeg');

select * from product;

/* product_cart */
insert into product_cart(member_id, product_id, product_count)
    values(2, 2, 4);

select * from product_cart where member_id = 2;

show tables;

select * from product where product.product_id in (select product_id from product_cart where member_id = 2);

select * from product_cart;

insert into color(product_id, product_color) value(2,'space-gray');

select * from product;

select * from color;