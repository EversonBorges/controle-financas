package br.com.controlefinancas.api.domain.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlefinancas.api.domain.card.CardRepository;
import jakarta.validation.Valid;

@Service
public class TransactionService {
	@Autowired 
	private TransactionRepository repository;
	
	@Autowired 
	private CardRepository cardRepository;
	
	public void createTransaction(@Valid RequestTransactionDto dto){
	
		var card = cardRepository.getReferenceById(dto.idCard());
		List<Transaction> transactions = new ArrayList<>();

		var installments = dto.installments();
		for (int i = 0; i < dto.installments(); i++) {
			var transaction = new Transaction(
					i == 0 ? dto.referenceDate(): dto.referenceDate().plusMonths(i), 
					card, 
					dto.userCard(), 
					dto.purchaseDescription(), 
					dto.price(), 
					installments);
			transactions.add(transaction);
			installments--;
		}
		repository.saveAll(transactions);
	}
	
	public Transaction update(RequestTransactionUpdateDto dto) {
		
		var transaction = repository.getReferenceById(dto.id());

		if(dto.referenceDate() != null) {
			transaction.setReferenceDate(dto.referenceDate());
		}
		
		if(dto.idCard() != null) {
			var card = cardRepository.getReferenceById(dto.idCard());
			transaction.setCard(card);
		}
		
		if(dto.userCard() != null) {
			transaction.setUserCard(dto.userCard());
		}
		
		if(dto.purchaseDescription() != null) {
			transaction.setPurchaseDescription(dto.purchaseDescription());
		}
		
		if(dto.price() != null) {
			transaction.setPrice(dto.price());
		}
		
		if(dto.installments() != null) {
			transaction.setInstallments(dto.installments());
		}
		
		return transaction;
	}

	public Map<String, String> getMonthAndYearDateString(String referenceDate) {
		
		Map<String, String> monthAndYear = new HashMap<String, String>();
		monthAndYear.put("MONTH", referenceDate.substring(4, 6));
		monthAndYear.put("YEAR", referenceDate.substring(7, 11));
		
		return monthAndYear;
	}

}