package br.com.controlefinancas.api.domain.installments;

import java.time.LocalDate;

public record ResponseInstallmentsDto(
			Long idTransaction, 
			LocalDate referenceDate, 
			String nomeCard,
			Long userCardId,
			String userCard, 
			String purchaseDescription, 
			Double price, 
			Integer installmentsTotal,
			LocalDate installmentDate,
			Double intallmentValue,
			Integer installment
		) {

	public ResponseInstallmentsDto(Installments installments) {
		this(
				installments.getTransaction().getId(), 
				installments.getTransaction().getReferenceDate(), 
				installments.getTransaction().getCard().getNameCard(), 
				installments.getTransaction().getUserCard().getId(),
				installments.getTransaction().getUserCard().getNameUser(),
				installments.getTransaction().getPurchaseDescription(), 
				installments.getTransaction().getPrice(), 
				installments.getTransaction().getInstallmentsTotal(),
				installments.getInstallmentDate(),
				installments.getValueInstallments(),
				installments.getInstallment()
			);
	}

}