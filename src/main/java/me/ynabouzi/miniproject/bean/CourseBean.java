package me.ynabouzi.miniproject.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.enums.Major;
import me.ynabouzi.miniproject.enums.Semester;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.CourseItemEntity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@Getter
@Setter
@Named(value = "CourseBean")
@SessionScoped
// CourseBean for handling UI-specific course operations
public class CourseBean implements Serializable {

	private List<Major> selectedMajors;
	private List<Major> availableMajors;

	private List<Semester> availableSemesters;
	private List<CourseItemEntity> selectedCourseItems;
	private List<CourseItemEntity> availableCourseItems;

	private Semester selectedSemester;
	private CourseEntity courseEntity;

	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();

	public CourseBean() {
		this.setCourseEntity(new CourseEntity());
		setAvailableCourseItems(ServiceDAOFactory.getCourseItemService().getAllCourseItems());
		setAvailableMajors(Arrays.asList(Major.values()));
		setAvailableSemesters(Arrays.asList(Semester.values()));
	}

	@PostConstruct
	public void init() {
		this.setCourseEntity(new CourseEntity());
		this.setAvailableCourseItems(courseItemService.getAllCourseItems());
		if(selectedCourseItems != null && !selectedCourseItems.isEmpty())
			selectedCourseItems.clear();
		if (selectedMajors != null && !selectedMajors.isEmpty())
			selectedMajors.clear();
		if (selectedSemester != null)
			selectedSemester = null;
	}
}
