package me.ynabouzi.miniproject.controllers.admin.courses;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.admin.CourseBean;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseEntity;

import java.io.Serializable;

@Setter
@Getter
@Data
@Named(value = "AdminUpdateCoursesController")
@RequestScoped
public class UpdateCoursesController implements Serializable {

	private static CourseEntityDAOImpl courseService = ServiceDAOFactory.getCourseService();
	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();


	@Inject
	private CourseBean courseBean;

	private void courseSetter(CourseEntity courseEntity)
	{
		this.courseBean.setCourseEntity(courseEntity);
		this.courseBean.setSelectedSemester(courseEntity.getSemester());
		this.courseBean.setSelectedMajors(courseEntity.getMajors());
		this.courseBean.setSelectedCourseItems(courseEntity.getCourseItems());
	}

	public String redirectToEditPage(CourseEntity courseEntity)
	{
		this.courseSetter(courseEntity);
		return "/admin/courses/update-course.xhtml?faces-redirect=true";
	}

	public String updateCourse(){
		courseService.updateCourse(courseBean.getCourseEntity(), courseBean.getCourseEntity().getId());
		courseBean.init();
		return "/admin/index.xhtml?faces-redirect=true";
	}
}
