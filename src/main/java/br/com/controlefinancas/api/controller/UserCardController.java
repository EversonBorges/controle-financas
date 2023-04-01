package br.com.controlefinancas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.controlefinancas.api.domain.UserCard.UserCard;
import br.com.controlefinancas.api.domain.UserCard.UserCardRepository;
import br.com.controlefinancas.api.domain.UserCard.UserCardRequestDTO;
import br.com.controlefinancas.api.domain.UserCard.UserCardRequestUpdateDTO;
import br.com.controlefinancas.api.domain.UserCard.UserCardResponseDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-card")
public class UserCardController {
	
	@Autowired
	private UserCardRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> registerUserCard(@Valid @RequestBody UserCardRequestDTO requestDTO, UriComponentsBuilder builder){
		
		var userCard = new UserCard(requestDTO);
		var uri = builder.path("/user-card/{id}").buildAndExpand(userCard.getId()).toUri();
		repository.save(userCard);
		
		return ResponseEntity.created(uri).body(new UserCardResponseDTO(userCard));
	}
	
	@GetMapping
	public ResponseEntity<List<UserCardResponseDTO>> getAllUserCard(){
		
		var usersCard = repository.findAll().stream().map(UserCardResponseDTO::new).toList();
		return ResponseEntity.ok(usersCard);
	}
	
	@GetMapping("/allActive")
	public ResponseEntity<List<UserCardResponseDTO>> getAllUserCardByActive(){
		
		var usersCard = repository.findAllByActiveTrue().stream().map(UserCardResponseDTO::new).toList();
		return ResponseEntity.ok(usersCard);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> updateUserCard(@RequestBody UserCardRequestUpdateDTO updateDto){
		
		var userCard = repository.getReferenceById(updateDto.id());
		userCard.update(updateDto);
		
		return ResponseEntity.ok(new UserCardResponseDTO(userCard));
	}
}
