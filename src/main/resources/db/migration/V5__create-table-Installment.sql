CREATE TABLE installments(
	id bigint not null auto_increment,
	transaction_id bigint not null,
	installment_date date not null,
	value_installment decimal(10,2) not null,
	installment int not null,
	
	primary key(id),
	constraint fk_installments_transaction_id foreign key(transaction_id) references transactions(id)
);
