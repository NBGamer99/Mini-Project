package me.ynabouzi.miniproject.controllers.admin.professors;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.admin.ProfessorBean;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.ProfessorEntity;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;

import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminAddProfessorController")
@RequestScoped
public class AddProfessorController {

	private static ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();

	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();

	@Inject
	private ProfessorBean professorBean;

	public String addProfessor() {
		ProfessorEntity professorEntity = professorBean.getProfessorEntity();
		professorEntity.setCourseItems(professorBean.getSelectedCoursesItems());

		List<CourseItemEntity> courseItems = professorBean.getSelectedCoursesItems();


		professorService.saveEntity(professorBean.getProfessorEntity());

		professorEntity = professorService.getProfessorByLastName(professorEntity.getLastName());

		for (CourseItemEntity courseItem : courseItems) {
			courseItem.setProfessor(professorEntity);
			courseItemService.updateEntity(courseItem, courseItem.getId());
		}

		professorBean.init();
		return "/admin/professors/professor-list?faces-redirect=true";
	}
}
