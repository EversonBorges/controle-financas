CREATE TABLE user_card(
	id bigint not null auto_increment,
	name_user varchar(100) not null,
	active tinyint not null,
	
	primary key(id)
);