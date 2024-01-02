package me.ynabouzi.miniproject.controllers.admin.users;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.UserEntityDAOImpl;
import me.ynabouzi.miniproject.model.UserEntity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminListUsersController")
@RequestScoped
public class ListUsersController implements Serializable {

	private static UserEntityDAOImpl userService = ServiceDAOFactory.getUserService();

	private List<UserEntity> users;

	@PostConstruct
	public void init() {
		users = fetchUsersFromBackend();
	}

	public List<UserEntity> fetchUsersFromBackend() {
		users = userService.getAllUsers();
		return users;
	}

	public void deleteUser(Long id) {
		if (userService.deleteUser(id)) {
			this.init();
		}
	}


}