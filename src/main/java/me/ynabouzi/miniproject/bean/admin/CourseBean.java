package me.ynabouzi.miniproject.bean.admin;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.enums.Major;
import me.ynabouzi.miniproject.enums.Semester;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@Getter
@Setter
@Named(value = "CourseBean")
@SessionScoped
public class CourseBean implements Serializable {

	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();
	private List<Major> selectedMajors;
	private List<Major> availableMajors;
	private List<Semester> availableSemesters;
	private List<CourseItemEntity> selectedCourseItems;
	private List<CourseItemEntity> availableCourseItems;
	private Semester selectedSemester;
	private CourseEntity courseEntity;

	public CourseBean() {
		this.setCourseEntity(new CourseEntity());
		setAvailableCourseItems(ServiceDAOFactory.getCourseItemService().getAllEntities());
		setAvailableMajors(Arrays.asList(Major.values()));
		setAvailableSemesters(Arrays.asList(Semester.values()));
	}

	public void changeValues() {
		this.setAvailableCourseItems(courseItemService.getAllEntities());
		this.setAvailableMajors(Arrays.asList(Major.values()));
		this.setAvailableSemesters(Arrays.asList(Semester.values()));
	}

	@PostConstruct
	public void init() {
		this.setCourseEntity(new CourseEntity());
		this.setAvailableCourseItems(courseItemService.getAllEntities());
		if (selectedCourseItems != null && !selectedCourseItems.isEmpty())
			selectedCourseItems.clear();
		if (selectedMajors != null && !selectedMajors.isEmpty())
			selectedMajors.clear();
		if (selectedSemester != null)
			selectedSemester = null;
	}
}
