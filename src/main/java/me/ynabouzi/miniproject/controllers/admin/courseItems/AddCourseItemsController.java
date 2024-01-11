package me.ynabouzi.miniproject.controllers.admin.courseItems;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.admin.CourseItemBean;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;
import me.ynabouzi.miniproject.model.CourseEntity;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.io.Serializable;
import java.util.ArrayList;
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
		ProfessorEntity professorEntity = professorService.getEntityById(courseItemBean.getSelectedProfessor());
		CourseEntity courseEntity = courseService.getEntityById(courseItemBean.getSelectedCourse());
		CourseItemEntity courseItem = courseItemBean.getCourseItemEntity();

		courseItem.setProfessor(professorEntity);
		courseItem.setCourse(courseEntity);

		List<CourseItemEntity> listOfAllCourseItems = new ArrayList<>();
		listOfAllCourseItems.add(courseItem);
		if (courseEntity.getCourseItems() != null)
			listOfAllCourseItems.addAll(courseEntity.getCourseItems());

		courseEntity.setCourseItems(listOfAllCourseItems);

		courseItemService.saveEntity(courseItem);

		courseItemBean.init();
		return "/admin/course-items/course-item-list.xhtml?faces-redirect=true";
	}


}
