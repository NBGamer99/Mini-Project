package me.ynabouzi.miniproject.controllers.admin.courseItems;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.CourseItemBean;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import me.ynabouzi.miniproject.dao.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
@Named(value = "AdminAddCoursesItemsController")
@RequestScoped
public class AddCourseItemsController implements Serializable {

	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();
	private static ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();
	private static CourseEntityDAOImpl courseService = ServiceDAOFactory.getCourseService();

	@Inject
	private CourseItemBean courseItemBean;

	public String addCourseItem() {
		ProfessorEntity professorEntity = professorService.getProfessorById(courseItemBean.getSelectedProfessor());
		CourseEntity courseEntity = courseService.getCourseById(courseItemBean.getSelectedCourse());
		CourseItemEntity courseItem = courseItemBean.getCourseItemEntity();

		courseItem.setProfessor(professorEntity);
		courseItem.setCourse(courseEntity);

		List<CourseItemEntity> listOfAllCourseItems = courseEntity.getCourseItems();
		listOfAllCourseItems.add(courseItem);

		courseEntity.setCourseItems(listOfAllCourseItems);

		courseItemService.saveCourseItem(courseItem);

		courseItemBean.init();
		return "/admin/course-items/course-item-list.xhtml?faces-redirect=true";
	}
}
