package me.ynabouzi.miniproject.controllers.security;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.controllers.security.handler.AccessCheckService;

import java.io.Serializable;

@Data
@Getter
@Setter
@Named(value = "ForbiddenAccessController")
@RequestScoped
public class ForbiddenAccessController implements Serializable {

	@Inject
	private UserBean userBean;

	private AccessCheckService accessCheckService;

	@PostConstruct
	public void init() {
		accessCheckService = new AccessCheckService();
	}

	public String redirectToLoginIfNotLoggedIn(String currentPage) {
		String redirection = accessCheckService.checkAccess(userBean, currentPage);
		if (redirection != null) {
			return redirection;
		}
		return null;
	}

}
