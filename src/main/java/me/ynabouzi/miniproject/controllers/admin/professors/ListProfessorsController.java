package me.ynabouzi.miniproject.controllers.admin.professors;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminListProfessorsController")
@RequestScoped
public class ListProfessorsController implements Serializable {

	private ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();

	private List<ProfessorEntity> professors;

	@PostConstruct
	public void init() {
		professors = fetchProfessorsFromBackend();
	}

	private List<ProfessorEntity> fetchProfessorsFromBackend() {
		professors = professorService.getAllProfessors();
		return professors;
	}

	public void deleteProfessor(Long id) {
		if (professorService.deleteProfessor(id)) {
			this.init();
		}
	}

}
