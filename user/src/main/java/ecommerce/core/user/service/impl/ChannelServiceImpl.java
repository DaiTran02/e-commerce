package ecommerce.core.user.service.impl;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ecommerce.core.user.common.AbstractCrudService;
import ecommerce.core.user.dto.ChannelCreateDto;
import ecommerce.core.user.dto.ChannelResponseDto;
import ecommerce.core.user.entity.Channel;
import ecommerce.core.user.mapper.ChannelMapper;
import ecommerce.core.user.repository.ChannelRepository;
import ecommerce.core.user.service.ChannelService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl extends AbstractCrudService<Channel, Long> implements ChannelService{
	private final ChannelRepository channelRepository;
	private final ChannelMapper channelMapper;

	@Override
	protected JpaRepository<Channel, Long> getRepository() {
		return channelRepository;
	}

	@Override
	public ChannelResponseDto createChannel(ChannelCreateDto channelCreateDto) {
		Channel channel = channelMapper.createToEntity(channelCreateDto);
		channel.setCreateTime(new Date());
		ChannelResponseDto channelResponseDto = channelMapper.entityToResponse(create(channel));
		return channelResponseDto;
	}

}
