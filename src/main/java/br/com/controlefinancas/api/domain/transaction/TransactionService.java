package br.com.controlefinancas.api.domain.transaction;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlefinancas.api.domain.UserCard.UserCardRepository;
import br.com.controlefinancas.api.domain.card.CardRepository;
import br.com.controlefinancas.api.domain.installments.InstallmentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class TransactionService {
	@Autowired 
	private TransactionRepository repository;
	
	@Autowired 
	private CardRepository cardRepository;
	
	@Autowired
	private UserCardRepository userCardRepository;
	
	@Autowired
	private InstallmentService installmentService;
	
	@Transactional
	public void createTransaction(@Valid RequestTransactionDto dto){
	
		var card = cardRepository.getReferenceById(dto.idCard());
		var userCard = userCardRepository.getReferenceById(dto.userCardId());
		var transaction = new Transaction(dto.referenceDate(), card, userCard, dto.purchaseDescription(), dto.price(), dto.installmentsTotal());

		repository.save(transaction);
		var transactionInstallments = repository.getReferenceById(transaction.getId());
		installmentService.createInstallment(transactionInstallments);
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
		
		if(dto.userCardId() != null) {
			var userCard = userCardRepository.getReferenceById(dto.userCardId());
			transaction.setUserCard(userCard);
		}
		
		if(dto.purchaseDescription() != null) {
			transaction.setPurchaseDescription(dto.purchaseDescription());
		}
		
		if(dto.price() != null) {
			transaction.setPrice(dto.price());
		}
		
		if(dto.installmentsTotal() != null) {
			transaction.setInstallmentsTotal(dto.installmentsTotal());
		}
		
		return transaction;
	}

	public Map<String, String> getMonthAndYearDateString(String referenceDate) {
		
		Map<String, String> monthAndYear = new HashMap<String, String>();
		monthAndYear.put("MONTH", referenceDate.substring(3, 5));
		monthAndYear.put("YEAR", referenceDate.substring(6, 10));
		
		return monthAndYear;
	}

}