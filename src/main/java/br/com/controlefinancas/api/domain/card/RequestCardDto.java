package br.com.controlefinancas.api.domain.card;

import jakarta.validation.constraints.NotBlank;

public record RequestCardDto(
		@NotBlank
		String nameCard, 
		@NotBlank
		String owner
	) {

}