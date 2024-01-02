package me.ynabouzi.miniproject.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.EvaluationEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.EvaluationEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;

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
		setIsCreated(false);
		availableCourseItems = courseItemService.getAllCourseItems();
	}

	public void changeValues() {
		availableCourseItems = courseItemService.getAllCourseItems();
	}

	@PostConstruct
	public void init() {
		setEvaluations(createdEvaluations);
		this.setAvailableCourseItems(courseItemService.getAllCourseItems());
		if (selectedCourseItemId != null)
			selectedCourseItemId = null;
	}

	public void getEvaluations() {
		if (selectedCourseItemId != null)
			setCreatedEvaluations(courseItemService.getCourseItemById(selectedCourseItemId).getEvaluations());
	}



}

