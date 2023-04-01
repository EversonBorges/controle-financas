package br.com.controlefinancas.api.domain.card;

public record ResponseCardActiveDTO(Long id, String nameCard) {

	public ResponseCardActiveDTO(Card card) {
		this(card.getId(), card.getNameCard());
	}

}
