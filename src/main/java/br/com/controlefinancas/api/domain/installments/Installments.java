package br.com.controlefinancas.api.domain.installments;

import java.time.LocalDate;

import br.com.controlefinancas.api.domain.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Installments")
@Table(name = "installments" )
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Installments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;
	
	@Column(name = "installment_date")
	private LocalDate installmentDate;
	
	@Column(name = "value_installment")
	private Double valueInstallments;
	
	@Column(name = "installment")
	private Integer installment;
	
	public Installments(Transaction transaction, LocalDate installmentDate, Double valueInstallments, Integer installment) {
		this.transaction = transaction;
		this.installmentDate = installmentDate;
		this.valueInstallments = valueInstallments;
		this.installment = installment;
	}
}
