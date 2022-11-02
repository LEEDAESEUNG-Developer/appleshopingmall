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

select * from product;

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

insert into product (product_name, product_price, product_data, product_category, product_color, product_ap, product_ram,
                     product_storage, product_img01)
    value('M2 MacBook PRO', 1690000, '2022-07-08', 1, 'mid-night' , 'M2', '8GB', '256GB', 'm1.jpeg');

insert into product (product_name, product_price, product_data, product_category, product_color, product_ap,
                     product_ram,
                     product_storage, product_img01)
    value ('iPhone 13 Pro', 1342000, 20211008, 3, 'sierra-blue', 'A15', 6, 128,
           '/iPhone/13_Pro/iPhone_13_pro_sierra_blue.jpg');

insert into product (product_name, product_price, product_data, product_category, product_color, product_ap,
                     product_ram,
                     product_storage, product_img01)
    value ('iPad 6세대 Pro', 1729000, 20221026, 2, 'space-gray', 'm2', 8, 128,
           '/iPad/6/ipad_6th_12.9inch_pro_space_gray.jpeg');






select * from product;

select product_name, product_color, product_img01 from product;

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

select product_name, product_color, product_img01 from product where product_name = 'M1-MacBook-Pro';

/* 주문 테이블 */
insert into order_tbl(order_member_id, cart_id, check_stock, check_shipment) value (2, 27, false, false);
insert into order_tbl(order_member_id, cart_id, check_stock, check_shipment) value (2, 27, false, false);

insert into order_tbl(order_tbl_id, product_id, product_count, product_price, check_stock, check_shipment, address)
    VALUE (
    (select order_number_id from order_number where order_date = (select max(order_date) from order_number group by member_id) and member_id = 2),
           1, 1, (select product_price from product where product.product_id = 1), false, false, '경기도');
select * from order_tbl;


/* 주문 번호 테이블 */
insert into order_number(member_id) value (2);
select * from order_number;

/*
주문번호 프로세스
1. order_number 테이블 데이터 생성
2. 생성된 데이터를 가지고 order_tbl에 데이터 저장
*/
insert into order_number(member_id) value (2);
insert into order_tbl(order_tbl_id, member_id, product_id, product_count, product_price, check_stock, check_shipment, address)
    VALUE (
           (select order_number_id from order_number where order_date = (select max(order_date) from order_number group by member_id) and member_id = 2),
           2, 1, 6, (select product_price from product where product.product_id = 1), false, false, '경기도');
select * from order_number;
select * from order_tbl;


/*
< order_number_id를 최신 값을 가져오기 >
1. order_date 최신 값으로 가져오기
*/
select max(order_date) from order_number group by member_id = 2;

select * from order_number;
/* 최근 날짜로 주문번호를 가지고 옴 */
select order_number_id from order_number where order_date = (select max(order_date) from order_number group by member_id) and member_id = 2;


select * from order_tbl;
select (product_count * product_price) as '결제해야 할 금액' from order_tbl where order_tbl_id = 2;
