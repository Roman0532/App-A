package service;

import dao.AuthorizationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {
    private static final Logger logger = LogManager.getLogger(AuthorizationDao.class.getName());
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    public static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String CONFIG_PROPERTIES = "/config.properties";

    /**
     * Подключение к БД
     */
    public Connection getDbConnection() throws DbException {
        Properties property = new Properties();
        try (InputStream fileInputStream = this.getClass().getResourceAsStream(CONFIG_PROPERTIES)) {
            property.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Файл не найден ", e);
        }

        try {
            Class.forName(property.getProperty(DRIVER));
        } catch (ClassNotFoundException e) {
            logger.error("Ошибка драйвера ", e);
        }

        try {
            return DriverManager.getConnection(property.getProperty(URL), System.getenv(LOGIN), System.getenv(PASSWORD));
        } catch (SQLException e) {
            logger.error("Подключение не удалось ", e);
            throw new DbException("Подключение не удалось", e);
        }
    }

    public void dbMigration() throws DbException {
        Properties property = new Properties();
        try (InputStream fileInputStream = this.getClass().getResourceAsStream(CONFIG_PROPERTIES)) {
            property.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Файл не найден ", e);
        }

        try {
            Flyway flyway = new Flyway();

            flyway.setDataSource(property.getProperty(URL), System.getenv(LOGIN), System.getenv(PASSWORD));

            flyway.migrate();
        } catch (Exception e) {
            throw new DbException("Не могу мигрировать", e);
        }
    }
}
