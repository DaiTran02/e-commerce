package ecommerce.core.user.service;

import java.util.List;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserFilterDto;
import ecommerce.core.user.dto.UserResponseDto;
import ecommerce.core.user.entity.Channel;
import ecommerce.core.user.entity.User;

public interface UserService {
	UserResponseDto createUser(UserCreateDto userCreateDto);
	User updateChannelOfUser(Long id, List<Channel> listChannels);
	List<UserResponseDto> getListUsers(UserFilterDto userFilterDto);
}
