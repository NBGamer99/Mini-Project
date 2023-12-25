package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.model.UserEntity;
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
		StudentEntity student = null;
		try {
			student = entityManager.find(StudentEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return student;
	}

	@Override
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
	public List<StudentEntity> getAllStudents() {
		List<StudentEntity> studentEntities = null;
		try {
			studentEntities = entityManager.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return studentEntities;
	}

	@Override
	public StudentEntity saveStudent(StudentEntity student) {
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
	public boolean deleteStudent(Long id) {
		StudentEntity student = this.getStudentById(id);
		if(student != null) {
			entityManager.remove(student);
			return true;
		}
		return false;
	}

	@Override
	public StudentEntity updateStudent(StudentEntity newStudent, Long id) {
		newStudent.setId(id);
		this.saveStudent(newStudent);
		return newStudent;
	}
}
