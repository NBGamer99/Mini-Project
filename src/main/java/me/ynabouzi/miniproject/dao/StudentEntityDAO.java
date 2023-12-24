package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.StudentEntity;

import java.util.List;

public interface StudentEntityDAO {

	StudentEntity getStudentById(Long id);

	StudentEntity getStudentByName(String name);

	List<StudentEntity> getAllStudents();

	StudentEntity saveStudent(StudentEntity student);

	void deleteStudent(Long id);

	void updateStudent(StudentEntity student, Long id);

}
