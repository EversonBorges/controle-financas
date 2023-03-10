package br.com.controlefinancas.api.domain.card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.controlefinancas.api.domain.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name= "Card")
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of= "id")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name_card")
	private String nameCard;
	@Column(name = "owner")
	private String owner;
	@Column(name = "active")
	private Boolean active;
	
	
	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
	private List<Transaction> transactions;

	public Card(RequestCardDto cardDto) {
		this.nameCard = cardDto.nameCard();
		this.owner = cardDto.owner();
		this.active = true;
	}

	public void update(RequestCardUpdateDto cardDto) {
		
		if(cardDto.nameCard() != null) {
			this.nameCard = cardDto.nameCard();
		}
		
		if(cardDto.owner() != null) {
			this.owner = cardDto.owner();
		}
	}

	public void activateAndDeactivate(ActiveCardDto activeDto) {
		this.active = activeDto.active();
	}
}
