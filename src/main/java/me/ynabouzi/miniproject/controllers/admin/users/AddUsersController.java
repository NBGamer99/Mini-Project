package me.ynabouzi.miniproject.controllers.admin.users;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.DAOImpl.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.model.ProfessorEntity;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.DAOImpl.UserEntityDAOImpl;
import me.ynabouzi.miniproject.enums.Users;
import me.ynabouzi.miniproject.model.UserEntity;
import me.ynabouzi.miniproject.util.PasswordHasher;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminAddUsersController")
@RequestScoped
public class AddUsersController implements Serializable {

	private static UserEntityDAOImpl userService = ServiceDAOFactory.getUserService();

	private static ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();

	private ProfessorEntity professorEntity;

	private List<ProfessorEntity> availableProfessors;

	private Users selectedUser;

	private Long selectedProfessorId;

	private List<Users> availableUsers;

	private UserEntity userEntity;

	public AddUsersController() {
		userEntity = new UserEntity();
		availableProfessors = professorService.getAllEntities();
		availableUsers = List.of(Users.values());
	}

	public void changeValues()
	{
		setAvailableProfessors(professorService.getAllEntities());
	}

	public String addUser() {
		userEntity.setRole(selectedUser);

		String  hashedPassword = PasswordHasher.hashPassword(userEntity.getPassword());
		userEntity.setPassword(hashedPassword);
		userService.saveEntity(userEntity);

		if (selectedUser == Users.PROFESSOR) {
			professorEntity = professorService.getEntityById(selectedProfessorId);
			professorEntity.setUser_professor(userService.getUserByUsername(userEntity.getUsername()));
			professorService.updateEntity(professorEntity, selectedProfessorId);
		}

		setUserEntity(new UserEntity());
		return "/admin/manage-user/users-list?faces-redirect=true";
	}
}
