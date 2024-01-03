package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.NoteEntity;

import java.util.List;

public interface NotesEntityDAO {

	NoteEntity getNoteById(Long id);

	NoteEntity saveNoteEntity(NoteEntity noteEntity);

	NoteEntity updateNoteEntity(NoteEntity noteEntity, Long NoteId);

	boolean deleteNoteEntity(Long id);

	List<NoteEntity> getAllNotesByCourseItem(Long courseItemId);

	List<NoteEntity> getAllNotesByStudent(Long studentId);

}
