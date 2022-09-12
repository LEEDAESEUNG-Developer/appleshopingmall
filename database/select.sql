use appleShopingMall;

select *
from product p,
     product_cart c
where p.product_id = c.product_id and c.member_id = 2;

select * from product_cart where member_id = 2;
select sum(product_count) from product_cart where member_id = 2;