package ecommerce.core.user.mapper;

import org.mapstruct.Mapper;

import ecommerce.core.user.dto.ChannelCreateDto;
import ecommerce.core.user.dto.ChannelResponseDto;
import ecommerce.core.user.entity.Channel;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
	Channel createToEntity(ChannelCreateDto channelCreateDto);
	ChannelResponseDto entityToResponse(Channel channel);
}
