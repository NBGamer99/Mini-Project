package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.util.List;

public interface CourseEntityDAO {
	CourseEntity saveCourse(CourseEntity course);
	CourseEntity getCourseById(Long id);
	List<CourseEntity> getAllCourses();
	boolean deleteCourse(Long id);
	CourseEntity updateCourse(CourseEntity Newcourse, Long oldId);
}
