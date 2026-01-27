package ecommerce.core.user.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users", indexes = {
		@Index(name = "inx_user_email", columnList = "email", unique=true)
})
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;

	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	private Date createTime;
	private Date updateTime;
	private String role;
	private String provider;

}
