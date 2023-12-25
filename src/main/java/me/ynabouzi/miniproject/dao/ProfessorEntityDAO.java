package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.util.List;

public interface ProfessorEntityDAO {

	ProfessorEntity getProfessorById(Long id);

	ProfessorEntity getProfessorByLastName(String name);

	List<ProfessorEntity> getAllProfessors();

	ProfessorEntity saveProfessor(ProfessorEntity professor);

	boolean deleteProfessor(Long id);

	ProfessorEntity updateProfessor(ProfessorEntity professor, Long id);
}
