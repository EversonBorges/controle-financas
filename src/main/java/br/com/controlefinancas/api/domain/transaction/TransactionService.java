package br.com.controlefinancas.api.domain.transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlefinancas.api.domain.UserCard.UserCardRepository;
import br.com.controlefinancas.api.domain.card.CardRepository;
import br.com.controlefinancas.api.domain.installments.InstallmentService;
import br.com.controlefinancas.api.domain.installments.Installments;
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
		installmentService.createInstallment(card, transactionInstallments);
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

	public ResponseTransactionUserCardDto getInfoTransactionUserCard(List<Installments> installments,
			String month, String year) {
		
		 Map<String, Object> infoUserCard = createTransactionInfoUserCard(installments, month, year);
		return new ResponseTransactionUserCardDto(infoUserCard);
	}
	
	private Map<String, Object> createTransactionInfoUserCard(List<Installments> installments, String month, String year) {
		
		Map<String, Object> infoUserCard = new HashMap<String, Object>();
		infoUserCard.put("USER_NAME", installments.get(0).getTransaction().getUserCard().getNameUser());
		infoUserCard.put("MONTH_YEAR", month + "/" + year);
		
		Double totalValuePurchase = (double) 0L;
		Integer totalTransactions = 0;
		
		for (Installments installment : installments) {
			totalValuePurchase += installment.getValueInstallments();
			totalTransactions ++;
		}
		
		infoUserCard.put("TOTAL_VALUE_PURCHASE", totalValuePurchase);
		infoUserCard.put("TOTAL_TRANSACTIONS", totalTransactions);
		
		return infoUserCard;
	}


}