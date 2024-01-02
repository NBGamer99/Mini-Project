package me.ynabouzi.miniproject.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Named(value = "ProfessorBean")
@SessionScoped
public class ProfessorBean implements Serializable {

	private static ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();
	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();

	private List<CourseItemEntity> availableCoursesItems;
	private  List<CourseItemEntity> selectedCoursesItems;
	private ProfessorEntity professorEntity;

	public ProfessorBean() {
		this.setProfessorEntity(new ProfessorEntity());
		availableCoursesItems = courseItemService.getAllCourseItems();
	}

	public void changeValues() {
		this.setAvailableCoursesItems(courseItemService.getAllCourseItems());
	}

	@PostConstruct
	public void init() {
		setProfessorEntity(new ProfessorEntity());
		if (selectedCoursesItems != null && !selectedCoursesItems.isEmpty())
			selectedCoursesItems.clear();
	}
}
