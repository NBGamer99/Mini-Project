package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return course;
	}

	@Override
	public CourseEntity getCourseById(Long id) {
		CourseEntity course = null;
		try {
			course = entityManager.find(CourseEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return course;
	}

	@Override
	public List<CourseEntity> getAllCourses() {
		List<CourseEntity> courseEntities = null;
		try {
			courseEntities = entityManager.createQuery("SELECT c FROM CourseEntity c", CourseEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return courseEntities;
	}

	@Override
	public boolean deleteCourse(Long id) {
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
	public CourseEntity updateCourse(CourseEntity Newcourse, Long oldId) {
		Newcourse.setId(oldId);
		this.saveCourse(Newcourse);
		return Newcourse;
	}
}
