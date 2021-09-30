create table Mtgcard(
    card_id int primary key,
    condition varchar(2) not null,
    card_name varchar(200) not null,
    cost_usd float not null,
    set_name varchar(200) not null,
    card_image_file_name varchar(600)
);

create table Card_inventory(
    ci_id identity,
    card_id int not null,
    count int not null default 0
);

alter table Card_inventory
    add constraint CK_CardInventory_Min check (count>=0);

alter table Card_inventory
    add foreign key (card_id) references Mtgcard(card_id);

/*
    card is greatly simplified for this project
    databases inteneded to serve online transaction processing needs should not use 6NF normalization


    a more normalized setup would look more like

    Table 1: Primary Key, Card Name

    Table 2: Primary Key, Set Name

    Table 3: Primary Key, FK Card Name, FK Set Name, Description (For Special Treatments)

    Table 4: FK Card Printing, Card Image

    Table 5: FK Card Printing, Unit Condition

    Table 6: FK CND+PRNT, Foil

    Table 7: FK Unit, Unit Price

    Table 8: FK Unit, Quantity Owned
 */