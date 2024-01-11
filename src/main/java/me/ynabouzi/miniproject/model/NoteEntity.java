package me.ynabouzi.miniproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notes", schema = "MINI_PROJET")
public class NoteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer noteCC;
	private Integer noteTP;
	private Integer noteProject;
	private Integer notePresentation;

	private boolean absentCC;
	private boolean absentTP;
	private boolean absentProject;
	private boolean absentPresentation;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private StudentEntity student;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private CourseItemEntity courseItem;

}
