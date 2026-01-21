package ecommerce.core.user.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Entity
@Data
@AllArgsConstructor
public class User {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fullName;
	private String email;
	private String password;
	private Date createTime;
	private Date updateTime;
	private String role;
	
}
