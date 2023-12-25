package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class StudentEntityDAOImpl implements StudentEntityDAO{

	@PersistenceContext
	private EntityManager entityManager;

	public StudentEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public StudentEntity getStudentById(Long id) {
		return entityManager.find(StudentEntity.class, id);
	}

	@Override
	public StudentEntity getStudentByLastName(String name) {
		return entityManager.createQuery("SELECT s FROM StudentEntity s WHERE s.lastName = :name", StudentEntity.class)
				.setParameter("name", name)
				.getSingleResult();
	}

	@Override
	public List<StudentEntity> getAllStudents() {
		try{
			return entityManager.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public StudentEntity saveStudent(StudentEntity student) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(student);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return student;
	}

	@Override
	public void deleteStudent(Long id) {
		StudentEntity student = entityManager.find(StudentEntity.class, id);
		if(student != null) {
			entityManager.remove(student);
		}
	}

	@Override
	public void updateStudent(StudentEntity newStudent, Long id) {
		newStudent.setId(id);
		this.saveStudent(newStudent);
	}
}
