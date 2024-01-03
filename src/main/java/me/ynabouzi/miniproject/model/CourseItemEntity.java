package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course_items", schema = "MINI_PROJET")
public class CourseItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double coefficient;
	private boolean validated;

	@ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private CourseEntity course;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "professor_id")
	private ProfessorEntity professor;

	@OneToMany(mappedBy = "courseItem", cascade = CascadeType.DETACH , fetch = FetchType.LAZY)
	private List<EvaluationEntity> evaluations;

	@Override
	public String toString() {
		return "CourseItemEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", coefficient=" + coefficient +
//				", course_parent=" + course +
//				", professor_item=" + professor +
//				", evaluations=" + evaluations +
				'}';
	}
}
