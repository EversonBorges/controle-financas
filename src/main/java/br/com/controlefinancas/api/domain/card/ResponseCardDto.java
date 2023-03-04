package br.com.controlefinancas.api.domain.card;

public record ResponseCardDto(Long id, String nameCard, String owner, Boolean active) {

	public ResponseCardDto(Card card) {
		this(card.getId(), card.getNameCard(), card.getOwner(), card.getActive());
	}
}
