package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.Data;
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

	private int noteCC;
	private int noteTP;
	private int noteProject;
	private int notePresentation;

	private boolean absentCC;
	private boolean absentTP;
	private boolean absentProject;
	private boolean absentPresentation;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private StudentEntity student;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private CourseItemEntity courseItem;

}
