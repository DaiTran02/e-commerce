package ecommerce.core.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.core.user.dto.ResponseApi;
import ecommerce.core.user.dto.UserFilterDto;
import ecommerce.core.user.dto.UserResponseDto;
import ecommerce.core.user.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "Manage users")
@OpenAPIDefinition(info = @Info(title = "API", version="v1"))
public class UserController {
	private final UserService userService;
	
	@GetMapping(path = "/lists",name = "List users")
	public Object getListUsers(@RequestParam int page, @RequestParam int size) {
		
		UserFilterDto userFilterDto = new UserFilterDto();
		userFilterDto.setPage(page);
		userFilterDto.setSize(size);
		
		ResponseApi<List<UserResponseDto>> responseApi = new ResponseApi<>();
		responseApi.setStatus(HttpStatus.OK);
		responseApi.setMessage("Success");
		responseApi.setResult(userService.getListUsers(userFilterDto));
		
		return responseApi.build();
	}
}
