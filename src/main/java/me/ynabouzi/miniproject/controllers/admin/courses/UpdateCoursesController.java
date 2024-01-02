package me.ynabouzi.miniproject.controllers.admin.courses;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.CourseBean;
import me.ynabouzi.miniproject.util.AdminController;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseEntity;

import java.io.IOException;
import java.io.Serializable;

@Setter
@Getter
@Data
@Named(value = "AdminUpdateCoursesController")
@RequestScoped
public class UpdateCoursesController implements Serializable {

	private CourseEntityDAOImpl courseService = AdminController.getCourseService();
	private CourseItemEntityDAOImpl courseItemService = AdminController.getCourseItemService();


	@Inject
	private CourseBean courseBean;

	public String redirectToEditPage(CourseEntity courseEntity){
		this.courseBean.setCourseEntity(courseEntity);
		this.courseBean.setSelectedSemester(courseEntity.getSemester());
		this.courseBean.setSelectedMajors(courseEntity.getMajors());
		this.courseBean.setSelectedCourseItems(courseEntity.getCourseItems());
		return "/admin/courses/update-course.xhtml?faces-redirect=true";
	}

	public String updateCourse(){
		courseService.updateCourse(courseBean.getCourseEntity(), courseBean.getCourseEntity().getId());
		courseBean.init();
		return "/admin/index.xhtml?faces-redirect=true";
	}
}
