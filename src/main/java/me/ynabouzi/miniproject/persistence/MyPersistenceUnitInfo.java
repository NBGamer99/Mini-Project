package me.ynabouzi.miniproject.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class MyPersistenceUnitInfo implements PersistenceUnitInfo {
	@Override
	public String getPersistenceUnitName() {
		return "default";
	}

	@Override
	public String getPersistenceProviderClassName() {
		return "org.hibernate.jpa.HibernatePersistenceProvider";
	}

	@Override
	public PersistenceUnitTransactionType getTransactionType() {
		return PersistenceUnitTransactionType.RESOURCE_LOCAL;
	}

	@Override
	public List<String> getManagedClassNames() {
		return List.of(
				"me.ynabouzi.miniproject.model.StudentEntity",
				"me.ynabouzi.miniproject.model.CourseEntity",
				"me.ynabouzi.miniproject.model.CourseItemEntity",
				"me.ynabouzi.miniproject.model.EvaluationEntity",
				"me.ynabouzi.miniproject.model.ProfessorEntity",
				"me.ynabouzi.miniproject.model.UserEntity"
		);

	}
	@Override
	public DataSource getJtaDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/MINI_PROJET?createDatabaseIfNotExist=true");
		config.setUsername("root");
		config.setPassword("1234");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");

		return new HikariDataSource(config);
	}

	@Override
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}

	@Override
	public DataSource getNonJtaDataSource() {
		return null;
	}

	@Override
	public List<String> getMappingFileNames() {
		return null;
	}

	@Override
	public List<URL> getJarFileUrls() {
		return null;
	}

	@Override
	public URL getPersistenceUnitRootUrl() {
		return null;
	}


	@Override
	public boolean excludeUnlistedClasses() {
		return false;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {
		return null;
	}

	@Override
	public ValidationMode getValidationMode() {
		return null;
	}

	@Override
	public String getPersistenceXMLSchemaVersion() {
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		return null;
	}

	@Override
	public void addTransformer(ClassTransformer classTransformer) {
	}
	@Override
	public ClassLoader getNewTempClassLoader() {
		return null;
	}
}
