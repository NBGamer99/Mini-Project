package me.ynabouzi.miniproject.controllers.admin.students;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.StudentBean;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.StudentEntityDAOImpl;

@Setter
@Getter
@Data
@Named(value = "AdminAddStudentController")
@RequestScoped
public class AddStudentController {

	private static StudentEntityDAOImpl studentService = ServiceDAOFactory.getStudentService();

	@Inject
	private StudentBean studentBean;

public String addStudent() {
		StudentEntity studentEntity = studentBean.getStudentEntity();
		studentEntity.setCourse_student(studentBean.getSelectedCourses());
		studentService.saveStudent(studentBean.getStudentEntity());
		studentBean.init();
		return "/admin/student/students-list.xhtml?faces-redirect=true";
	}


}
