package br.com.controlefinancas.api.domain.UserCard;

import jakarta.validation.constraints.NotBlank;

public record UserCardRequestDTO(@NotBlank String nameUser) {

}
