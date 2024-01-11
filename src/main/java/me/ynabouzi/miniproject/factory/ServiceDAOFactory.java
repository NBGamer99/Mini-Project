package me.ynabouzi.miniproject.factory;

import me.ynabouzi.miniproject.dao.DAOImpl.*;
import me.ynabouzi.miniproject.dao.EntityDAO;

public class ServiceDAOFactory {
	public enum ServiceType {
		NOTE, EVALUATION, COURSE, COURSE_ITEM, PROFESSOR, USER, STUDENT
	}

	protected static CourseItemEntityDAOImpl courseItemService;
	protected static EvaluationEntityDAOImpl evaluationService;
	protected static CourseEntityDAOImpl courseService;
	protected static ProfessorEntityDAOImpl professorService;
	protected static StudentEntityDAOImpl studentService;
	protected static UserEntityDAOImpl userService;
	protected static NoteEntityDAOImpl noteService;


	public static EntityDAO getService(ServiceType serviceType) {
		switch (serviceType) {
			case NOTE:
				return getNoteService();
			case EVALUATION:
				return getEvaluationService();
			case COURSE:
				return getCourseService();
			case USER:
				return getUserService();
			case STUDENT:
				return getStudentService();
			case PROFESSOR:
				return getProfessorService();
			case COURSE_ITEM:
				return getCourseItemService();
			default:
				throw new IllegalArgumentException("Invalid service type");
		}
	}

	public static NoteEntityDAOImpl getNoteService() {
		if (noteService == null) {
			noteService = new NoteEntityDAOImpl();
		}
		return noteService;
	}

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
