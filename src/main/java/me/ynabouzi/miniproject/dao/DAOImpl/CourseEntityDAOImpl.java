package me.ynabouzi.miniproject.dao.DAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import me.ynabouzi.miniproject.dao.EntityDAO;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;


@Transactional
public class CourseEntityDAOImpl implements EntityDAO<CourseEntity> {

	@PersistenceContext
	private EntityManager entityManager;

	public CourseEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public CourseEntity saveEntity(CourseEntity course) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(course);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return course;
	}

	@Override
	public CourseEntity getEntityById(Long id) {
		CourseEntity course = null;
		try {
			course = entityManager.find(CourseEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return course;
	}

	@Override
	public List<CourseEntity> getAllEntities() {
		List<CourseEntity> courseEntities = null;
		try {
			courseEntities = entityManager.createQuery("SELECT c FROM CourseEntity c", CourseEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return courseEntities;
	}

	@Override
	public boolean deleteEntity(Long id) {
		CourseEntity course = entityManager.find(CourseEntity.class, id);
		if (course != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(course);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public CourseEntity updateEntity(CourseEntity Newcourse, Long oldId) {
		Newcourse.setId(oldId);
		this.saveEntity(Newcourse);
		return Newcourse;
	}
}
