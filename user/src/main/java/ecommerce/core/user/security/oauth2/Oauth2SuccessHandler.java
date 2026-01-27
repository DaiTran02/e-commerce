package ecommerce.core.user.security.oauth2;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.entity.User;
import ecommerce.core.user.mapper.UserMapper;
import ecommerce.core.user.repository.UserRepository;
import ecommerce.core.user.security.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException {
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		String email = oAuth2User.getAttribute("email");
		String fullName = oAuth2User.getAttribute("name");
		String token = jwtService.generateTokenOauth2(email);

		oAuth2User.getAttributes().forEach((key,value)->{
			System.out.println("Key: "+key + " value: "+value);
		});
		
		User existUSer = userRepository.findByEmail(email).orElse(null);
		if(existUSer == null) {
			UserCreateDto userCreateDto = new UserCreateDto();
			userCreateDto.setEmail(email);
			userCreateDto.setFullName(fullName);
			userCreateDto.setPassword(null);
			User user = userMapper.toEntity(userCreateDto);
			user.setCreateTime(new Date());
			user.setUpdateTime(null);
			user.setRole("USER");
			user.setProvider("GOOGLE");
			
			userRepository.save(user);
		}
		
		Cookie jwtCookie = new Cookie("JWT_TOKEN", token);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setSecure(false);
		jwtCookie.setPath("/");
		jwtCookie.setMaxAge(24 * 60 * 60);
		
		response.addCookie(jwtCookie);
		
		
		String targetURL = "http://localhost:8080/public/swagger-ui/index.html";
		getRedirectStrategy().sendRedirect(request, response, targetURL);
	}
}
