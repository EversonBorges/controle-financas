package br.com.controlefinancas.api.domain.UserCard;

public record UserCardResponseDTO(Long id, String nameUser, Boolean active) {
	
	public UserCardResponseDTO(UserCard user) {
		this(user.getId(), user.getNameUser(), user.getActive());
	}
}
