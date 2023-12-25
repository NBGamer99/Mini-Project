package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.StudentEntity;

import java.util.List;

public interface StudentEntityDAO {

	StudentEntity getStudentById(Long id);

	StudentEntity getStudentByLastName(String name);

	List<StudentEntity> getAllStudents();

	StudentEntity saveStudent(StudentEntity student);

	boolean deleteStudent(Long id);

	StudentEntity updateStudent(StudentEntity student, Long id);

}
