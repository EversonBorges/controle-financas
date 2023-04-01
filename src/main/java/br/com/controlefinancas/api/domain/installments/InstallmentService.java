package br.com.controlefinancas.api.domain.installments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlefinancas.api.domain.transaction.Transaction;
import jakarta.transaction.Transactional;

@Service
public class InstallmentService {

	@Autowired
	private InstallmentRepository repository;

	@Transactional
	public void createInstallment(Transaction transaction) {

		List<Installments> installmentsList = new ArrayList<>();

		Double valueInstallment = transaction.getPrice() / transaction.getInstallmentsTotal();
		for (int i = 0; i < transaction.getInstallmentsTotal(); i++) {
			
			var installments = new Installments(
					transaction,
					i == 0 ? transaction.getReferenceDate() : transaction.getReferenceDate().plusMonths(i),
					valueInstallment, 
					i + 1);
			
			installmentsList.add(installments);
		}

		repository.saveAll(installmentsList);
	}
}
