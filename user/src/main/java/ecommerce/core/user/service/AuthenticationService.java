package ecommerce.core.user.service;

import java.util.List;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserLoginDto;
import ecommerce.core.user.dto.UserResponseDto;

public interface AuthenticationService {
	UserResponseDto register(UserCreateDto userCreateDto);
	UserResponseDto login(UserLoginDto userLoginDto);
	void bulkRegister(List<UserCreateDto> listCreateDtos);
}
