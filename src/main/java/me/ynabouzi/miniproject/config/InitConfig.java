package me.ynabouzi.miniproject.config;

import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import me.ynabouzi.miniproject.util.EntityManagerHelper;
import org.slf4j.Logger;

@WebServlet(name = "initServlet", loadOnStartup = 1)
public class InitConfig extends HttpServlet {
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
