package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;


@Transactional
public class CourseEntityDAOImpl implements CourseEntityDAO{

	@PersistenceContext
	private EntityManager entityManager;

	public CourseEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public CourseEntity saveCourse(CourseEntity course) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(course);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return course;
	}

	@Override
	public CourseEntity getCourseById(Long id) {
		return entityManager.find(CourseEntity.class, id);
	}

	@Override
	public List<CourseEntity> getAllCourses() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseEntity> cq = cb.createQuery(CourseEntity.class);
		Root<CourseEntity> root = cq.from(CourseEntity.class);
		cq.select(root);
		TypedQuery<CourseEntity> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public void deleteCourse(Long id) {
		CourseEntity course = entityManager.find(CourseEntity.class, id);
		if (course != null) {
			entityManager.remove(course);
		}
	}

	@Override
	public void updateCourse(CourseEntity Newcourse, Long oldId) {
		Newcourse.setId(oldId);
		this.saveCourse(Newcourse);
	}
}
