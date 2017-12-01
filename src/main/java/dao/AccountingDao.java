package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountingDao {
    private static final Logger logger = LogManager.getLogger(AccountingDao.class.getName());
    private Connection dbConn;

    public AccountingDao(Connection dbConn) {
        this.dbConn = dbConn;
    }

    /**
     * Добавление данных о пользователе успешно прошедших аккаунтинг БД
     */
    public void addAccounting(String login, String dateStart, String dateEnd, String volume) throws DbException {
        logger.debug("Пользователь {} успешно прошел аккаунтинг выполняется добавление в базу", login);
        try (PreparedStatement prsmt = dbConn.prepareStatement
                ("INSERT INTO ACCOUNTING (LOGIN,DATA_START,DATA_END,VOLUME) VALUES (?,?,?,?)")) {
            prsmt.setString(1, login);
            prsmt.setString(2, dateStart);
            prsmt.setString(3, dateEnd);
            prsmt.setString(4, volume);
            prsmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("В методе addAccounting произошла ошибка бд", e);
            throw new DbException("Произошла ошибка бд", e);
        }

    }
}