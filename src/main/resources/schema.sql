create table Mtgcard(
    card_id int primary key,
    quality varchar(2) not null,
    card_name varchar(200) not null,
    cost_usd float not null,
    set_name varchar(200) not null,
    card_image_file_name varchar(600),
    number_Have int not null
);