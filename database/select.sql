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

select * from color;

select * from product;

select product_name, product_color, product_stock from product;

select * from member;

select * from order_number order by order_date desc;
select * from order_tbl;
select * from order_number;
select * from product_cart;

select * from member;

select count(product_id) from product;

select * from order_tbl where order_tbl_id = 13 and member_id = 2;

select * from product order by product_data desc limit 1;

show tables;

select * from product_catagory;

select *
from product_catagory
where catagory_subject = 'MAC';

select * from product where product_category = (select catagory_id from product_catagory where catagory_subject = 'MAC');

select * from product;

select * from order_number;

show tables;

select * from product;

delete
from product
where product_name = '제품 삭제';

show table status where name = 'product';
alter table product auto_increment = 12;

select * from product_catagory;

select * from order_tbl;
select  * from order_create_number;

select * from product;

select *
from member;

select *
from (select @RWONUM := @RWONUM + 1 as rnum,
             product_id,
             product_name,
             product_price,
             product_data,
             product_category,
             product_color,
             product_ap,
             product_ram,
             product_operating_system,
             product_storage,
             product_img01
      from product;

select * from (@rownum := @rownum + 1 as rnum, product_id from product)

select *
from (select @RWONUM := @RWONUM + 1 as rnum,
             product_id,
             product_name,
             product_price,
             product_data,
             product_category,
             product_color,
             product_ap,
             product_ram,
             product_operating_system,
             product_storage,
             product_img01
      from product,
           (SELECT @RWONUM := 0) A
      ORDER BY product_id desc) result
where rnum > (1 - 1) * 9
    limit 9;


select *
    from
        (select @RWONUM := @RWONUM +1 as rnum, product_id, product_data from product, (select @rwonum := 0) A) result order by product_data desc;

    select * from product;
select * from product_catagory;

select *
from (select @RWONUM := @RWONUM + 1 as rnum,
             product_id,
             product_name,
             product_price,
             product_data,
             product_category,
             product_color,
             product_ap,
             product_ram,
             product_operating_system,
             product_storage,
             product_img01
      from product,
           (SELECT @RWONUM := 0) A
      ORDER BY product_id desc) result;



select * from product_cart;
select * from product;

select cart_id, member_id, c.product_price, product_count, product_stock, product_img01
    from product_cart c inner join product p on c.product_id = p.product_id where member_id = 2;