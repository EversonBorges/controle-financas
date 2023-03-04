package br.com.controlefinancas.api.domain.transaction;

import java.time.LocalDate;

import br.com.controlefinancas.api.domain.card.Card;

public record ResponseTransactionDto(Long id, LocalDate referenceDate, Card cardId, String userCard, String purchaseDescription, Double price, Integer installments) {

	public ResponseTransactionDto(Transaction transaction) {
		this(
				transaction.getId(), 
				transaction.getReferenceDate(), 
				transaction.getCard(), 
				transaction.getUserCard(), 
				transaction.getPurchaseDescription(), 
				transaction.getPrice(), 
				transaction.getInstallments()
			);
	}
}