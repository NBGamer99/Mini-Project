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
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.util.CSVExporter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Named(value = "ProfessorCourseItemsNotesController")
@RequestScoped
public class CourseItemsNotesController implements Serializable {

	private static NoteEntityDAOImpl noteService = ServiceDAOFactory.getNoteService();

	private static CourseItemEntityDAOImpl courseItemEntityDAO = ServiceDAOFactory.getCourseItemService();

	private static EvaluationEntityDAOImpl evaluationService = ServiceDAOFactory.getEvaluationService();

	@Inject
	private NotesBean notesBean;

	@Inject
	private ProfessorUserBean professorUserBean;

	public void createStudentNotes() {
		CourseItemEntity courseItem = professorUserBean.getSelectedCourseItem();
		List<StudentEntity> students = professorUserBean.getSelectedCourseItemStudents();
		Map<Long, List<NoteEntity>> notesByCourseItem = notesBean.getNotesByCourseItem();
		List<NoteEntity> notes = noteService.getAllNotesByCourseItem(courseItem.getId());

		if (students != null && notesByCourseItem.get(courseItem.getId()) == null) {
			if (notes.isEmpty()) {
				notes = new ArrayList<>();
				for (StudentEntity student : students) {
					NoteEntity note = new NoteEntity();
					note.setStudent(student);
					note.setCourseItem(courseItem);
					notes.add(note);
				}
			}
			notesByCourseItem.put(courseItem.getId(), notes);
		}
	}

	public void clearStudentNotes() {
		CourseItemEntity courseItem = professorUserBean.getSelectedCourseItem();
		Map<Long, List<NoteEntity>> notesByCourseItem = notesBean.getNotesByCourseItem();
		List<StudentEntity> students = professorUserBean.getSelectedCourseItemStudents();

		List<NoteEntity> notes = new ArrayList<>();

		if (courseItem != null && notesByCourseItem.get(courseItem.getId()) != null) {
			notesByCourseItem.remove(courseItem.getId());
			for (StudentEntity student : students) {
				NoteEntity note = new NoteEntity();
				note.setStudent(student);
				note.setCourseItem(courseItem);
				notes.add(note);
			}
			notesByCourseItem.put(courseItem.getId(), notes);
		}

	}

	public List<String[]> normalizingData() {

		List<String[]> data = new ArrayList<>();

		data.add(new String[]{"Student Name", "Course Item", "Grade"});

		List<NoteEntity> notes = noteService.getAllNotesByCourseItem(professorUserBean.getSelectedCourseItem().getId());

		if (notes.get(0).getCourseItem().isValidated()) {
			for (NoteEntity note : notes) {
				data.add(new String[]{note.getStudent().getFirstName() + " " + note.getStudent().getLastName(), note.getCourseItem().getName()});
			}
		}
		return data;
	}
	public void exportToExcel() {

		CSVExporter.exportToCSV("exported/grades.csv", normalizingData());
	}



	public String saveStudentNotes() {
		CourseItemEntity courseItem = professorUserBean.getSelectedCourseItem();
		Map<Long, List<NoteEntity>> notesByCourseItem = notesBean.getNotesByCourseItem();

		if (courseItem != null && notesByCourseItem.get(courseItem.getId()) != null) {
			for (NoteEntity note : notesByCourseItem.get(courseItem.getId())) {
				handleAbsentNotes(note);
				noteService.saveEntity(note);
			}
			handValidation(courseItem);
		}
		return "/professor/index?faces-redirect=true";
	}

	public void handValidation(CourseItemEntity courseItem) {
		boolean noNull = false;
		List<NoteEntity> notes = noteService.getAllNotesByCourseItem(professorUserBean.getSelectedCourseItem().getId());

		for (NoteEntity note : notes) {
			if (note.getNoteCC() != null && note.getNoteTP() != null && note.getNoteProject() != null && note.getNotePresentation() != null) {
				noNull = true;
			} else {
				noNull = false;
				break;
			}
		}

		if (noNull) {
			courseItem.setValidated(true);
			courseItemEntityDAO.saveEntity(courseItem);
		}

	}

	private void handleAbsentNotes(NoteEntity note) {
		if (note.isAbsentCC()) {
			note.setNoteCC(0);
		}
		if (note.isAbsentTP()) {
			note.setNoteTP(0);
		}
		if (note.isAbsentProject()) {
			note.setNoteProject(0);
		}
		if (note.isAbsentPresentation()) {
			note.setNotePresentation(0);
		}
	}

}
