package me.ynabouzi.miniproject.dao.DAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.dao.EntityDAO;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class CourseItemEntityDAOImpl implements EntityDAO<CourseItemEntity> {

	@PersistenceContext
	private EntityManager entityManager;

	public CourseItemEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public CourseItemEntity saveEntity(CourseItemEntity courseItem) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(courseItem);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return courseItem;
	}

	@Override
	public List<CourseItemEntity> getAllEntities() {
		List<CourseItemEntity> courseItemEntities = null;
		try {
			courseItemEntities = entityManager.createQuery("SELECT c FROM CourseItemEntity c", CourseItemEntity.class)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return courseItemEntities;
	}

	@Override
	public CourseItemEntity getEntityById(Long id) {
		CourseItemEntity courseItem = null;
		try {
			courseItem = entityManager.find(CourseItemEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return courseItem;
	}

	@Override
	public boolean deleteEntity(Long id) {
		CourseItemEntity courseItem = entityManager.find(CourseItemEntity.class, id);
		if (courseItem != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(courseItem);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public CourseItemEntity updateEntity(CourseItemEntity newCourseItem, Long id) {
		newCourseItem.setId(id);
		this.saveEntity(newCourseItem);
		return newCourseItem;
	}
}
