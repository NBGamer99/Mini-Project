package me.ynabouzi.miniproject.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "students", schema = "MINI_PROJET")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String studentCode;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<CourseEntity> course_student;

}
