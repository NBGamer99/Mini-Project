package me.ynabouzi.miniproject.controllers.admin.professors;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.controllers.error.ErrorMessageController;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.DAOImpl.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminListProfessorsController")
@RequestScoped
public class ListProfessorsController implements Serializable {

	ErrorMessageController errorMessageController = new ErrorMessageController();

	private static ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();

	private List<ProfessorEntity> professors;

	@PostConstruct
	public void init() {
		professors = fetchProfessorsFromBackend();
	}

	private List<ProfessorEntity> fetchProfessorsFromBackend() {
		professors = professorService.getAllEntities();
		return professors;
	}

	public void deleteProfessor(Long id) {
		ProfessorEntity professor = professorService.getEntityById(id);
		unsetAttributes(professor);
		if (professorService.deleteEntity(id)) {
			this.init();
		}
	}

	private void unsetAttributes(ProfessorEntity professor) {
		if (professor.getCourseItems() != null)
			professor.getCourseItems().forEach(courseItem -> courseItem.setProfessor(null));
	}


}
