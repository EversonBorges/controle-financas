package br.com.controlefinancas.api.domain.UserCard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="UserCard")
@Table(name = "user_card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserCard {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_user")
	private String nameUser;
	
	@Column(name = "active")
	private Boolean active; 
	
	public UserCard(UserCardRequestDTO requestDTO) {
		this.nameUser = requestDTO.nameUser();
		this.active = true;
	}

	public void update(UserCardRequestUpdateDTO updateDto) {

		if(updateDto.nameUser() != null) {
			this.nameUser = updateDto.nameUser();
		}
		
		this.active = updateDto.active();
	}
}
