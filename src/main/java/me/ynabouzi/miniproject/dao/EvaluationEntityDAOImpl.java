package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.CourseItemEntity;
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
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return evaluationEntity;
	}
	@Override
	public EvaluationEntity getEvaluationEntityById(Long id) {
		EvaluationEntity evaluationEntity = null;
		try {
			evaluationEntity = entityManager.find(EvaluationEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return evaluationEntity;
	}

	@Override
	public List<EvaluationEntity> getEvaluationEntityByCourseItem(CourseItemEntity courseItem) {
		List<EvaluationEntity> evaluationEntities = null;
		try
		{
			evaluationEntities = entityManager.createQuery("SELECT e FROM EvaluationEntity e WHERE e.courseItem = :courseItem", EvaluationEntity.class)
					.setParameter("courseItem", courseItem)
					.getResultList();}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return evaluationEntities;
	}

	@Override
	public boolean deleteEvaluationEntity(Long id) {
		EvaluationEntity evaluationEntity = entityManager.find(EvaluationEntity.class, id);
		if(evaluationEntity != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(evaluationEntity);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public EvaluationEntity updateEvaluationEntity(EvaluationEntity newEvaluationEntity, Long id) {
		newEvaluationEntity.setId(id);
		this.saveEvaluationEntity(newEvaluationEntity);
		return newEvaluationEntity;
	}
}
