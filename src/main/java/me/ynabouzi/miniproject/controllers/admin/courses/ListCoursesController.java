package me.ynabouzi.miniproject.controllers.admin.courses;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseEntity;

import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@Data
@Named(value = "AdminListCoursesController")
@RequestScoped
public class ListCoursesController implements Serializable {

	private static CourseEntityDAOImpl courseService = ServiceDAOFactory.getCourseService();
	private List<CourseEntity> courses;

	@PostConstruct
	public void init() {
		courses = fetchCoursesFromBackend();
	}

	private List<CourseEntity> fetchCoursesFromBackend() {
		courses = courseService.getAllCourses();
		return courses;
	}

	public void deleteCourse(Long id) {
		if (courseService.deleteCourse(id))
		{
			this.init();
		}
	}

}
