package me.ynabouzi.miniproject.bean.admin;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.dao.ProfessorEntityDAOImpl;
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
	private static CourseEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseService();

	private List<CourseEntity> availableCourses;
	private Long selectedCourse;

	private CourseItemEntity courseItemEntity;
	private List<ProfessorEntity> availableProfessors;
	private Long selectedProfessor;

	public CourseItemBean() {
		availableProfessors = professorService.getAllProfessors();
		availableCourses = courseItemService.getAllCourses();
	}

	public void changeValues() {
		this.setAvailableProfessors(professorService.getAllProfessors());
		this.setAvailableCourses(courseItemService.getAllCourses());
	}

	@PostConstruct
	public void init() {
		this.setCourseItemEntity(new CourseItemEntity());
		setAvailableCourses(courseItemService.getAllCourses());
		if (selectedCourse != null)
			selectedCourse = null;
		if (selectedProfessor != null)
			selectedProfessor = null;
	}

}
