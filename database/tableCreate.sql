create table color(
    color_id int auto_increment primary key,
    product_id int,
    product_color varchar(50) not null,
    foreign key (product_id) references product(product_id)
);

select * from information_schema.TABLE_CONSTRAINTS where CONSTRAINT_NAME = 'member_id_fk';