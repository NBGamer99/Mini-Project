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

	@ManyToOne
	private CourseEntity course_item;

	@ManyToOne
	private ProfessorEntity professor_item;

	@OneToMany(mappedBy = "courseItem")
	private Set<EvaluationEntity> evaluations;
}
