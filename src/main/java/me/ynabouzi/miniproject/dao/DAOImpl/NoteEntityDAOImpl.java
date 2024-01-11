package me.ynabouzi.miniproject.dao.DAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.dao.EntityDAO;
import me.ynabouzi.miniproject.model.NoteEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class NoteEntityDAOImpl implements EntityDAO<NoteEntity> {

	@PersistenceContext
	private EntityManager entityManager;

	public NoteEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	public NoteEntityDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public NoteEntity getEntityById(Long id) {
		NoteEntity note = null;
		try {
			note = entityManager.find(NoteEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return note;
	}

	@Override
	public List<NoteEntity> getAllEntities() {
		List<NoteEntity> notes = null;
		try {
			notes = entityManager.createQuery("SELECT n FROM NoteEntity n", NoteEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return notes;
	}

	@Override
	public NoteEntity saveEntity(NoteEntity noteEntity) {
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
	public NoteEntity updateEntity(NoteEntity noteEntity, Long NoteId) {
		noteEntity.setId(NoteId);
		this.saveEntity(noteEntity);
		return noteEntity;
	}

	@Override
	public boolean deleteEntity(Long id) {
		try {
			entityManager.getTransaction().begin();

			NoteEntity note = this.getEntityById(id);
			if (note != null)
			{
				entityManager.remove(note);
				entityManager.getTransaction().commit();
				return true;
			}else{
				entityManager.getTransaction().rollback();
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
			return false;
		}
	}

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

	public List<NoteEntity> getAllNotesOfStudent(Long studentId) {
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
