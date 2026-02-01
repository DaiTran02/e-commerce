package ecommerce.core.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ecommerce.core.user.common.AbstractCrudService;
import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserFilterDto;
import ecommerce.core.user.dto.UserResponseDto;
import ecommerce.core.user.entity.Channel;
import ecommerce.core.user.entity.User;
import ecommerce.core.user.mapper.UserMapper;
import ecommerce.core.user.repository.UserRepository;
import ecommerce.core.user.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl extends AbstractCrudService<User, Long> implements UserService{
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	protected JpaRepository<User, Long> getRepository() {
		return userRepository;
	}
	
	
	@Override
	public UserResponseDto createUser(UserCreateDto userCreateDto) {
		User user = userMapper.toEntity(userCreateDto);
		user.setCreateTime(new Date());
		user.setUpdateTime(null);
		user.setRole("ADMIN");
		String password = userCreateDto.getPassword();
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		
		User create = create(user);
		return userMapper.toDto(create);
	}



	@Override
	public User updateChannelOfUser(Long id, List<Channel> listChannels) {
		User user = getById(id);
		user.getChannels().clear();
		user.getChannels().addAll(listChannels);
		return update(user);
	}


	@Override
	public List<UserResponseDto> getListUsers(UserFilterDto userFilterDto) {
		Pageable pageable = PageRequest.of(userFilterDto.getPage(), userFilterDto.getSize());
		return userMapper.entityToListDto(getAll(pageable));
	}


	@Override
	public User getUserById(Long id) {
		return getById(id);
	}

}
