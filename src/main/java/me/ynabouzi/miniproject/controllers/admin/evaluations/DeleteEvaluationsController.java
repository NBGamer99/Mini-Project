package me.ynabouzi.miniproject.controllers.admin.evaluations;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.EvaluationsBean;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.EvaluationEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;

import java.io.Serializable;

@Setter
@Getter
@Data
@Named(value = "AdminDeleteEvaluationsController")
@RequestScoped
public class DeleteEvaluationsController implements Serializable {

	private static EvaluationEntityDAOImpl evaluationService = ServiceDAOFactory.getEvaluationService();
	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();

	@Inject
	private EvaluationsBean evaluationsBean;


	public void deleteAllEvaluations() {
		Long courseItemId = evaluationsBean.getSelectedCourseItemId();
		CourseItemEntity courseItem = courseItemService.getCourseItemById(courseItemId);

		if (evaluationsBean.getCreatedEvaluations() != null && !evaluationsBean.getCreatedEvaluations().isEmpty())
		{
			courseItem.setEvaluations(null);
			for (EvaluationEntity evaluation : evaluationsBean.getCreatedEvaluations()) {
				evaluation.setCourseItem(null);
				evaluationService.deleteEvaluationEntity(evaluation.getId());
			}
			courseItemService.updateCourseItem(courseItem, courseItemId);
			evaluationsBean.setIsCreated(false);
		}
		evaluationsBean.getEvaluations();
	}
}
