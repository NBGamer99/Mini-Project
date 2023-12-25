package me.ynabouzi.miniproject.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import me.ynabouzi.miniproject.persistence.MyPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;

public class EntityManagerHelper {

	private static final MyPersistenceUnitInfo persistenceUnitInfo = new MyPersistenceUnitInfo.Builder()
			.jdbcUrl("jdbc:mysql://localhost:3306/MINI_PROJET")
			.jdbcUser("root")
			.jdbcDriver("com.mysql.cj.jdbc.Driver")
			.jdbcPassword("1234")
			.persistenceUnitName("default")
			.hibernateFormatSql("true")
			.hibernateHbm2ddlAuto("update")
			.hibernateShowSql("true")
			.setManagedClassNames(List.of("me.ynabouzi.miniproject.model.StudentEntity",
					"me.ynabouzi.miniproject.model.CourseEntity",
					"me.ynabouzi.miniproject.model.CourseItemEntity",
					"me.ynabouzi.miniproject.model.EvaluationEntity",
					"me.ynabouzi.miniproject.model.ProfessorEntity",
					"me.ynabouzi.miniproject.model.UserEntity"))
			.build();

	private static EntityManagerFactory emf = new HibernatePersistenceProvider()
			.createContainerEntityManagerFactory(persistenceUnitInfo, new HashMap<>());
	private static EntityManager em;

	private EntityManagerHelper() {
	}
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
