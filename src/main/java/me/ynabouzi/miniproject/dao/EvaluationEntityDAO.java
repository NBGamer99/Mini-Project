package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.EvaluationEntity;

import java.util.List;

public interface EvaluationEntityDAO {

	EvaluationEntity getEvaluationEntityById(Long id);

	List<EvaluationEntity> getEvaluationEntityByCourseId(Long courseId);

	EvaluationEntity saveEvaluationEntity(EvaluationEntity evaluationEntity);

	void deleteEvaluationEntity(Long id);

	void updateEvaluationEntity(EvaluationEntity newEvaluationEntity, Long id);

}
