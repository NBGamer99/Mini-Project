package me.ynabouzi.miniproject.bean.login;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.enums.Users;

import java.io.Serializable;

@Data
@Getter
@Setter
@Named(value = "UserBean")
@SessionScoped
public class UserBean implements Serializable {
	private String username;
	private String password;
	private Users role;
	private boolean loggedIn = false;
}
