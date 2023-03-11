package br.com.controlefinancas.api.domain.card;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCardDto(
		@NotBlank
		String nameCard, 
		@NotBlank
		String owner,
		@NotNull
		Integer duoDate,
		@NotNull
		Double limitCard,
		@NotNull
		Integer bestDayBuy
	) {
}