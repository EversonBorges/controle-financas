package br.com.controlefinancas.api.domain.transaction;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.controlefinancas.api.domain.UserCard.UserCard;
import br.com.controlefinancas.api.domain.card.Card;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Transaction")
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transaction {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "reference_date")
	private LocalDate referenceDate;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id")
	private Card card;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_card_id")
	private UserCard userCard;
	
	@Column(name = "purchase_description")
	private String purchaseDescription;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "installments")
	private Integer installmentsTotal;
	
	public Transaction(LocalDate referenceDate, Card card, UserCard userCard, String purchaseDescription,Double price, Integer installments) {
		this.referenceDate = referenceDate;
		this.card = card;
		this.userCard = userCard;
		this.purchaseDescription = purchaseDescription;
		this.price = price;
		this.installmentsTotal = installments;
	}
}

