package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.*;
import me.ynabouzi.miniproject.enums.Major;
import me.ynabouzi.miniproject.enums.Semester;

import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
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
	private Major major;

	@OneToMany(mappedBy = "course_student")
	private Set<StudentEntity> students;

	@OneToMany(mappedBy = "course_item")
	private Set<CourseItemEntity> courseItems;
}
