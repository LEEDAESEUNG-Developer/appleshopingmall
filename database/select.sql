use appleShopingMall;

select *
from product p,
     product_cart c
where p.product_id = c.product_id and c.member_id = 2;

select * from product_cart where member_id = 2;
select sum(product_count) from product_cart where member_id = 2;

select * from product_cart;

select product_price from product where product_id = 1; /* 가격 */

select product_count from product_cart where member_id = 2 and product_id = 1;

alter table product_cart add column product_price int not null after product_id;
alter table product_cart drop column product_price;

/* 총 토탈 계산 */
select sum(product_price * product_count) from product_cart where member_id = 2;