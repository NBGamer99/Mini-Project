package me.ynabouzi.miniproject.controllers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.UserBean;
import me.ynabouzi.miniproject.dao.UserEntityDAOImpl;
import me.ynabouzi.miniproject.model.UserEntity;
import me.ynabouzi.miniproject.util.PasswordHasher;

import java.io.Serializable;


@Data
@Getter
@Setter
@Named(value = "LoginController")
@SessionScoped
public class LoginController implements Serializable {

	@Inject
	private ErrorMessageController errorMessageController;

	@Inject
	private UserBean userBean;

	private static UserEntityDAOImpl userService = new UserEntityDAOImpl();

	public String login() {
		UserEntity dbUser = userService.getUserByUsername(userBean.getUsername());
		if (dbUser != null && PasswordHasher.verifyPassword(userBean.getPassword(), dbUser.getPassword())) {
			userBean.setLoggedIn(true);
			return "welcome?faces-redirect=true";
		} else {
			errorMessageController.setErrorMessage("Invalid username or password");
			return "login";
		}
	}

	public String logout()
	{
		userBean.setLoggedIn(false);
		return "login?faces-redirect=true";
	}

	public String redirectToLoginIfNotLoggedIn(String currentPage) {
		if (userBean.isLoggedIn())
			if("/login.xhtml".equals(currentPage))
				return "welcome?faces-redirect=true";
			else
				return null;
		return "login";
	}
}
