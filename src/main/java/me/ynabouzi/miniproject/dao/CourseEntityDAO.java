package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.CourseEntity;

import java.util.List;

public interface CourseEntityDAO {
	CourseEntity saveCourse(CourseEntity course);
	CourseEntity getCourseById(Long id);
	List<CourseEntity> getAllCourses();
	void deleteCourse(Long id);
	void updateCourse(CourseEntity Newcourse, long oldId);
}
