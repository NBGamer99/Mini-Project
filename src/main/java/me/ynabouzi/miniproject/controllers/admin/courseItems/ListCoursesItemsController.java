package me.ynabouzi.miniproject.controllers.admin.courseItems;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.model.CourseItemEntity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminListCoursesItemsController")
@RequestScoped
public class ListCoursesItemsController implements Serializable {

	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();
	private List<CourseItemEntity> courseItemEntities;

	@PostConstruct
	public void init() {
		fetchCoursesItemsFromBackend();
	}

	public void fetchCoursesItemsFromBackend() {
		courseItemEntities = courseItemService.getAllEntities();
	}

	public void deleteCourseItem(Long id) {
		CourseItemEntity courseItem = courseItemService.getEntityById(id);
		unsetAttributes(courseItem);
		if (courseItemService.deleteEntity(id)) {
			fetchCoursesItemsFromBackend();
		}
	}

	private void unsetAttributes(CourseItemEntity courseItem) {
		if (courseItem.getProfessor() != null)
			courseItem.getProfessor().getCourseItems().remove(courseItem);
	}
}
