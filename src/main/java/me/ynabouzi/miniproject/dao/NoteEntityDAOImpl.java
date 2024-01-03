package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.NoteEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class NoteEntityDAOImpl implements NotesEntityDAO{

	@PersistenceContext
	private EntityManager entityManager;

	public NoteEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public NoteEntity getNoteById(Long id) {
		NoteEntity note = null;
		try {
			note = entityManager.find(NoteEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return note;
	}

	@Override
	public NoteEntity saveNoteEntity(NoteEntity noteEntity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(noteEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return noteEntity;
	}

	@Override
	public NoteEntity updateNoteEntity(NoteEntity noteEntity, Long NoteId) {
		noteEntity.setId(NoteId);
		this.saveNoteEntity(noteEntity);
		return noteEntity;
	}

	@Override
	public boolean deleteNoteEntity(Long id) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(this.getNoteById(id));
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
			return false;
		}
		return true;
	}

	@Override
	public List<NoteEntity> getAllNotesByCourseItem(Long courseItemId) {
		List<NoteEntity> notes = null;
		try {
			notes = entityManager.createQuery("SELECT n FROM NoteEntity n WHERE n.courseItem.id = :courseItemId", NoteEntity.class)
					.setParameter("courseItemId", courseItemId)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return notes;
	}

	@Override
	public List<NoteEntity> getAllNotesByStudent(Long studentId) {
		List<NoteEntity> notes = null;
		try {
			notes = entityManager.createQuery("SELECT n FROM NoteEntity n WHERE n.student.id = :studentId", NoteEntity.class)
					.setParameter("studentId", studentId)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return notes;
	}
}
