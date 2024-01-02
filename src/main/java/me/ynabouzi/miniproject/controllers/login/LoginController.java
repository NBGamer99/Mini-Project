package me.ynabouzi.miniproject.controllers.login;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.UserBean;
import me.ynabouzi.miniproject.controllers.error.ErrorMessageController;
import me.ynabouzi.miniproject.dao.UserEntityDAOImpl;
import me.ynabouzi.miniproject.enums.Users;
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
			if (dbUser.getRole().equals(Users.ADMIN))
			{
				userBean.setRole(Users.ADMIN);
				return "admin/index.xhtml?faces-redirect=true";
			}
			else if (dbUser.getRole().equals(Users.PROFESSOR))
			{
				userBean.setRole(Users.PROFESSOR);
				return "welcome?faces-redirect=true";
			}
		} else {
			errorMessageController.setErrorMessage("Invalid username or password");
			return "index";
		}
		return "index";
	}

	public String logout()
	{
		userBean.setLoggedIn(false);
		userBean.setRole(null);
		return "/index?faces-redirect=true";
	}

	public String redirectToLoginIfNotLoggedIn(String currentPage) {
		System.out.println("Current Page: " + currentPage+ " " + "/index.xhtml".equals(currentPage) + " userBean.isLoggedIn(): " + userBean.isLoggedIn());
		if (!userBean.isLoggedIn() && !"/index.xhtml".equals(currentPage)) {
			return "/index.xhtml?faces-redirect=true";
		} else if (userBean.isLoggedIn()) {
			if ("/index.xhtml".equals(currentPage)) {
				if (userBean.getRole().equals(Users.ADMIN)) {
					return "admin/index.xhtml?faces-redirect=true";
				} else if (userBean.getRole().equals(Users.PROFESSOR)) {
					return "welcome?faces-redirect=true";
				}
			}
		}
		return null;
	}
}
