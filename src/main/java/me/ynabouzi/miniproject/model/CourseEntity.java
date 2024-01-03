package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.*;
import me.ynabouzi.miniproject.enums.Major;
import me.ynabouzi.miniproject.enums.Semester;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "courses", schema = "MINI_PROJET")
public class CourseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String name;

	@Enumerated(EnumType.STRING)
	private Semester semester;

	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Major.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "course_major", joinColumns = @JoinColumn(name = "course_id"))
	private List<Major> majors;

	@ManyToMany(mappedBy = "course_student", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<StudentEntity> students;

	@OneToMany(mappedBy = "course", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<CourseItemEntity> courseItems;

	public String getCoursesItems() {
		StringBuilder result = new StringBuilder("[");
		if (courseItems == null || courseItems.isEmpty()) {
			return result.append("]").toString();
		}
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
		return "CourseEntity{" +
				"id=" + id +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", semester=" + semester +
				", majors=" + majors +
//				", students=" + students +
//				", courseItems=" + courseItems +
				'}';
	}
}
