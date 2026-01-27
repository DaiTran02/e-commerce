package ecomerce.core.user.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import ecommerce.core.user.mapper.UserMapper;
import ecommerce.core.user.repository.UserRepository;
import ecommerce.core.user.security.jwt.JwtService;
import ecommerce.core.user.service.impl.AuthenticationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
	@Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    // --- TEST CHO REGISTER ---

	
}
