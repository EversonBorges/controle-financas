package br.com.controlefinancas.api.domain.installments;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlefinancas.api.domain.card.Card;
import br.com.controlefinancas.api.domain.card.CardService;
import br.com.controlefinancas.api.domain.transaction.Transaction;
import jakarta.transaction.Transactional;

@Service
public class InstallmentService {

	@Autowired
	private InstallmentRepository repository;
	
	@Autowired
	private CardService cardService;

	@Transactional
	public void createInstallment(Card card, Transaction transaction) {

		List<Installments> installmentsList = new ArrayList<>();
		
		LocalDate dateExpiration = cardService.getExpirationDate(card);
		LocalDate bestBuyDate = cardService.getBestBuyDate(card.getBestDayBuy(), dateExpiration);
		
		Period period = Period.between(transaction.getReferenceDate(), bestBuyDate);
		
		LocalDate installmentDate = transaction.getReferenceDate().isBefore(bestBuyDate) ? dateExpiration.minusMonths(period.getMonths()) : transaction.getReferenceDate().plusMonths(1);

		Double valueInstallment = transaction.getPrice() / transaction.getInstallmentsTotal();
		for (int i = 0; i < transaction.getInstallmentsTotal(); i++) {
			
			var installments = new Installments(
					transaction,
					i == 0 ? installmentDate : installmentDate.plusMonths(i),
					valueInstallment, 
					i + 1);
			
			installmentsList.add(installments);
		}

		repository.saveAll(installmentsList);
	}
}
