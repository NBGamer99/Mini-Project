package me.ynabouzi.miniproject.dao.DAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.dao.EntityDAO;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class StudentEntityDAOImpl implements EntityDAO<StudentEntity> {

	@PersistenceContext
	private EntityManager entityManager;

	public StudentEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public StudentEntity getEntityById(Long id) {
		StudentEntity student = null;
		try {
			student = entityManager.find(StudentEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return student;
	}

	public StudentEntity getStudentByLastName(String name) {
		StudentEntity student = null;
		try {
			student = entityManager.createQuery("SELECT s FROM StudentEntity s WHERE s.lastName = :name", StudentEntity.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return student;
	}

	@Override
	public List<StudentEntity> getAllEntities() {
		List<StudentEntity> studentEntities = null;
		try {
			studentEntities = entityManager.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return studentEntities;
	}

	@Override
	public StudentEntity saveEntity(StudentEntity student) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(student);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return student;
	}

	@Override
	public boolean deleteEntity(Long id) {
		StudentEntity student = this.getEntityById(id);
		if (student != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(student);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public StudentEntity updateEntity(StudentEntity newStudent, Long id) {
		newStudent.setId(id);
		this.saveEntity(newStudent);
		return newStudent;
	}
}
