package ecommerce.core.user.service;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserLoginDto;
import ecommerce.core.user.dto.UserResponseDto;

public interface AuthenticationService {
	UserResponseDto register(UserCreateDto userCreateDto);
	UserResponseDto login(UserLoginDto userLoginDto);
}
