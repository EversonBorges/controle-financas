package br.com.controlefinancas.api.domain.card;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Long>{

	@Query("select * FROM transactions WHERE card_id = 6 AND YEAR(reference_date) = '2023' AND MONTH(reference_date) = '04'")
	Page<Card> findAllByActiveTrueAndTransactionsReferenceDate(Pageable pageable, LocalDate referenceDate);
}
