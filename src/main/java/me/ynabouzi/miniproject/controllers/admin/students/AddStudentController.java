package me.ynabouzi.miniproject.controllers.admin.students;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.admin.StudentBean;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.DAOImpl.StudentEntityDAOImpl;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminAddStudentController")
@RequestScoped
public class AddStudentController {

	private static StudentEntityDAOImpl studentService = ServiceDAOFactory.getStudentService();

	private static CourseEntityDAOImpl courseService = ServiceDAOFactory.getCourseService();

	@Inject
	private StudentBean studentBean;


public String addStudent() {
	StudentEntity studentEntity = studentBean.getStudentEntity();


	for (CourseEntity course : studentBean.getSelectedCourses()) {
//		course.setStudents(studentEntity);
		if (course.getStudents() != null)
			course.getStudents().add(studentEntity);
		else
			course.setStudents(List.of(studentEntity));

		courseService.updateEntity(course, course.getId());
	}
	studentEntity.setCourse_student(studentBean.getSelectedCourses());
	studentService.saveEntity(studentBean.getStudentEntity());

	studentBean.init();
	return "/admin/student/students-list.xhtml?faces-redirect=true";
	}


}
