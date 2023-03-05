package br.com.controlefinancas.api.domain.transaction;

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
	
	public ResponseTransactionDto createTransaction(@Valid RequestTransactionDto dto) {
		var card = cardRepository.findById(dto.idCard()).get();
		//TODO tratar caso card seja nulo
		 var transaction = new Transaction(dto.referenceDate(), card, dto.userCard(), dto.purchaseDescription(), dto.price(), dto.installments());
		 repository.save(transaction);
		return new ResponseTransactionDto(transaction);
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

}