package br.com.controlefinancas.api.domain.card;

import java.time.LocalDate;

public record ResponseCardDto(
		Long id, 
		String nameCard, 
		String owner, 
		Boolean active, 
		Integer duoDate, 
		Double limitCard, 
		Integer bestDayBuy,
		LocalDate dateExpiration,
		LocalDate bestBuyDate
		) {

	public ResponseCardDto(Card card) {
		this(
				card.getId(), 
				card.getNameCard(), 
				card.getOwner(), 
				card.getActive(), 
				card.getDuoDate(), 
				card.getLimitCard(), 
				card.getBestDayBuy(),
				card.getDateExpiration(),
				card.getBestBuyDate()
			);
	}
}
