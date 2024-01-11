package me.ynabouzi.miniproject.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import me.ynabouzi.miniproject.bean.admin.EvaluationsBean;
import me.ynabouzi.miniproject.model.EvaluationEntity;

import java.util.ArrayList;
import java.util.List;

public class CoefficientSumValidator{
    public void validate(EvaluationsBean evaluationsBean) throws ValidatorException {
        List<EvaluationEntity> createdEvaluations = evaluationsBean.getEvaluations();
        double total = createdEvaluations.stream()
                .mapToDouble(evaluation -> evaluation.getCoefficient() != null ? evaluation.getCoefficient() * 100 : 0.0)
                .sum();
        if (total != 100.0) {
            FacesMessage message = new FacesMessage("Sum of coefficients must be 100");
            throw new ValidatorException(message);
        }
    }
}