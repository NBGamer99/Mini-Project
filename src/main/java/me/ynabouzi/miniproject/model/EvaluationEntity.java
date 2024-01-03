package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@ToString
@Entity
@Table(name = "evaluations", schema = "MINI_PROJET")
public class EvaluationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private Double coefficient;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private CourseItemEntity courseItem;
}
