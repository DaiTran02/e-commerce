package ecommerce.core.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ecommerce.core.user.common.AbstractCrudService;
import ecommerce.core.user.dto.ChannelCreateDto;
import ecommerce.core.user.dto.ChannelResponseDto;
import ecommerce.core.user.entity.Channel;
import ecommerce.core.user.entity.User;
import ecommerce.core.user.exception.CantCreateDataException;
import ecommerce.core.user.mapper.ChannelMapper;
import ecommerce.core.user.repository.ChannelRepository;
import ecommerce.core.user.service.ChannelService;
import ecommerce.core.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl extends AbstractCrudService<Channel, Long> implements ChannelService{
	private final ChannelRepository channelRepository;
	private final ChannelMapper channelMapper;
	private final UserService userService;

	@Override
	protected JpaRepository<Channel, Long> getRepository() {
		return channelRepository;
	}

	@Override
	@Transactional
	public ChannelResponseDto createChannel(ChannelCreateDto channelCreateDto) {
		try {
			Channel channel = channelMapper.createToEntity(channelCreateDto);
			
			channel.setCreateTime(new Date());
			
			User user = userService.getUserById(channelCreateDto.getUserId());
			channel.setUser(user);
			
			Channel channelCreate = create(channel);
			
			List<Channel> listChannels = new ArrayList<Channel>();
			listChannels.add(channelCreate);
			
			userService.updateChannelOfUser(channelCreateDto.getUserId(), listChannels);
			
			ChannelResponseDto channelResponseDto = channelMapper.entityToResponse(channelCreate);
			return channelResponseDto;
		}catch(CantCreateDataException e) {
			throw new CantCreateDataException();
		}
	}

}
