package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.ProfessorEntity;

import java.util.List;

public class ProfessorEntityDAOImpl implements ProfessorEntityDAO{

	@PersistenceContext
	private EntityManager entityManager;

	public ProfessorEntityDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ProfessorEntity saveProfessor(ProfessorEntity professor) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(professor);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return professor;
	}

	@Override
	public ProfessorEntity getProfessorById(Long id) {
		return entityManager.find(ProfessorEntity.class, id);
	}

	@Override
	public ProfessorEntity getProfessorByName(String name) {
		return entityManager.find(ProfessorEntity.class, name);
	}

	@Override
	public List<ProfessorEntity> getAllProfessors() {
		return entityManager.createQuery("SELECT p FROM ProfessorEntity p", ProfessorEntity.class).getResultList();
	}

	@Override
	public void deleteProfessor(Long id) {
		ProfessorEntity professor = entityManager.find(ProfessorEntity.class, id);
		if(professor != null) {
			entityManager.remove(professor);
		}
	}

	@Override
	public void updateProfessor(ProfessorEntity newProfessor, Long id) {
		newProfessor.setId(id);
		this.saveProfessor(newProfessor);
	}
}
