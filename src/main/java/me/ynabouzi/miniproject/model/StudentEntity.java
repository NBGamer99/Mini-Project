package me.ynabouzi.miniproject.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "students", schema = "MINI_PROJET")
public class StudentEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;
	private String studentCode;

	@ManyToOne(cascade = CascadeType.ALL)
	private  CourseEntity course_student;

}
