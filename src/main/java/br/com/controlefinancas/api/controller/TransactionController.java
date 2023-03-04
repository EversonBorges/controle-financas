package br.com.controlefinancas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.controlefinancas.api.domain.transaction.RequestTransactionDto;
import br.com.controlefinancas.api.domain.transaction.TransactionRepository;
import br.com.controlefinancas.api.domain.transaction.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("transactions")
public class TransactionController {

	@Autowired 
	private TransactionRepository repository;
	
	@Autowired
	private TransactionService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> registerTransaction(@Valid @RequestBody RequestTransactionDto transactionDto, UriComponentsBuilder uriBuilder){
		var transaction =  service.createTransaction(transactionDto);
		var uri = uriBuilder.path("/transaction/{id}").buildAndExpand(transaction.id()).toUri();
		
		return ResponseEntity.created(uri).body(transaction);
	}
}
