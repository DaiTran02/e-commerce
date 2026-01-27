package ecommerce.core.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
	private String fullName;
	private String email;
	private String token;
}
