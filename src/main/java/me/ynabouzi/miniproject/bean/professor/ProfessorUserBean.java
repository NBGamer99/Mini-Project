package me.ynabouzi.miniproject.bean.professor;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.admin.UserBean;
import me.ynabouzi.miniproject.dao.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.dao.UserEntityDAOImpl;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.ProfessorEntity;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.model.UserEntity;
import me.ynabouzi.miniproject.util.ServiceDAOFactory;
import java.util.List;


import java.io.Serializable;

@Setter
@Getter
@Data
@Named(value = "ProfessorUserBean")
@SessionScoped
public class ProfessorUserBean implements Serializable {

	private static CourseItemEntityDAOImpl courseItemService = ServiceDAOFactory.getCourseItemService();
	private static ProfessorEntityDAOImpl professorService = ServiceDAOFactory.getProfessorService();
	private static UserEntityDAOImpl userService = ServiceDAOFactory.getUserService();
	private List<CourseItemEntity> professorCourseItemsList;

	private UserEntity user;

	private CourseItemEntity selectedCourseItem;

	private List<StudentEntity> selectedCourseItemStudents;

	@Inject
	private UserBean userBean;

	public void init() {
		if (userBean.isLoggedIn())
		{
			this.setUser(userService.getUserByUsername(userBean.getUsername()));
			this.setProfessorCourseItemsList(professorService.getProfessorByUserID(this.user.getId()).getCourseItems());
		}
//		System.out.println(this.getProfessorCourseItemsList());
	}

	public void selectCourseItem(CourseItemEntity courseItem)
	{
		setSelectedCourseItem(courseItem);
		if (courseItem != null)
		{
			System.out.println("selected item done");
			setSelectedCourseItemStudents(courseItem.getCourse().getStudents());
//			System.out.println("Students : "+courseItem.getCourse().getStudents());
		}
//		setSelectedCourseItemStudents(courseItem.getCourse().getStudents());
//		setSelectedCourseItemStudents(courseItem.getCourse().getStudents());
//		return "/professor/index?faces-redirect=true";
	}



}
