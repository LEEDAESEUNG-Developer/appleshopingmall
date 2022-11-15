use appleShopingMall;

/* 상품 테이블*/
alter table product add product_Content_img01 varchar(50);
alter table product add product_Color varchar(50) after product_category;

alter table product drop product_Color;

alter table product add product_operating_system varchar(50) after product_ram;

alter table product add product_stock int default 0 after product_img01;
alter table product change product_stock product_stock int default 0;

select * from product;

desc product;

alter table product change product_Color product_color varchar(50);

alter table product change product_data product_data date default now();

alter table product drop product_img04;

alter table product add product_img04 varchar(50) after product_img03;

select *
from product;

alter table product change product_Content_img01 product_Content_img01 varchar(100);

/* 회원 테이블*/
select * from member;
desc member;
alter table member change member_email member_email varchar(50) not null unique;
alter table member add position varchar(30) default 'USER';

/* 주문 테이블 */
use appleShopingMall;
show tables;
desc order_tbl;
select * from order_tbl;

alter table order_tbl modify order_tbl_id int not null;

/* 주문 번호 테이블 */
select * from order_number;
alter table order_number add order_date DATETIME default now();

desc order_number;
alter table order_number modify member_id int not null;

desc product;

select * from product;
alter table product drop product_data;
alter table product add product_data date default (current_date) after product_price;
