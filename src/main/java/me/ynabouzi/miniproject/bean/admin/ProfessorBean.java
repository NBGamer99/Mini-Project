package me.ynabouzi.miniproject.bean.admin;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.ProfessorEntity;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Named(value = "ProfessorBean")
@SessionScoped
public class ProfessorBean implements Serializable {

	private static ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();
	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();

	private List<CourseItemEntity> availableCoursesItems;
	private List<CourseItemEntity> selectedCoursesItems;
	private ProfessorEntity professorEntity;

	public ProfessorBean() {
		this.setProfessorEntity(new ProfessorEntity());
		availableCoursesItems = courseItemService.getAllEntities();
	}

	public void filterAvailableCoursesItems() {
		availableCoursesItems = courseItemService.getAllEntities();
//		for (CourseItemEntity courseItem : availableCoursesItems) {
//			System.out.println(courseItem.getProfessor());
////			System.out.println(professorEntity.getId());
////			System.out.println(courseItem.getProfessor().getId());
//			if (courseItem.getProfessor() != null)
//				availableCoursesItems.remove(courseItem);
//		}
		this.setAvailableCoursesItems(availableCoursesItems);
	}

	public void changeValues() {
		filterAvailableCoursesItems();
	}

	@PostConstruct
	public void init() {
		setProfessorEntity(new ProfessorEntity());
		if (selectedCoursesItems != null && !selectedCoursesItems.isEmpty())
			selectedCoursesItems.clear();
	}
}
