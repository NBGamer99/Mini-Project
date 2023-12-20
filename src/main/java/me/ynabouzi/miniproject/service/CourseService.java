package me.ynabouzi.miniproject.service;

import jakarta.persistence.EntityManager;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class CourseService {
	private final CourseEntityDAOImpl courseEntityDAOImpl;

	public CourseService() {
		this.courseEntityDAOImpl = new CourseEntityDAOImpl();
	}

	public void saveCourse(CourseEntity course) {
		courseEntityDAOImpl.saveCourse(course);
	}

	public CourseEntity getCourseById(Long id) {
		return courseEntityDAOImpl.getCourseById(id);
	}

	public void deleteCourse(Long id) {
		courseEntityDAOImpl.deleteCourse(id);
	}

	public List<CourseEntity> getAllCourses() {
		return courseEntityDAOImpl.getAllCourses();
	}

	public void updateCourse(CourseEntity Newcourse, long oldId) {
		courseEntityDAOImpl.updateCourse(Newcourse, oldId);
	}
}
