package ecommerce.core.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;
	
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
	
	@PostMapping("/bulk_register")
	public Object bulkRegister(@RequestBody UserCreateDto userCreateDto) {
		List<UserCreateDto> listUserCreateDtos = new ArrayList<UserCreateDto>();
		String email = userCreateDto.getEmail();
		String password = passwordEncoder.encode(userCreateDto.getPassword());
 		for(int i = 0; i < 10000; i++) {
			UserCreateDto userDto = new UserCreateDto();
			userDto.setEmail(email+i);
			userDto.setFullName(userCreateDto.getFullName());
			userDto.setPassword(password);
			
			listUserCreateDtos.add(userDto);
			if(listUserCreateDtos.size() == 500) {
				authenticationService.bulkRegister(new ArrayList<UserCreateDto>(listUserCreateDtos));
				listUserCreateDtos.clear();
			}
		}
 		
 		if(!listUserCreateDtos.isEmpty()) {
 			authenticationService.bulkRegister(new ArrayList<UserCreateDto>(listUserCreateDtos));
 			listUserCreateDtos.clear();
 		}
 		
 		
		return ResponseEntity.ok().body("ok"); 
	}

}
