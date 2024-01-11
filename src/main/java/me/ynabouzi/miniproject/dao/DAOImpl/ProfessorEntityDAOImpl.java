package me.ynabouzi.miniproject.dao.DAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.ynabouzi.miniproject.dao.EntityDAO;
import me.ynabouzi.miniproject.model.ProfessorEntity;
import me.ynabouzi.miniproject.util.EntityManagerHelper;

import java.util.List;

public class ProfessorEntityDAOImpl implements EntityDAO<ProfessorEntity> {

	@PersistenceContext
	private EntityManager entityManager;

	public ProfessorEntityDAOImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	@Override
	public ProfessorEntity saveEntity(ProfessorEntity professor) {
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
	public ProfessorEntity getEntityById(Long id) {
		ProfessorEntity professor = null;
		try {
			professor = entityManager.find(ProfessorEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return professor;
	}

	public ProfessorEntity getProfessorByUserID(Long id) {
		ProfessorEntity professor = null;
		try {
			professor = entityManager.createQuery("SELECT p FROM ProfessorEntity p WHERE p.user_professor.id = :id", ProfessorEntity.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return professor;
	}

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
	public List<ProfessorEntity> getAllEntities() {
		List<ProfessorEntity> professorEntities = null;
		try {
			professorEntities = entityManager.createQuery("SELECT p FROM ProfessorEntity p", ProfessorEntity.class).getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return professorEntities;
	}

	@Override
	public boolean deleteEntity(Long id) {
		ProfessorEntity professor = this.getEntityById(id);
		if (professor != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(professor);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public ProfessorEntity updateEntity(ProfessorEntity newProfessor, Long id) {
		newProfessor.setId(id);
		this.saveEntity(newProfessor);
		return newProfessor;
	}
}
