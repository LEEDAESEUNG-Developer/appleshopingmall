use appleShopingMall;

alter table product add product_Content_img01 varchar(50);
alter table product add product_Color varchar(50) after product_category;

alter table product drop product_Color;

alter table product add product_operating_system varchar(50) after product_ram;