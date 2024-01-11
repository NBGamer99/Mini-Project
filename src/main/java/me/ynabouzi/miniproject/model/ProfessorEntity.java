package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Setter
@Table(name = "professors", schema = "MINI_PROJET")
public class ProfessorEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String speciality;
	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String code;


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserEntity user_professor;

	@OneToMany(mappedBy = "professor", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<CourseItemEntity> courseItems;

	public String FullNameTitle() {
		return this.firstName + " " + this.lastName;
	}

	public String getProfessorCoursesItems() {
		StringBuilder result = new StringBuilder("[");
		for (CourseItemEntity courseItem : courseItems) {
			if (result.length() > 1) {
				result.append(", ");
			}
			result.append(courseItem.getName());
		}
		result.append("]");
		return result.toString();
	}

	@Override
	public String toString() {
		return "ProfessorEntity{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", speciality='" + speciality + '\'' +
				", code='" + code + '\'' +
				'}';
	}
}
