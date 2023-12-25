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
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return courseItem;
	}

	@Override
	public CourseItemEntity getCourseItemById(Long id) {
		CourseItemEntity courseItem = null;
		try {
			courseItem = entityManager.find(CourseItemEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return courseItem;
	}

	@Override
	public List<CourseItemEntity> getCourseItemByCourseId(Long id) {
		List<CourseItemEntity> courseItemEntities = null;
		try {
			courseItemEntities = entityManager.createQuery("SELECT c FROM CourseItemEntity c WHERE c.course_parent.id = :id", CourseItemEntity.class)
					.setParameter("id", id)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return courseItemEntities;
	}

	@Override
	public List<CourseItemEntity> getAllCourseItems() {
		List<CourseItemEntity> courseItemEntities = null;
		try {
			courseItemEntities = entityManager.createQuery("SELECT c FROM CourseItemEntity c", CourseItemEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return courseItemEntities;
	}

	@Override
	public boolean deleteCourseItem(Long id) {
		CourseItemEntity courseItem = entityManager.find(CourseItemEntity.class, id);
		if (courseItem != null) {
			entityManager.remove(courseItem);
			return true;
		}
		return false;
	}

	@Override
	public CourseItemEntity updateCourseItem(CourseItemEntity newCourseItem, Long id) {
		newCourseItem.setId(id);
		this.saveCourseItem(newCourseItem);
		return newCourseItem;
	}
}
