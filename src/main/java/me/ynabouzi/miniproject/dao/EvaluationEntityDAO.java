package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.EvaluationEntity;

import java.util.List;

public interface EvaluationEntityDAO {

	EvaluationEntity getEvaluationEntityById(Long id);

	List<EvaluationEntity> getEvaluationEntityByCourseItem(CourseItemEntity courseItem);

	EvaluationEntity saveEvaluationEntity(EvaluationEntity evaluationEntity);

	boolean deleteEvaluationEntity(Long id);

	EvaluationEntity updateEvaluationEntity(EvaluationEntity newEvaluationEntity, Long id);

}
