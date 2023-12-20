package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "evaluations", schema = "MINI_PROJET")
public class EvaluationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private Double coefficient;

	@ManyToOne
	private CourseItemEntity courseItem;
}
