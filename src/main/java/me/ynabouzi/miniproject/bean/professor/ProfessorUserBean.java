package me.ynabouzi.miniproject.bean.professor;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.dao.DAOImpl.CourseItemEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.ProfessorEntityDAOImpl;
import me.ynabouzi.miniproject.dao.DAOImpl.UserEntityDAOImpl;
import me.ynabouzi.miniproject.enums.Users;
import me.ynabouzi.miniproject.model.CourseItemEntity;
import me.ynabouzi.miniproject.model.StudentEntity;
import me.ynabouzi.miniproject.model.UserEntity;
import me.ynabouzi.miniproject.factory.ServiceDAOFactory;

import java.io.Serializable;
import java.util.List;

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

	private double courseItemAverageGrade;

	private double courseAverageGrade;

	@Inject
	private UserBean userBean;

	public void init() {
		System.out.println("init professor user bean " + userBean.getRole());
		setCourseAverageGrade(0);
		setCourseItemAverageGrade(0);
		if (userBean.isLoggedIn() && userBean.getRole().equals(Users.PROFESSOR)) {
			this.setUser(userService.getUserByUsername(userBean.getUsername()));
			this.setProfessorCourseItemsList(professorService.getProfessorByUserID(this.user.getId()).getCourseItems());
		}
	}

	public void selectCourseItem(CourseItemEntity courseItem) {
		setSelectedCourseItem(courseItem);
		if (courseItem != null) {
			System.out.println("selected item done");
			setSelectedCourseItemStudents(courseItem.getCourse().getStudents());
		}
	}


}
