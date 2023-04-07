package br.com.controlefinancas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.controlefinancas.api.domain.card.ActiveCardDto;
import br.com.controlefinancas.api.domain.card.Card;
import br.com.controlefinancas.api.domain.card.CardRepository;
import br.com.controlefinancas.api.domain.card.CardService;
import br.com.controlefinancas.api.domain.card.RequestCardDto;
import br.com.controlefinancas.api.domain.card.RequestCardUpdateDto;
import br.com.controlefinancas.api.domain.card.ResponseCardActiveDTO;
import br.com.controlefinancas.api.domain.card.ResponseCardDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cards")
public class CardController {

	@Autowired
	private CardRepository repository;
	
	@Autowired
	private CardService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> registerCard(@Valid @RequestBody RequestCardDto requestDto, UriComponentsBuilder uriBuilder){
		var card = new Card(requestDto);
		repository.save(card);
		
		var uri = uriBuilder.path("/cards/{id}").buildAndExpand(card.getId()).toUri();
		return ResponseEntity.created(uri).body(new ResponseCardDto(card));
	}
	
	@GetMapping("/allCardsActive")
	public ResponseEntity<List<ResponseCardDto>> getAllCardsActive(){
		var cards = repository.findAllByActiveTrue();
		service.setDateExpirationAndBestBuyDate(cards);
		
		return ResponseEntity.ok(cards.stream().map(ResponseCardDto::new).toList());
	}
	
	@GetMapping
	public ResponseEntity<Page<ResponseCardDto>> getAllCards(Pageable pageable){
		var page = repository.findAll(pageable).map(ResponseCardDto::new);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCardById(@PathVariable Long id){
		var card = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new ResponseCardDto(card));
	}
	
	@GetMapping("/listCardActive")
	public ResponseEntity<List<ResponseCardActiveDTO>> getListCardActive(){
		 var cards = repository.findAllByActiveTrue().stream().map(ResponseCardActiveDTO::new).toList();
		return ResponseEntity.ok(cards);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> updateCard(@Valid @RequestBody RequestCardUpdateDto cardDto, @PathVariable Long id){
		var card = repository.getReferenceById(id);
		service.update(card, cardDto);
		
		return ResponseEntity.ok(new ResponseCardDto(card));
	}
	
	@PutMapping("activateAndDeactivate/{id}")
	@Transactional
	public ResponseEntity<?> activateAndDeactivateCard(@PathVariable Long id, @RequestBody @Valid ActiveCardDto activeDto){
		var card = repository.getReferenceById(id);
		service.activateAndDeactivate(card, activeDto);
		
		return ResponseEntity.noContent().build();
	}
}
