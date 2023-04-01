package br.com.controlefinancas.api.domain.installments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstallmentRepository extends JpaRepository<Installments, Long>{
	
	@Query("""
			SELECT i FROM Installments i
			WHERE i.transaction.card.id = :id
			AND MONTH(i.installmentDate) = :month
			AND YEAR(i.installmentDate) = :year
			""")
	Page<Installments> findInstallmentsByTransactionAndMonthAndYearReferencesByIdCard(Pageable pageable, Long id, String month, String year);

}