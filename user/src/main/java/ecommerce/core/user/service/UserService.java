package ecommerce.core.user.service;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserResponseDto;

public interface UserService {
	UserResponseDto createUser(UserCreateDto userCreateDto);
}
