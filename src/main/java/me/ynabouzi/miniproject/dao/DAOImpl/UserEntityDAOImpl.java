package me.ynabouzi.miniproject.dao.DAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.dao.EntityDAO;
import me.ynabouzi.miniproject.model.UserEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class UserEntityDAOImpl implements EntityDAO<UserEntity> {

	@PersistenceContext
	private static EntityManager entityManager;

	public UserEntityDAOImpl() {
		entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public UserEntity getEntityById(Long id) {
		UserEntity user = null;
		try {
			user = entityManager.find(UserEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	public UserEntity getUserByUsername(String username) {
		UserEntity user = null;
		try {
			user = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class).setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	@Override
	public List<UserEntity> getAllEntities() {
		List<UserEntity> userEntities = null;
		try {
			userEntities = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userEntities;
	}

	@Override
	public UserEntity saveEntity(UserEntity user) {
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
	public boolean deleteEntity(Long id) {
		UserEntity user = this.getEntityById(id);
		try {
			if (user != null) {
				entityManager.getTransaction().begin();
				entityManager.remove(user);
				entityManager.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public UserEntity updateEntity(UserEntity user, Long id) {
		user.setId(id);
		this.saveEntity(user);
		return user;
	}
}
