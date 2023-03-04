package br.com.controlefinancas.api.domain.card;

import jakarta.validation.constraints.NotNull;

public record ActiveCardDto(@NotNull Boolean active) {

}
