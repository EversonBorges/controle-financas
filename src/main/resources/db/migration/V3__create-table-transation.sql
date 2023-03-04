CREATE TABLE transactions(
	id bigint not null auto_increment,
	reference_date date not null,
	card_id bigint not null,
	user_card varchar(100) not null,
	purchase_description varchar(100) not null,
	price decimal(10,2) not null,
	installments int not null,
	
	primary key(id),
	constraint fk_transactions_card_id foreign key(card_id) references cards(id)
);