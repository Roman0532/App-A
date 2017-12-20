package config;

import exception.ExceptionProviders;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import service.DbException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionProvider implements ExceptionProviders<Connection> {
    @InjectLogger
    private
    Logger logger;
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String CONFIG_PROPERTIES = "../config.properties";

    /**
     * Подключение к БД
     */
    @Override
    public Connection get() throws service.DbException {
        Properties property = new Properties();
        try (InputStream fileInputStream = this.getClass().getResourceAsStream(CONFIG_PROPERTIES)) {
            property.load(fileInputStream);
            Class.forName(property.getProperty(DRIVER));
            dbMigration(property);
            return DriverManager.getConnection(property.getProperty(URL), System.getenv(LOGIN), System.getenv(PASSWORD));
        } catch (Exception e) {
            logger.error("Ошибка соеденения с базой данных");
            throw new DbException("Connection failed", e);
        }
    }

    private void dbMigration(Properties property) {
        logger.debug("Выполнение миграций");
        try {
            Flyway flyway = new Flyway();
            flyway.setDataSource(property.getProperty(URL), System.getenv(LOGIN), System.getenv(PASSWORD));
            flyway.migrate();
        } catch (Exception e) {
            logger.error("Не могу выполнить миграцию");
        }
    }
}

