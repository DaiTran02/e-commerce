package ecommerce.core.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseDto {
	private String fullName;
	private String email;
	private String token;
}
