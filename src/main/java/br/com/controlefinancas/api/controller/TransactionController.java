package br.com.controlefinancas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.controlefinancas.api.domain.transaction.RequestTransactionDto;
import br.com.controlefinancas.api.domain.transaction.RequestTransactionUpdateDto;
import br.com.controlefinancas.api.domain.transaction.ResponseTransactionDto;
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
	
	@GetMapping
	public ResponseEntity<Page<ResponseTransactionDto>> getAllTransactions(Pageable pageable){
		var page = repository.findAll(pageable).map(ResponseTransactionDto::new);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTransactionById(@PathVariable Long id){
		var transaction = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new ResponseTransactionDto(transaction));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> updateTransaction(@Valid @RequestBody RequestTransactionUpdateDto dto){
		var transaction = service.update(dto);
		
		return ResponseEntity.ok(new ResponseTransactionDto(transaction));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeTransaction(@PathVariable Long id){
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
