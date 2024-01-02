package me.ynabouzi.miniproject.controllers.admin.courseItems;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminListCoursesItemsController")
@RequestScoped
public class ListCoursesItemsController implements Serializable {

	private List<CourseItemEntity> courseItemEntities;

	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();


	@PostConstruct
	public void init() {
		courseItemEntities = fetchCoursesItemsFromBackend();
	}

	public List<CourseItemEntity> fetchCoursesItemsFromBackend() {
		courseItemEntities = courseItemService.getAllCourseItems();
		return courseItemEntities;
	}

	public void deleteCourseItem(Long id) {
		if (courseItemService.deleteCourseItem(id)) {
			this.init();
		}
	}
}
