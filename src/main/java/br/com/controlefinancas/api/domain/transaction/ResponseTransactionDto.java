package br.com.controlefinancas.api.domain.transaction;

import java.time.LocalDate;

public record ResponseTransactionDto(Long id, LocalDate referenceDate, String nomeCard, String userCard, String purchaseDescription, Double price, Integer installments) {

	public ResponseTransactionDto(Transaction transaction) {
		this(
				transaction.getId(), 
				transaction.getReferenceDate(), 
				transaction.getCard().getNameCard(), 
				transaction.getUserCard(), 
				transaction.getPurchaseDescription(), 
				transaction.getPrice(), 
				transaction.getInstallments()
			);
	}

}