insert into mtgcard(card_id, condition, card_name, cost_usd, set_name, card_image_file_name)
values(1, 'NM', 'Omnath, Locus of Rage', 2.75, 'Battle for Zendikar', 'bfz-217-omnath-locus-of-rage.jpg');

insert into mtgcard(card_id, condition, card_name, cost_usd, set_name, card_image_file_name)
values(2, 'NM', 'Sol Ring', 1.86, 'Commander 2021', 'c21-263-sol-ring.jpg');

insert into mtgcard(card_id, condition, card_name, cost_usd, set_name, card_image_file_name)
values(3, 'NM', 'Eternal Witness', 3.58, 'Commander Legends', 'cmr-425-eternal-witness.jpg');

insert into card_inventory(ci_id, card_id, count)
values(1,1,1);

insert into card_inventory(ci_id, card_id, count)
values(2,2,5);

insert into card_inventory(ci_id, card_id, count)
values(3,3,12);

insert into State_Tax(st_id, state_initials, multiplier)
values ( 1,'WI',0.05);

insert into State_Tax(st_id, state_initials, multiplier)
values ( 2,'IL',0.0625);
