package me.ynabouzi.miniproject.dao;

import java.util.List;

public interface EntityDAO<T> {
	T saveEntity(T entity);

	T getEntityById(Long id);

	List<T> getAllEntities();

	boolean deleteEntity(Long id);

	T updateEntity(T newEntity, Long oldId);
}
