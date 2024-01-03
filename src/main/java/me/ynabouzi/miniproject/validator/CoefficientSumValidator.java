package me.ynabouzi.miniproject.validator;

import jakarta.faces.application.Application;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import me.ynabouzi.miniproject.bean.admin.EvaluationsBean;
import me.ynabouzi.miniproject.model.EvaluationEntity;

import java.util.ArrayList;
import java.util.List;

public class CoefficientSumValidator{

    public void validate(EvaluationsBean evaluationsBean) throws ValidatorException {
        System.out.println("evaluationsBean: " + evaluationsBean.getEvaluations());
        // Access the properties or methods of the bean
        List<EvaluationEntity> createdEvaluations = evaluationsBean.getEvaluations();

        System.out.println("createdEvaluations: " + createdEvaluations.get(0).getCoefficient());

        double total = createdEvaluations.stream()
                .mapToDouble(evaluation -> evaluation.getCoefficient() != null ? evaluation.getCoefficient() * 100 : 0.0)
                .sum();

        System.out.println("total: " + total);

        if (total != 100.0) {
            FacesMessage message = new FacesMessage("Sum of coefficients must be 100");
            throw new ValidatorException(message);
        }
    }
}