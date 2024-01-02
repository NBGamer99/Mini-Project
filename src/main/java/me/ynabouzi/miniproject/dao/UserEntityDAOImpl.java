package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.UserEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class UserEntityDAOImpl implements UserEntityDAO{

	@PersistenceContext
	private static EntityManager entityManager;

	public UserEntityDAOImpl() {
		entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public UserEntity getUserById(Long id) {
		UserEntity user = null;
		try {
			user = entityManager.find(UserEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		UserEntity user = null;
		try {
			user = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
					.setParameter("username", username)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> userEntities = null;
		try {
			userEntities = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userEntities;
	}

	@Override
	public UserEntity saveUser(UserEntity user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(user);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return user;
	}

	@Override
	public boolean deleteUser(Long id) {
		UserEntity user = this.getUserById(id);
		try {
			if(user != null) {
				entityManager.getTransaction().begin();
				entityManager.remove(user);
				entityManager.getTransaction().commit();
				return true;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public UserEntity updateUser(UserEntity user, Long id) {
		user.setId(id);
		this.saveUser(user);
		return user;
	}
}
