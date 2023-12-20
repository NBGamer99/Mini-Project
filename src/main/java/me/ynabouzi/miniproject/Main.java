package me.ynabouzi.miniproject;

import jakarta.persistence.EntityManager;
import me.ynabouzi.miniproject.enums.Major;
import me.ynabouzi.miniproject.enums.Semester;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.service.CourseService;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

public class Main {
	public static void main(String[] args) {
		CourseEntity course = new CourseEntity();
		course.setCode("CSE");
		course.setName("Computer Science");
		course.setMajor(Major.IID);
		course.setSemester(Semester.S1);

		CourseService courseService = new CourseService();
		courseService.saveCourse(course);

	}
}
