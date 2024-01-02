package me.ynabouzi.miniproject.controllers.admin.students;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.StudentEntityDAOImpl;
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
		students = studentService.getAllStudents();
		return students;
	}

	public void deleteStudent(Long id) {
		if (studentService.deleteStudent(id))
		{
			this.init();
		}
	}

}
