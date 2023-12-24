package me.ynabouzi.miniproject.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class CourseItemEntityDAOImpl implements CourseItemEntityDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public CourseItemEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public CourseItemEntity saveCourseItem(CourseItemEntity courseItem) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(courseItem);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return courseItem;
	}

	@Override
	public CourseItemEntity getCourseItemById(Long id) {
		return entityManager.find(CourseItemEntity.class, id);
	}

	@Override
	public List<CourseItemEntity> getCourseItemByCourseId(Long id) {
		try
		{
			return entityManager.createQuery("SELECT c FROM CourseItemEntity c WHERE c.course_parent.id = :id", CourseItemEntity.class)
					.setParameter("id", id)
					.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CourseItemEntity> getAllCourseItems() {
		try {
			return entityManager.createQuery("SELECT c FROM CourseItemEntity c", CourseItemEntity.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteCourseItem(Long id) {
		CourseItemEntity courseItem = entityManager.find(CourseItemEntity.class, id);
		if (courseItem != null) {
			entityManager.remove(courseItem);
		}
	}

	@Override
	public void updateCourseItem(CourseItemEntity newCourseItem, Long id) {
		newCourseItem.setId(id);
		this.saveCourseItem(newCourseItem);
	}
}
