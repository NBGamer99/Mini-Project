package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;

import java.util.List;


@Getter
@Setter
@ToString
@Entity
@Table(name = "students", schema = "MINI_PROJET")
public class StudentEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String code;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<CourseEntity> course_student;

	public String FullName()
	{
		return this.firstName + " " + this.lastName;
	}


}
