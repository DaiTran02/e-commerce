package ecommerce.core.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.core.user.dto.ChannelCreateDto;
import ecommerce.core.user.dto.ChannelResponseDto;
import ecommerce.core.user.service.ChannelService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/channel")
@RequiredArgsConstructor
public class ChannelController {
	
	private final ChannelService channelService;
	
	@PostMapping(path = "/create",name = "Create channel")
	public Object createChannel(@RequestBody ChannelCreateDto channelCreateDto) {
			ChannelResponseDto channelResponseDto = channelService.createChannel(channelCreateDto);
			return ResponseEntity.ok().body(channelResponseDto);
	}
	

}
