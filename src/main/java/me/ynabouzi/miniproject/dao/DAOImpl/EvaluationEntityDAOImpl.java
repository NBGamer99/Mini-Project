package me.ynabouzi.miniproject.dao.DAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.dao.EntityDAO;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class EvaluationEntityDAOImpl implements EntityDAO<EvaluationEntity> {

	@PersistenceContext
	private EntityManager entityManager;

	public EvaluationEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public EvaluationEntity saveEntity(EvaluationEntity evaluationEntity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(evaluationEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return evaluationEntity;
	}

	@Override
	public EvaluationEntity getEntityById(Long id) {
		EvaluationEntity evaluationEntity = null;
		try {
			evaluationEntity = entityManager.find(EvaluationEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return evaluationEntity;
	}

	public List<EvaluationEntity> getAllEvaluationsByCourseItem(Long courseItemId) {
		List<EvaluationEntity> evaluationEntities = null;
		try {
			evaluationEntities = entityManager.createQuery("SELECT e FROM EvaluationEntity e WHERE e.courseItem.id = :courseItemId", EvaluationEntity.class)
					.setParameter("courseItemId", courseItemId)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return evaluationEntities;
	}

	@Override
	public List<EvaluationEntity> getAllEntities() {
		List<EvaluationEntity> evaluationEntities = null;
		try {
			evaluationEntities = entityManager.createQuery("SELECT e FROM EvaluationEntity e", EvaluationEntity.class)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return evaluationEntities;
	}

	@Override
	public boolean deleteEntity(Long id) {
		EvaluationEntity evaluationEntity = entityManager.find(EvaluationEntity.class, id);
		if (evaluationEntity != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(evaluationEntity);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public EvaluationEntity updateEntity(EvaluationEntity newEvaluationEntity, Long id) {
		newEvaluationEntity.setId(id);
		this.saveEntity(newEvaluationEntity);
		return newEvaluationEntity;
	}
}
