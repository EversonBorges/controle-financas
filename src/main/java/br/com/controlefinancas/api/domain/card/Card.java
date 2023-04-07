package br.com.controlefinancas.api.domain.card;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name= "Card")
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	
	@Column(name= "duo_date")
	private Integer duoDate;
	
	@Column(name = "limit_card")
	private Double limitCard;
	
	@Column(name = "best_day_buy")
	private Integer bestDayBuy;
	
	@Transient
	private LocalDate dateExpiration;
	
	@Transient
	private LocalDate bestBuyDate;
	
	public Card(RequestCardDto cardDto) {
		this.nameCard = cardDto.nameCard();
		this.owner = cardDto.owner();
		this.duoDate = cardDto.duoDate();
		this.limitCard = cardDto.limitCard();
		this.bestDayBuy = cardDto.bestDayBuy();
		this.active = true;
	}
	
}
