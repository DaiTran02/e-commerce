package ecommerce.core.user.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserLoginDto;
import ecommerce.core.user.dto.UserResponseDto;
import ecommerce.core.user.entity.User;
import ecommerce.core.user.exception.EmailAlreadyExistsException;
import ecommerce.core.user.exception.InvalidCredentialsException;
import ecommerce.core.user.mapper.UserMapper;
import ecommerce.core.user.repository.UserRepository;
import ecommerce.core.user.security.jwt.JwtService;
import ecommerce.core.user.service.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public UserResponseDto register(UserCreateDto userCreateDto) {
		Optional<User> userExist = userRepository.findByEmail(userCreateDto.getEmail());
		if(userExist.isPresent()) {
			throw new EmailAlreadyExistsException(userCreateDto.getEmail());
		}
		
		User user = userMapper.toEntity(userCreateDto);
		user.setCreateTime(new Date());
		user.setUpdateTime(null);
		user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
		user.setRole("USER");
		User create = userRepository.save(user);
		UserResponseDto userResponseDto = userMapper.toDto(create);
		return userResponseDto;
	}
	
	@Override
	public UserResponseDto login(UserLoginDto userLoginDto) {
		try {
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
			var user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(null);
			var jwt = jwtService.generateToken(new CustomUserDetail(user));
			return UserResponseDto.builder().fullName(user.getFullName()).email(user.getEmail()).token(jwt).build();
		}catch(AuthenticationException e) {
			throw new InvalidCredentialsException();
		}

	}
	
}
