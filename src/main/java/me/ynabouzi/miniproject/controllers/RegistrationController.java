package me.ynabouzi.miniproject.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.UserEntityDAOImpl;
import me.ynabouzi.miniproject.enums.Users;
import me.ynabouzi.miniproject.model.UserEntity;
import me.ynabouzi.miniproject.util.PasswordHasher;

@Data
@Getter
@Setter
@Named(value = "RegistrationController")
@RequestScoped
public class RegistrationController {

	@Inject
	private ErrorMessageController errorMessageController;

	private static UserEntityDAOImpl userService = new UserEntityDAOImpl();
	private String username;
	private String password;
	private String confirmPassword;
	private boolean AgreeToTerms;

	public String register() {
		if (username == null || username.isEmpty()) {
			errorMessageController.setErrorMessage("Username cannot be empty");
			return "register";
		}
		if (password == null || password.isEmpty()) {
			errorMessageController.setErrorMessage("Password cannot be empty");
			return "register";
		}
		if (confirmPassword == null || confirmPassword.isEmpty()) {
			errorMessageController.setErrorMessage("Confirm Password cannot be empty");
			return "register";
		}
		if (!password.equals(confirmPassword)) {
			errorMessageController.setErrorMessage("Password and Confirm Password do not match");
			return "register";
		}
		if (!AgreeToTerms) {
			errorMessageController.setErrorMessage("You must agree to the terms and conditions");
			return null;
		}
		if (userService.getUserByUsername(username) != null) {
			errorMessageController.setErrorMessage("Username already exists");
			return "register";
		}

		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(PasswordHasher.hashPassword(password));
		user.setRole(Users.ADMIN);

		userService.saveUser(user);
		errorMessageController.setErrorMessage("Registration Successful");

		return "login?faces-redirect=true";
	}
}
