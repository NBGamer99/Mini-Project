package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.UserEntity;

import java.util.List;

public class UserEntityDAOImpl implements UserEntityDAO{

	@PersistenceContext
	private EntityManager entityManager;

	public UserEntityDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public UserEntity getUserById(Long id) {
		return entityManager.find(UserEntity.class, id);
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		return entityManager.find(UserEntity.class, username);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		try {
			return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserEntity saveUser(UserEntity user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(user);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		UserEntity user = entityManager.find(UserEntity.class, id);
		if(user != null) {
			entityManager.remove(user);
		}
	}

	@Override
	public void updateUser(UserEntity user, Long id) {
		user.setId(id);
		this.saveUser(user);
	}
}
