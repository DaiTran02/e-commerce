package ecommerce.core.user.mapper;

import org.mapstruct.Mapper;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserResponseDto;
import ecommerce.core.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toEntity(UserCreateDto userCreateDto);
	UserResponseDto toDto(User entity);
}
