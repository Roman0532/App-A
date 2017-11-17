package service;

import dao.AuthorizationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
    private static final Logger logger = LogManager.getLogger(AuthorizationDao.class);
    /**
     * Подключение к БД
     */
    public static java.sql.Connection getDbConnection() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        try {
            return DriverManager.getConnection("jdbc:h2:./src/resources/db/migration/aaa", "sa", "");
        } catch (SQLException e) {
            logger.error("Подключение не удалось " + e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
