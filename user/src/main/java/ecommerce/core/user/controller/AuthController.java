package ecommerce.core.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.core.user.dto.UserCreateDto;
import ecommerce.core.user.dto.UserLoginDto;
import ecommerce.core.user.dto.UserResponseDto;
import ecommerce.core.user.service.AuthenticationService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public Object register(@RequestBody UserCreateDto userCreateDto) {
		UserResponseDto userResponseDto = authenticationService.register(userCreateDto);
		
		return ResponseEntity.ok().body(userResponseDto); 
	}
	
	
	@PostMapping("/login")
	public Object login(@RequestBody UserLoginDto userLoginDto) {
		UserResponseDto userResponseDto = authenticationService.login(userLoginDto);
		
		return ResponseEntity.ok().body(userResponseDto);
	}

}
