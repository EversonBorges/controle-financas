package br.com.controlefinancas.api.domain.card;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CardService {

public void update(Card card, RequestCardUpdateDto cardDto) {
		
		if(cardDto.nameCard() != null) {
			card.setNameCard(cardDto.nameCard());
		}
		
		if(cardDto.owner() != null) {
			card.setOwner(cardDto.owner());
		}
		
		if(cardDto.duoDate() != null) {
			card.setDuoDate(cardDto.duoDate());
		}
		
		if(cardDto.limitCard() != null) {
			card.setLimitCard(cardDto.limitCard());
		}
		
		if(cardDto.bestDayBuy() != null) {
			card.setBestDayBuy(cardDto.bestDayBuy());
		}
	}

	public void activateAndDeactivate(Card card, ActiveCardDto activeDto) {
		card.setActive(activeDto.active());
	}
	
	
	public void  setDateExpirationAndBestBuyDate(List<Card> cards) {
		
		for (Card card : cards) {
			LocalDate dateExpiration = getExpirationDate(card);
			LocalDate bestBuyDate = getBestBuyDate(card.getBestDayBuy(), dateExpiration);
			card.setDateExpiration(dateExpiration);
			card.setBestBuyDate(bestBuyDate);
		}
	}

	public LocalDate getExpirationDate(Card card) {
		LocalDate dateExpiration =  LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),card.getDuoDate());
		return dateExpiration;
	}
	
	public  LocalDate getBestBuyDate(Integer bestDayBuy, LocalDate dateExpiration) {
		
		return dateExpiration.minusDays(bestDayBuy);
	}
}
