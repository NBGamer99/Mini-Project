package me.ynabouzi.miniproject.bean.admin;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Named(value = "CourseItemBean")
@SessionScoped
public class CourseItemBean implements Serializable {

	private static ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();
	private static CourseEntityDAOImpl courseService = ServiceDAOFactory.getCourseService();

	private List<CourseEntity> availableCourses;
	private Long selectedCourse;

	private CourseItemEntity courseItemEntity;
	private List<ProfessorEntity> availableProfessors;
	private Long selectedProfessor;

	public CourseItemBean() {
		availableProfessors = professorService.getAllEntities();
		availableCourses = courseService.getAllEntities();
	}

	public void updateAvailableEntities() {
		this.setAvailableProfessors(professorService.getAllEntities());
		this.setAvailableCourses(courseService.getAllEntities());
	}

	@PostConstruct
	public void init() {
		this.setCourseItemEntity(new CourseItemEntity());
		setAvailableCourses(courseService.getAllEntities());
		if (selectedCourse != null)
			selectedCourse = null;
		if (selectedProfessor != null)
			selectedProfessor = null;
	}

}
