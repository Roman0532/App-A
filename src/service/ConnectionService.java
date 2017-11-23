package service;

import dao.AuthorizationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {
    private static final Logger logger = LogManager.getLogger(AuthorizationDao.class.getName());

    /**
     * Подключение к БД
     */
    public Connection getDbConnection() {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("src/resources/config.properties"));
        } catch (IOException e) {
            logger.error("Файл не найден ", e);
        }

        try {
            Class.forName(property.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            logger.error("Ошибка драйвера ", e);
        }

        try {
            return DriverManager.getConnection(property.getProperty("url"), System.getenv("login"), System.getenv("password"));
        } catch (SQLException e) {
            logger.error("Подключение не удалось ", e);
           // throw new DbException("Подключение не удалось",e);
            return null;
        }
    }

    public void dbMigration() {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("src/resources/config.properties"));
        } catch (IOException e) {
            logger.error("Файл не найден ", e);
          //  throw new IOException("Файл не найден",e);
        }

        Flyway flyway = new Flyway();

        flyway.setDataSource(property.getProperty("url"), "sa", "");

        flyway.migrate();
    }
}
