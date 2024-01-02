package me.ynabouzi.miniproject.controllers.admin.courses;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.CourseBean;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;

import java.io.Serializable;

@Setter
@Getter
@Data
@Named(value = "AdminAddCoursesController")
@RequestScoped
public class AddCourseController implements Serializable {

	private static CourseEntityDAOImpl courseService = ServiceDAOFactory.getCourseService();

	@Inject
	private CourseBean courseBean;

	public void courseSetter()
	{
		courseBean.getCourseEntity().setMajors(courseBean.getSelectedMajors().isEmpty() ? courseBean.getCourseEntity().getMajors() : courseBean.getSelectedMajors());
		courseBean.getCourseEntity().setSemester(courseBean.getSelectedSemester() == null ? courseBean.getCourseEntity().getSemester() : courseBean.getSelectedSemester());
		courseBean.getCourseEntity().setCourseItems(courseBean.getSelectedCourseItems() == null ? courseBean.getCourseEntity().getCourseItems() : courseBean.getSelectedCourseItems());
	}

	public String addCourse()
	{
		this.courseSetter();
		courseService.saveCourse(courseBean.getCourseEntity());
		courseBean.init();
		return "/admin/index.xhtml?faces-redirect=true";
	}
}
