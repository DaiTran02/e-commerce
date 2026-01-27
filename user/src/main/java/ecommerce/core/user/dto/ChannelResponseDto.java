package ecommerce.core.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ChannelResponseDto {
	private String nameChannel;
	private String avatar;
	private Date createTime;
}
