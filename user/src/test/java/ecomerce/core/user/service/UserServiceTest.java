package ecomerce.core.user.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ecommerce.core.user.mapper.UserMapper;
import ecommerce.core.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserMapper userMapper;
	
	@Mock
	private UserRepository userRepository;

}
