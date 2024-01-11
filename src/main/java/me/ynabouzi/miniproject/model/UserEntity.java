package me.ynabouzi.miniproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.ynabouzi.miniproject.enums.Users;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users", schema = "MINI_PROJET")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private Users role;
}
