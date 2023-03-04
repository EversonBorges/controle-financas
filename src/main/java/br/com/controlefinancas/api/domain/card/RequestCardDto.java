package br.com.controlefinancas.api.domain.card;

import jakarta.validation.constraints.NotBlank;

public record RequestCardDto(
		@NotBlank(message = "{nameCard.required}")
		String nameCard, 
		@NotBlank(message = "{owner.required}")
		String owner
	) {

}