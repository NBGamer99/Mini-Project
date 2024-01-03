package me.ynabouzi.miniproject.controllers.login;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.admin.UserBean;
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
				System.out.println("I did work and enter");
				userBean.setRole(Users.PROFESSOR);
				return "/professor/index?faces-redirect=true";
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
		System.out.println("Current Page: " + currentPage+ " " + "/index.xhtml".equals(currentPage) + " userBean.isLoggedIn(): " + userBean.isLoggedIn() + "User role " + userBean.getRole());

		if (!userBean.isLoggedIn() && !"/index.xhtml".equals(currentPage)) {
			return "/index.xhtml?faces-redirect=true"; // Redirect to login if not logged in
		} else if (userBean.isLoggedIn()) {
			if ("/index.xhtml".equals(currentPage)) {
				// Redirect to respective dashboards based on user role
				if (userBean.getRole().equals(Users.ADMIN)) {
					return "/admin/index.xhtml?faces-redirect=true";
				} else if (userBean.getRole().equals(Users.PROFESSOR)) {
					return "/professor/index.xhtml?faces-redirect=true";
				}
			} else if (currentPage.startsWith("/admin/") && !userBean.getRole().equals(Users.ADMIN)) {
				// Prevent access to admin pages for non-admin users
				return "/error.xhtml?faces-redirect=true"; // Redirect to an error page or handle differently
			} else if (currentPage.startsWith("/professor/") && !userBean.getRole().equals(Users.PROFESSOR)) {
				// Prevent access to professor pages for non-professor users
				return "/error.xhtml?faces-redirect=true"; // Redirect to an error page or handle differently
			}
		}
		return null;
	}

}
