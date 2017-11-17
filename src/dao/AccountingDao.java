package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountingDao {
    private static final Logger logger = LogManager.getLogger(AccountingDao.class);
    private static Connection dbConn;

    public AccountingDao(Connection dbConn) {
        AccountingDao.dbConn = dbConn;
    }

    private static Connection getDbConn() {
        return dbConn;
    }
    /**
     * Добавление данных о пользователе успешно прошедших аккаунтинг БД
     */
    public static void addAccounting(UserData userData) throws SQLException {
        logger.debug("Пользователь " + userData.getLogin() + "успешно прошел аккаунтинг выполняется добавление в базу");

        PreparedStatement prsmt = getDbConn().prepareStatement("INSERT INTO ACCOUNTING (LOGIN,DATA_START,DATA_END,VOLUME) VALUES (?,?,?,?)");
        prsmt.setString(1, userData.getLogin());
        prsmt.setString(2, userData.getDataStart());
        prsmt.setString(3, userData.getDataEnd());
        prsmt.setString(4, userData.getVolume());
        prsmt.executeUpdate();

        prsmt.close();
    }
}

