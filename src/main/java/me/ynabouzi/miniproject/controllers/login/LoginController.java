package me.ynabouzi.miniproject.controllers.login;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.controllers.error.ErrorMessageController;
import me.ynabouzi.miniproject.controllers.login.handler.LoginHandlerChain;
import me.ynabouzi.miniproject.dao.DAOImpl.UserEntityDAOImpl;
import me.ynabouzi.miniproject.enums.Users;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.model.UserEntity;
import me.ynabouzi.miniproject.util.PasswordHasher;

import java.io.Serializable;

@Data
@Getter
@Setter
@Named(value = "LoginController")
@RequestScoped
public class LoginController implements Serializable {

	private static UserEntityDAOImpl userService = ServiceDAOFactory.getUserService();
	@Inject
	private ErrorMessageController errorMessageController;
	@Inject
	private UserBean userBean;

	public String login() {
		UserEntity dbUser = userService.getUserByUsername(userBean.getUsername());
		if (dbUser != null && PasswordHasher.verifyPassword(userBean.getPassword(), dbUser.getPassword())) {
			userBean.setLoggedIn(true);
			if (dbUser.getRole().equals(Users.ADMIN)) {
				userBean.setRole(Users.ADMIN);
				return "admin/index.xhtml?faces-redirect=true";
			} else if (dbUser.getRole().equals(Users.PROFESSOR)) {
//				System.out.println("I did work and enter");
				userBean.setRole(Users.PROFESSOR);
				return "/professor/index?faces-redirect=true";
			}
		} else {
			errorMessageController.setErrorMessage("Invalid username or password");
			return "index";
		}
		return "index";
	}

	public String logout() {
		userBean.setUsername(null);
		userBean.setPassword(null);
		userBean.setLoggedIn(false);
		userBean.setRole(null);
		return "/index?faces-redirect=true";
	}

}
