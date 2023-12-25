package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.*;
import me.ynabouzi.miniproject.enums.Users;

@Data
@ToString
@EqualsAndHashCode
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
