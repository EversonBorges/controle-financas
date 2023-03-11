package br.com.controlefinancas.api.domain.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	@Query("""
			SELECT t FROM Transaction t
			WHERE t.card.id = :id
			AND MONTH(t.referenceDate) = :month
			AND YEAR(t.referenceDate) = :year
			""")
	Page<Transaction> findAllTransactionsByCardIdAndMonthAndYearReferences(Pageable pageable, Long id, String month, String year);

}