package br.com.controlefinancas.api.domain.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	Page<Transaction> findAllTransactionsByCardId(Pageable pageable, Long id);

}
