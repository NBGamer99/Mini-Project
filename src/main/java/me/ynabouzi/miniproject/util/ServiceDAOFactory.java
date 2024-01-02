package me.ynabouzi.miniproject.util;

import me.ynabouzi.miniproject.dao.*;

public class ServiceDAOFactory {

	protected static EvaluationEntityDAOImpl evaluationService;
	protected static CourseEntityDAOImpl courseService;

	protected static CourseItemEntityDAOImpl courseItemService;

	protected static ProfessorEntityDAOImpl professorService;

	protected static StudentEntityDAOImpl studentService;

	protected static UserEntityDAOImpl userService;


public static EvaluationEntityDAOImpl getEvaluationService() {
		if (evaluationService == null) {
			evaluationService = new EvaluationEntityDAOImpl();
		}
		return evaluationService;
	}

	public static CourseEntityDAOImpl getCourseService() {
		if (courseService == null) {
			courseService = new CourseEntityDAOImpl();
		}
		return courseService;
	}

	public static CourseItemEntityDAOImpl getCourseItemService() {
		if (courseItemService == null) {
			courseItemService = new CourseItemEntityDAOImpl();
		}
		return courseItemService;
	}

	public static ProfessorEntityDAOImpl getProfessorService() {
		if (professorService == null) {
			professorService = new ProfessorEntityDAOImpl();
		}
		return professorService;
	}

	public static UserEntityDAOImpl getUserService() {
		if (userService == null) {
			userService = new UserEntityDAOImpl();
		}
		return userService;
	}

	public static StudentEntityDAOImpl getStudentService() {
		if (studentService == null) {
			studentService = new StudentEntityDAOImpl();
		}
		return studentService;
	}
}
