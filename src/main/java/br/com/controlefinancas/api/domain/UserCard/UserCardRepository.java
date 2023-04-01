package br.com.controlefinancas.api.domain.UserCard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCardRepository extends JpaRepository<UserCard, Long>{

	List<UserCard> findAllByActiveTrue();

}
