package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.CourseItemEntity;

import java.util.List;

public interface CourseItemEntityDAO {
	CourseItemEntity saveCourseItem(CourseItemEntity courseItem);

	CourseItemEntity getCourseItemById(Long id);

	List<CourseItemEntity> getCourseItemByCourseId(Long id);

	List<CourseItemEntity> getAllCourseItems();

	void deleteCourseItem(Long id);

	void updateCourseItem(CourseItemEntity newCourseItem, Long id);
}
