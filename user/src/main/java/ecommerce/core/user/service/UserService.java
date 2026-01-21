package ecommerce.core.user.service;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ecommerce.core.user.common.AbstractCrudService;
import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserResponseDto;
import ecommerce.core.user.entity.User;
import ecommerce.core.user.mapper.UserMapper;
import ecommerce.core.user.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService extends AbstractCrudService<User, Long>{
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	protected JpaRepository<User, Long> getRepository() {
		return userRepository;
	}
	
	
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
	

}
