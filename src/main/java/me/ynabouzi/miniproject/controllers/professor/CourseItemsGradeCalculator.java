package me.ynabouzi.miniproject.controllers.professor;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.professor.NotesBean;
import me.ynabouzi.miniproject.bean.professor.ProfessorUserBean;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.EvaluationEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.NoteEntityDAOImpl;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.model.NoteEntity;

import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@Named(value = "ProfessorCourseItemsGradeCalculatorController")
@RequestScoped
public class CourseItemsGradeCalculator implements Serializable {

	private static NoteEntityDAOImpl noteService = ServiceDAOFactory.getNoteService();

	private static CourseItemEntityDAOImpl courseItemEntityDAO = ServiceDAOFactory.getCourseItemService();

	private static EvaluationEntityDAOImpl evaluationService = ServiceDAOFactory.getEvaluationService();

	@Inject
	private NotesBean notesBean;

	@Inject
	private ProfessorUserBean professorUserBean;

	public void handleCourseItemAverageChange() {
		CourseItemEntity courseItem = professorUserBean.getSelectedCourseItem();

		double courseItemAverageGrade = calculateCourseItemAverageGrade(courseItem);
		if (courseItemAverageGrade != 0) {
			professorUserBean.setCourseItemAverageGrade(courseItemAverageGrade);
		}
	}

	public void calculateCourseAverageGrade() {
		CourseItemEntity course = professorUserBean.getSelectedCourseItem();

		List<CourseItemEntity> courseItems = course.getCourse().getCourseItems();
		boolean stillNotvalidated = false;
		double sumAverage = 0;
		double courseAverageGrade = 0;
		for (CourseItemEntity courseItem : courseItems) {
			if (!courseItem.isValidated()) {
				stillNotvalidated = true;
				break;
			}
			sumAverage += calculateCourseItemAverageGrade(courseItem);
		}

		if (!stillNotvalidated) {
			courseAverageGrade = sumAverage / courseItems.size();
			professorUserBean.setCourseAverageGrade(courseAverageGrade);
		}
	}

	public double calculateCourseItemAverageGrade(CourseItemEntity courseItem) {
		double courseItemAverageGrade = 0;
		if (courseItem != null && courseItem.isValidated()) {
			List<NoteEntity> notes = noteService.getAllNotesByCourseItem(courseItem.getId());
			List<EvaluationEntity> evaluationEntities = evaluationService.getAllEvaluationsByCourseItem(courseItem.getId());
			if (notes.isEmpty() || evaluationEntities.isEmpty()) {
				return 0;
			}
			double sumAverage = 0;
			for (NoteEntity note : notes) {

				double studentAverageGrade = note.getNoteCC() * evaluationEntities.get(0).getCoefficient() + note.getNoteProject() * evaluationEntities.get(1).getCoefficient() + note.getNoteTP() * evaluationEntities.get(2).getCoefficient() + note.getNotePresentation() * evaluationEntities.get(3).getCoefficient();
				;
				sumAverage += studentAverageGrade;
			}
			courseItemAverageGrade = sumAverage / notes.size();
		}
		return courseItemAverageGrade;
	}
}
