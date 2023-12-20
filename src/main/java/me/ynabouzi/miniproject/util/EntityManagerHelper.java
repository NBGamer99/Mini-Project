package me.ynabouzi.miniproject.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import me.ynabouzi.miniproject.persistence.MyPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class EntityManagerHelper {
	private static final EntityManagerFactory emf = new HibernatePersistenceProvider()
			.createContainerEntityManagerFactory(new MyPersistenceUnitInfo(), new HashMap<>());;
	private static EntityManager em;
	public static EntityManager getEntityManager() {
		if (em == null) {
			em = emf.createEntityManager();
		}
		else if (!em.isOpen()) {
			em = emf.createEntityManager();
		}
		return em;
	}
}
