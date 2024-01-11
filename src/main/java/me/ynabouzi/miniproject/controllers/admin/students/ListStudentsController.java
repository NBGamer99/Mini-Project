package me.ynabouzi.miniproject.controllers.admin.students;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.DAOImpl.StudentEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.StudentEntity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminListStudentsController")
@RequestScoped
public class ListStudentsController implements Serializable
{
	private static StudentEntityDAOImpl studentService = ServiceDAOFactory.getStudentService();

	private List<StudentEntity> students;

	@PostConstruct
	public void init() {
		students = fetchStudentsFromBackend();
	}

	private List<StudentEntity> fetchStudentsFromBackend() {
		students = studentService.getAllEntities();
		return students;
	}

	public void deleteStudent(Long id) {
		StudentEntity student = studentService.getEntityById(id);
		unsetAttributes(student);
		if (studentService.deleteEntity(id))
		{
			this.init();
		}
	}

	private void unsetAttributes(StudentEntity student) {
		if (student.getCourse_student() != null)
			student.getCourse_student().forEach(course -> course.getStudents().remove(student));
	}

}
