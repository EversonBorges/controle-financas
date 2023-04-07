package br.com.controlefinancas.api.domain.card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>{

	List<Card> findAllByActiveTrue();
}
