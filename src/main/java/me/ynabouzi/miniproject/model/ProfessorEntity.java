package me.ynabouzi.miniproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "professors", schema = "MINI_PROJET")
public class ProfessorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String first_name;
	private String last_name;
	private String speciality;
	private String code;

	@OneToMany(mappedBy = "professor_item",cascade = CascadeType.ALL)
	private Set<CourseItemEntity> courseItems;

}
