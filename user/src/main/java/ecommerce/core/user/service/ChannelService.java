package ecommerce.core.user.service;

import ecommerce.core.user.dto.ChannelCreateDto;
import ecommerce.core.user.dto.ChannelResponseDto;

public interface ChannelService {
	ChannelResponseDto createChannel(ChannelCreateDto channelCreateDto);
}
