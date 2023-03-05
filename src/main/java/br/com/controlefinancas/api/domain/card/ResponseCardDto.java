package br.com.controlefinancas.api.domain.card;

import java.util.List;

import br.com.controlefinancas.api.domain.transaction.Transaction;

public record ResponseCardDto(Long id, String nameCard, String owner, Boolean active, List<Transaction> transactions) {

	public ResponseCardDto(Card card) {
		this(card.getId(), card.getNameCard(), card.getOwner(), card.getActive(), card.getTransactions());
	}
}
