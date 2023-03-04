CREATE TABLE cards(
	id bigint not null auto_increment,
	name_card varchar(100) not null,
	owner varchar(100) not null,
	
	primary key(id)
);