package me.ynabouzi.miniproject.controllers.admin.professors;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.ProfessorBean;
import me.ynabouzi.miniproject.dao.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.model.ProfessorEntity;

@Setter
@Getter
@Data
@Named(value = "AdminAddProfessorController")
@RequestScoped
public class AddProfessorController {

	private static ProfessorEntityDAOImpl professorService = new ProfessorEntityDAOImpl();

	@Inject
	private ProfessorBean professorBean;

	public String addProfessor() {
		ProfessorEntity professorEntity = professorBean.getProfessorEntity();
		professorEntity.setCourseItems(professorBean.getSelectedCoursesItems());
		professorService.saveProfessor(professorBean.getProfessorEntity());
		professorBean.init();
		return "/admin/professors/professor-list?faces-redirect=true";
	}
}
