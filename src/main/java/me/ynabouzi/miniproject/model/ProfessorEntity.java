package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "professors", schema = "MINI_PROJET")
public class ProfessorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String speciality;
	private String code;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private UserEntity user_professor;

	@OneToMany(mappedBy = "professor",cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
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
//				", user_professor=" + user_professor +
//				", courseItems=" + courseItems +
				'}';
	}

}
