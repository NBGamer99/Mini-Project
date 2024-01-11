package me.ynabouzi.miniproject.controllers.professor;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.professor.ProfessorUserBean;
import me.ynabouzi.miniproject.model.CourseItemEntity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "ProfessorCourseItemsListController")
@RequestScoped
public class CourseItemsListController implements Serializable {
	private List<CourseItemEntity> allProfessorCourses;

	@Inject
	private ProfessorUserBean professorUserBean;

	public void initialize() {
		professorUserBean.init();
		setAllProfessorCourses(professorUserBean.getProfessorCourseItemsList());
	}
}
