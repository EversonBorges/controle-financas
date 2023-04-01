package br.com.controlefinancas.api.domain.transaction;

import java.time.LocalDate;

import br.com.controlefinancas.api.domain.UserCard.UserCard;

public record ResponseTransactionDto(Long id, LocalDate referenceDate, String nomeCard, String userCard, String purchaseDescription, Double price, Integer installments) {

	public ResponseTransactionDto(Transaction transaction) {
		this(
				transaction.getId(), 
				transaction.getReferenceDate(), 
				transaction.getCard().getNameCard(), 
				transaction.getUserCard().getNameUser(), 
				transaction.getPurchaseDescription(), 
				transaction.getPrice(), 
				transaction.getInstallmentsTotal()
			);
	}

}