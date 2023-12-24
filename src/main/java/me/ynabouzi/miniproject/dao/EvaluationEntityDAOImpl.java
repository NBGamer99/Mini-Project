package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class EvaluationEntityDAOImpl implements EvaluationEntityDAO{

	@PersistenceContext
	private EntityManager entityManager;

	public EvaluationEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public EvaluationEntity saveEvaluationEntity(EvaluationEntity evaluationEntity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(evaluationEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return evaluationEntity;
	}
	@Override
	public EvaluationEntity getEvaluationEntityById(Long id) {
			return entityManager.find(EvaluationEntity.class, id);
	}

	@Override
	public List<EvaluationEntity> getEvaluationEntityByCourseId(Long courseId) {

		try
		{
			return entityManager.createQuery("SELECT e FROM EvaluationEntity e WHERE e.courseItem.id = :id", EvaluationEntity.class)
					.setParameter("id", courseId)
					.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteEvaluationEntity(Long id) {
		EvaluationEntity evaluationEntity = entityManager.find(EvaluationEntity.class, id);
		if(evaluationEntity != null) {
			entityManager.remove(evaluationEntity);
		}
	}

	@Override
	public void updateEvaluationEntity(EvaluationEntity newEvaluationEntity, Long id) {
		newEvaluationEntity.setId(id);
		this.saveEvaluationEntity(newEvaluationEntity);
	}
}
