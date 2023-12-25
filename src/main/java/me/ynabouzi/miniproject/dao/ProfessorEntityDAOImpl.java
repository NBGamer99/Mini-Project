package me.ynabouzi.miniproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.model.ProfessorEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class ProfessorEntityDAOImpl implements ProfessorEntityDAO{

	@PersistenceContext
	private EntityManager entityManager;

	public ProfessorEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public ProfessorEntity saveProfessor(ProfessorEntity professor) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(professor);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.getTransaction().rollback();
		}
		return professor;
	}

	@Override
	public ProfessorEntity getProfessorById(Long id) {
		ProfessorEntity professor = null;
		try {
			professor = entityManager.find(ProfessorEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return professor;
	}

	@Override
	public ProfessorEntity getProfessorByLastName(String name) {
		ProfessorEntity professor = null;
		try {
			professor = entityManager.createQuery("SELECT p FROM ProfessorEntity p WHERE p.lastName = :name", ProfessorEntity.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return professor;
	}

	@Override
	public List<ProfessorEntity> getAllProfessors() {
		List<ProfessorEntity> professorEntities = null;
		try {
			professorEntities = entityManager.createQuery("SELECT p FROM ProfessorEntity p", ProfessorEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return professorEntities;
	}

	@Override
	public boolean deleteProfessor(Long id) {
		ProfessorEntity professor = this.getProfessorById(id);
		if(professor != null) {
			entityManager.remove(professor);
			return true;
		}
		return false;
	}

	@Override
	public ProfessorEntity updateProfessor(ProfessorEntity newProfessor, Long id) {
		newProfessor.setId(id);
		this.saveProfessor(newProfessor);
		return newProfessor;
	}
}
