package ecomerce.core.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserLoginDto;
import ecommerce.core.user.dto.UserResponseDto;
import ecommerce.core.user.entity.User;
import ecommerce.core.user.exception.InvalidCredentialsException;
import ecommerce.core.user.mapper.UserMapper;
import ecommerce.core.user.repository.UserRepository;
import ecommerce.core.user.security.AuthenticationServiceImpl;
import ecommerce.core.user.security.jwt.JwtService;

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

    @Test
    void register_Success_ShouldReturnUserResponseDto() {
        // Given: Tạo dữ liệu bằng Builder
        UserCreateDto createDto = UserCreateDto.builder()
                .email("test@example.com")
                .password("rawPassword")
                .fullName("Nguyen Van A")
                .build();

        User userEntity = User.builder()
                .email("test@example.com")
                .password("encodedPassword")
                .role("USER")
                .build();

        UserResponseDto expectedResponse = UserResponseDto.builder()
                .email("test@example.com")
                .fullName("Nguyen Van A")
                .build();

        // Stubbing (Giả lập hành vi)
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userMapper.toEntity(any(UserCreateDto.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(userEntity);
        when(userMapper.toDto(any(User.class))).thenReturn(expectedResponse);

        // When
        UserResponseDto result = authenticationService.register(createDto);

        // Then
        assertNotNull(result);
        assertEquals(expectedResponse.getEmail(), result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    // --- TEST CHO LOGIN ---

    @Test
    void login_Success_ShouldReturnToken() {
        // Given: Tạo dữ liệu bằng Builder
        UserLoginDto loginDto = UserLoginDto.builder()
                .email("test@example.com")
                .password("correctPassword")
                .build();

        User userEntity = User.builder()
                .email("test@example.com")
                .fullName("Nguyen Van A")
                .build();

        String mockToken = "eyJhbGciOiJIUzI1NiJ9.mockToken";

        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(userEntity));
        when(jwtService.generateToken(any())).thenReturn(mockToken);

        // When
        UserResponseDto result = authenticationService.login(loginDto);

        // Then
        assertNotNull(result);
        assertEquals(mockToken, result.getToken());
        assertEquals("Nguyen Van A", result.getFullName());
        
        // Kiểm tra xem manager có được gọi để verify credentials không
        verify(authenticationManager).authenticate(
                any(UsernamePasswordAuthenticationToken.class)
        );
    }

    @Test
    void login_Failure_InvalidCredentials_ShouldThrowException() {
        // Given
        UserLoginDto loginDto = UserLoginDto.builder()
                .email("wrong@example.com")
                .password("wrongPassword")
                .build();

        // Giả lập AuthenticationManager ném lỗi xác thực
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        // When & Then
        assertThrows(InvalidCredentialsException.class, () -> {
            authenticationService.login(loginDto);
        });
    }
	
}
