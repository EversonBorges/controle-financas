package br.com.controlefinancas.api.domain.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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

	public Card(RequestCardDto cardDto) {
		this.nameCard = cardDto.nameCard();
		this.owner = cardDto.owner();
		this.active = true;
	}

	public void update(@Valid RequestCardDto cardDto) {
		this.nameCard = cardDto.nameCard();
		this.owner = cardDto.owner();
	}

	public void activateAndDeactivate(ActiveCardDto activeDto) {
		this.active = activeDto.active();
	}
}
