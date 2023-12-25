package me.ynabouzi.miniproject.config;

import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import me.ynabouzi.miniproject.util.EntityManagerHelper;
import org.hibernate.jpa.HibernatePersistenceProvider;
import me.ynabouzi.miniproject.persistence.MyPersistenceUnitInfo;
import org.slf4j.Logger;


@WebServlet(name = "initServlet", value = "/", loadOnStartup = 1)
public class InitConfig extends HttpServlet
{
	static final Logger logger = org.slf4j.LoggerFactory.getLogger(InitConfig.class);

	private EntityManager em;
	public void init() {
		logger.info("############# Database initialized #############");
		em = EntityManagerHelper.getEntityManager();
	}

	public void destroy() {
		em.close();
	}
}
