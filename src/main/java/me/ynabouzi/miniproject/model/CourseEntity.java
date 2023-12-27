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

	@ElementCollection(targetClass = Major.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "course_major", joinColumns = @JoinColumn(name = "course_id"))
	@Column(name = "major")
	private Set<Major> majors;

	@OneToMany(mappedBy = "course_student", cascade = CascadeType.ALL)
	private Set<StudentEntity> students;

	@OneToMany(mappedBy = "course_parent", cascade = CascadeType.ALL)
	private Set<CourseItemEntity> courseItems;
}
