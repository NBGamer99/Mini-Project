package me.ynabouzi.miniproject.bean.admin;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.StudentEntity;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Named(value = "StudentBean")
@SessionScoped
public class StudentBean implements Serializable {

	private static CourseEntityDAOImpl courseService = ServiceDAOFactory.getCourseService();

	private List<CourseEntity> availableCourses;

	private List<CourseEntity> selectedCourses;

	private StudentEntity studentEntity;

	public StudentBean() {
		availableCourses = courseService.getAllCourses();
	}

	public void changeValues() {
		this.setAvailableCourses(courseService.getAllCourses());
	}

	@PostConstruct
	public void init() {
		setStudentEntity(new StudentEntity());
		if (selectedCourses != null && !selectedCourses.isEmpty())
			selectedCourses.clear();
	}

}
