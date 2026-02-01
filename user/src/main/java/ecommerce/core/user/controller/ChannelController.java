package ecommerce.core.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.core.user.dto.ChannelCreateDto;
import ecommerce.core.user.dto.ChannelResponseDto;
import ecommerce.core.user.dto.ResponseApi;
import ecommerce.core.user.service.ChannelService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/channel")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Manage Channel")
@OpenAPIDefinition(info = @Info(title = "API", version="v1"))
public class ChannelController {

	private final ChannelService channelService;

	@PostMapping(path = "/create",name = "Create channel")
	public Object createChannel(@RequestBody ChannelCreateDto channelCreateDto) {

		ChannelResponseDto channelResponseDto = channelService.createChannel(channelCreateDto);
		ResponseApi<ChannelResponseDto> responseApi = new ResponseApi<ChannelResponseDto>();
		responseApi.setStatus(HttpStatus.OK);
		responseApi.setMessage("Success");
		responseApi.setResult(channelResponseDto);


		return responseApi.build();
	}


}
