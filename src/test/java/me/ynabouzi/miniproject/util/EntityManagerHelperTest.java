package me.ynabouzi.miniproject.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EntityManagerHelperTest {

	@Test
	void testGetEntityManager() {
		EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
		EntityManager entityManager = Mockito.mock(EntityManager.class);

		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);

		EntityManager result = EntityManagerHelper.getEntityManager();

		assertNotNull(result);

	}
}