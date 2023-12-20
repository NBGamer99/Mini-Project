package me.ynabouzi.miniproject.config;

import java.util.HashMap;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import me.ynabouzi.miniproject.persistence.MyPersistenceUnitInfo;
import org.slf4j.Logger;


@WebServlet(name = "initServlet", value = "/", loadOnStartup = 1)
public class InitConfig extends HttpServlet {
	static final EntityManagerFactory emf = new HibernatePersistenceProvider()
			.createContainerEntityManagerFactory(new MyPersistenceUnitInfo(), new HashMap<>());

	static final EntityManager em = emf.createEntityManager();

	static final Logger logger = org.slf4j.LoggerFactory.getLogger(InitConfig.class);

	public void init() {
		logger.info("############# Database initialized #############");
	}

	public void destroy() {
		emf.close();
	}
}
