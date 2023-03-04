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
		 var transaction = new Transaction(dto.referenceDate(), card, dto.userCard(), dto.purchaseDescription(), dto.price(), dto.installments());
		 repository.save(transaction);
		return new ResponseTransactionDto(transaction);
	}

}