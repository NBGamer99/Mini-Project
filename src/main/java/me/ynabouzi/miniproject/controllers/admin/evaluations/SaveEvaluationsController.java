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
import me.ynabouzi.miniproject.dao.DAOImpl.EvaluationEntityDAOImpl;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

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
			List<EvaluationEntity> evaluations = evaluationsBean.getCreatedEvaluations();
			System.out.println("Evaluations re :" + evaluations);
			for (int i = 0; i < evaluations.size(); i++) {
				System.out.println("Evaluations before is :" + evaluations);
				evaluationService.updateEntity(evaluations.get(i), evaluations.get(i).getId());
				System.out.println("Evaluations after is :" + evaluations);
//				System.out.println("Coeffecient is :" + evaluation.g.getCoefficient());
			}
			return "#";
		}catch (ValidatorException e) {
			FacesContext.getCurrentInstance().addMessage(null, e.getFacesMessage());
			return null; //
		}
	}



}
