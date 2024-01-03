package me.ynabouzi.miniproject.controllers.professor;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.professor.NotesBean;
import me.ynabouzi.miniproject.bean.professor.ProfessorUserBean;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.NoteEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.NoteEntity;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Named(value = "ProfessorCourseItemsNotesController")
@RequestScoped
public class CourseItemsNotesController implements Serializable {

//	private static CourseEntityDAOImpl courseService = ServiceDAOFactory.getCourseService();

	private static NoteEntityDAOImpl noteService = ServiceDAOFactory.getNoteService();

	private static CourseItemEntityDAOImpl courseItemEntityDAO = ServiceDAOFactory.getCourseItemService();


	@Inject
	private NotesBean notesBean;

	@Inject
	private ProfessorUserBean professorUserBean;

	public void createStudentNotes()
	{
		CourseItemEntity courseItem = professorUserBean.getSelectedCourseItem();
		List<StudentEntity> students = professorUserBean.getSelectedCourseItemStudents();
		Map<Long, List<NoteEntity>> notesByCourseItem = notesBean.getNotesByCourseItem();
		List<NoteEntity> notes = new ArrayList<>();

//		System.out.println("createStudentNotes");
//		System.out.println("it's me twice");

		if (courseItem != null && students != null && notesByCourseItem.get(courseItem.getId()) == null)
		{
			System.out.println("courseItem != " + courseItem + "&& students.size != " + notes.size());

			for (StudentEntity student : students)
			{
				NoteEntity note = new NoteEntity();
				note.setStudent(student);
				note.setCourseItem(courseItem);
				notes.add(note);
			}
			notesByCourseItem.put(courseItem.getId(), notes);
		}


		System.out.println(notesByCourseItem);
//		notesBean.setNotesByCourseItem(notesByCourseItem);
	}

	public void clearStudentNotes()
	{
		CourseItemEntity courseItem = professorUserBean.getSelectedCourseItem();
		Map<Long, List<NoteEntity>> notesByCourseItem = notesBean.getNotesByCourseItem();
		if (courseItem != null && notesByCourseItem.get(courseItem.getId()) != null)
		{
			notesByCourseItem.remove(courseItem.getId());
		}
	}

	public String saveStudentNotes()
	{
		CourseItemEntity courseItem = professorUserBean.getSelectedCourseItem();
		Map<Long, List<NoteEntity>> notesByCourseItem = notesBean.getNotesByCourseItem();
		if (courseItem != null && notesByCourseItem.get(courseItem.getId()) != null)
		{
			for (NoteEntity note : notesByCourseItem.get(courseItem.getId()))
			{
				noteService.saveNoteEntity(note);
			}
		}
		courseItem.setValidated(true);
		courseItemEntityDAO.updateCourseItem(courseItem, courseItem.getId());

		return "/professor/index?faces-redirect=true";
	}

}
