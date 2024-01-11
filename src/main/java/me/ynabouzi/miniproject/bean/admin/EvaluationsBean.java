package me.ynabouzi.miniproject.bean.admin;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.EvaluationEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;

import java.io.Serializable;
import java.util.List;

//@Data
@Getter
@Setter
@Named(value = "EvaluationsBean")
@SessionScoped
public class EvaluationsBean implements Serializable {


	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();

	private static EvaluationEntityDAOImpl evaluationService = ServiceDAOFactory.getEvaluationService();
	private List<EvaluationEntity> evaluations;
	private List<CourseItemEntity> availableCourseItems;

	private List<EvaluationEntity> createdEvaluations;

	private Boolean isCreated = false;

	private Long selectedCourseItemId;

	public EvaluationsBean() {
		availableCourseItems = courseItemService.getAllEntities();
	}

	public void changeValues() {
		availableCourseItems = courseItemService.getAllEntities();
	}

	@PostConstruct
	public void init() {
		setEvaluations(createdEvaluations);
		this.setAvailableCourseItems(courseItemService.getAllEntities());
		if (selectedCourseItemId != null)
			selectedCourseItemId = null;
	}

	public List<EvaluationEntity> getEvaluations() {
		List<EvaluationEntity> localEvaluations = null;
		if (selectedCourseItemId != null) {
			localEvaluations = courseItemService.getEntityById(selectedCourseItemId).getEvaluations();
			setCreatedEvaluations(localEvaluations);
			setIsCreated(true);
		}
		return localEvaluations;
	}


}

