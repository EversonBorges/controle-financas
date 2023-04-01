alter table transactions add user_card_id bigint not null;
update transactions set user_card_id = 1;
