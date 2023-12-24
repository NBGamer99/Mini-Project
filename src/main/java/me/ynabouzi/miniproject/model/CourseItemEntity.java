package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "course_items", schema = "MINI_PROJET")
public class CourseItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double coefficient;

	@ManyToOne(cascade = CascadeType.ALL)
	private CourseEntity course_parent;

	@ManyToOne(cascade = CascadeType.ALL)
	private ProfessorEntity professor_item;

	@OneToMany(mappedBy = "courseItem", cascade = CascadeType.ALL)
	private Set<EvaluationEntity> evaluations;
}
