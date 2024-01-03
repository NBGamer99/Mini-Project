package me.ynabouzi.miniproject.controllers.admin.evaluations;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.admin.EvaluationsBean;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.EvaluationEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminAddEvaluationsController")
@RequestScoped
public class AddEvaluationsController implements Serializable {

	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();
	private static EvaluationEntityDAOImpl evaluationService = ServiceDAOFactory.getEvaluationService();

	@Inject
	private EvaluationsBean evaluationsBean;

	public void createAllEvaluations(CourseItemEntity courseItem, Long selectedCourseItemId)
	{
		EvaluationEntity evaluationEntity = new EvaluationEntity();
		evaluationEntity.setType("CC");
		evaluationEntity.setCourseItem(courseItem);

		EvaluationEntity evaluationEntity2 = new EvaluationEntity();
		evaluationEntity2.setType("Project");
		evaluationEntity2.setCourseItem(courseItem);

		EvaluationEntity evaluationEntity3 = new EvaluationEntity();
		evaluationEntity3.setType("TP");
		evaluationEntity3.setCourseItem(courseItem);

		EvaluationEntity evaluationEntity4 = new EvaluationEntity();
		evaluationEntity4.setType("Presentation");
		evaluationEntity4.setCourseItem(courseItem);

		courseItem.setEvaluations(List.of(evaluationEntity, evaluationEntity2, evaluationEntity3, evaluationEntity4));

		courseItemService.updateCourseItem(courseItem, selectedCourseItemId);

		evaluationService.saveEvaluationEntity(evaluationEntity);
		evaluationService.saveEvaluationEntity(evaluationEntity2);
		evaluationService.saveEvaluationEntity(evaluationEntity3);
		evaluationService.saveEvaluationEntity(evaluationEntity4);

		evaluationsBean.setIsCreated(true);
	}

	public void createEvaluations() {

		Long selectedCourseItemId = evaluationsBean.getSelectedCourseItemId();

		System.out.println("Create Evaluation : " + evaluationsBean.getSelectedCourseItemId());

		CourseItemEntity courseItem = courseItemService.getCourseItemById(selectedCourseItemId);

		if (!evaluationsBean.getIsCreated())
			createAllEvaluations(courseItem, selectedCourseItemId);


		evaluationsBean.setCreatedEvaluations(courseItem.getEvaluations());
		evaluationsBean.getEvaluations();
	}



}
