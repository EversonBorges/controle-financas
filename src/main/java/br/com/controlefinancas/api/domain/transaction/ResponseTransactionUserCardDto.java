package br.com.controlefinancas.api.domain.transaction;

import java.util.Map;

public record ResponseTransactionUserCardDto(Object nameUserCard, Object totalTransaction, Object totalPurchase, Object monthAndYearReference) {

	public ResponseTransactionUserCardDto(Map<String, Object> infoUserCard) {
		this(
				infoUserCard.get("USER_NAME"),
				infoUserCard.get("TOTAL_TRANSACTIONS"),
				infoUserCard.get("TOTAL_VALUE_PURCHASE"),
				infoUserCard.get("MONTH_YEAR")
			);
	}
}
