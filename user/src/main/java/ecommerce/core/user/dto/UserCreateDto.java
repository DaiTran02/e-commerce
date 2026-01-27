package ecommerce.core.user.dto;

import lombok.Data;

@Data
public class UserCreateDto {
	private String fullName;
	private String email;
	private String password;
}
