update product_cart set product_price = (select product.product_price from product where product_id = 2) where cart_id = 3;

update product_cart set product_count = 1 where cart_id = 1;


select * from order_number;
select * from order_tbl;

update order_tbl set check_stock = true where order_id = 19;