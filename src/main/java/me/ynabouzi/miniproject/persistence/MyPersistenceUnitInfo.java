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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyPersistenceUnitInfo implements PersistenceUnitInfo {

	private static List<String> MANAGED_CLASS_NAMES;
	private static String JDBC_URL;
	private static String JDBC_USER;
	private static String JDBC_DRIVER;
	private static String JDBC_PASSWORD;
	private static String PERSISTENCE_UNIT_NAME;
	private static String HIBERNATE_HBM2DDL_AUTO;
	private static String HIBERNATE_SHOW_SQL;
	private static String HIBERNATE_FORMAT_SQL;

	public static class Builder{
		private List<String> managedClassNames;
		private String jdbcUrl;
		private String jdbcUser;
		private String jdbcDriver;
		private String jdbcPassword;
		private String persistenceUnitName;
		private String hibernateHbm2ddlAuto;
		private String hibernateShowSql;
		private String hibernateFormatSql;

		public Builder jdbcUrl(String jdbcUrl) {
			this.jdbcUrl = jdbcUrl;
			return this;
		}

		public Builder jdbcUser(String jdbcUser) {
			this.jdbcUser = jdbcUser;
			return this;
		}

		public Builder jdbcDriver(String jdbcDriver) {
			this.jdbcDriver = jdbcDriver;
			return this;
		}

		public Builder jdbcPassword(String jdbcPassword) {
			this.jdbcPassword = jdbcPassword;
			return this;
		}

		public Builder persistenceUnitName(String persistenceUnitName) {
			this.persistenceUnitName = persistenceUnitName;
			return this;
		}

		public Builder hibernateHbm2ddlAuto(String hibernateHbm2ddlAuto) {
			this.hibernateHbm2ddlAuto = hibernateHbm2ddlAuto;
			return this;
		}

		public Builder hibernateShowSql(String hibernateShowSql) {
			this.hibernateShowSql = hibernateShowSql;
			return this;
		}

		public Builder hibernateFormatSql(String hibernateFormatSql) {
			this.hibernateFormatSql = hibernateFormatSql;
			return this;
		}

		public Builder setManagedClassNames(List<String> managedClassName) {
			this.managedClassNames = managedClassName;
			return this;
		}

		public MyPersistenceUnitInfo build() {
			MyPersistenceUnitInfo myPersistenceUnitInfo = new MyPersistenceUnitInfo();
			JDBC_URL = this.jdbcUrl;
			JDBC_USER = this.jdbcUser;
			JDBC_DRIVER = this.jdbcDriver;
			JDBC_PASSWORD = this.jdbcPassword;
			PERSISTENCE_UNIT_NAME = this.persistenceUnitName;
			HIBERNATE_HBM2DDL_AUTO = this.hibernateHbm2ddlAuto;
			HIBERNATE_SHOW_SQL = this.hibernateShowSql;
			HIBERNATE_FORMAT_SQL = this.hibernateFormatSql;
			MANAGED_CLASS_NAMES = this.managedClassNames;
			return myPersistenceUnitInfo;
		}
	}


	@Override
	public String getPersistenceUnitName() {
		return PERSISTENCE_UNIT_NAME;
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
		return MANAGED_CLASS_NAMES;
	}

	@Override
	public DataSource getJtaDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(JDBC_URL+"?createDatabaseIfNotExist=true");
		config.setUsername(JDBC_USER);
		config.setPassword(JDBC_PASSWORD);
		config.setDriverClassName(JDBC_DRIVER);

		return new HikariDataSource(config);
	}

	@Override
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		properties.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		properties.setProperty("hibernate.format_sql", HIBERNATE_FORMAT_SQL);
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
