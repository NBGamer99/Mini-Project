package me.ynabouzi.miniproject.controllers.admin.evaluations;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.EvaluationsBean;
import me.ynabouzi.miniproject.dao.EvaluationEntityDAOImpl;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;

import java.io.Serializable;

@Setter
@Getter
@Data
@Named(value = "AdminSaveEvaluationsController")
@RequestScoped
public class SaveEvaluationsController implements Serializable {


	private static EvaluationEntityDAOImpl evaluationService = ServiceDAOFactory.getEvaluationService();

	@Inject
	private EvaluationsBean evaluationsBean;

	public String saveEvaluations()
	{
		for (EvaluationEntity evaluation : evaluationsBean.getCreatedEvaluations()) {
			evaluationService.updateEvaluationEntity(evaluation, evaluation.getId());
			System.out.println("Coeffecient is :" + evaluation.getCoefficient());
		}
		return "#";
	}



}
