package br.com.controlefinancas.api.domain.transaction;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record RequestTransactionUpdateDto(
		@NotNull
		Long id,
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate referenceDate, 
		Long idCard,
		Long userCardId, 
		String purchaseDescription, 
		Double price, 
		Integer installmentsTotal
	) {

}
