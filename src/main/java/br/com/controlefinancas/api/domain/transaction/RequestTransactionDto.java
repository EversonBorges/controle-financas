package br.com.controlefinancas.api.domain.transaction;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestTransactionDto(
		@NotNull
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate referenceDate, 
		@NotNull
		Long idCard,
		@NotBlank
		String userCard, 
		@NotBlank
		String purchaseDescription, 
		@NotNull
		Double price, 
		@NotNull
		Integer installments
	) {

}
