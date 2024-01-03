package me.ynabouzi.miniproject.controllers.admin.evaluations;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.admin.EvaluationsBean;
import me.ynabouzi.miniproject.dao.EvaluationEntityDAOImpl;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;

import java.io.Serializable;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import me.ynabouzi.miniproject.validator.CoefficientSumValidator;


@Setter
@Getter
@Data
@Named(value = "AdminSaveEvaluationsController")
@RequestScoped
public class SaveEvaluationsController implements Serializable {


	private static EvaluationEntityDAOImpl evaluationService = ServiceDAOFactory.getEvaluationService();

	private CoefficientSumValidator coefficientSumValidator = new CoefficientSumValidator();

	@Inject
	private EvaluationsBean evaluationsBean;


	public String saveEvaluations()
	{

		try {
			coefficientSumValidator.validate(evaluationsBean);
			for (EvaluationEntity evaluation : evaluationsBean.getCreatedEvaluations()) {
				evaluationService.updateEvaluationEntity(evaluation, evaluation.getId());
				System.out.println("Coeffecient is :" + evaluation.getCoefficient());
			}
			return "#";
		}catch (ValidatorException e) {
			FacesContext.getCurrentInstance().addMessage(null, e.getFacesMessage());
			return null; //
		}
	}



}
