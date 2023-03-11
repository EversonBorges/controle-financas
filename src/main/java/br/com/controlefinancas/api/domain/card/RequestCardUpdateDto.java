package br.com.controlefinancas.api.domain.card;

public record RequestCardUpdateDto(String nameCard, String owner, Integer duoDate, Double limitCard, Integer bestDayBuy) {

}