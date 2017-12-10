package dao;

import lombok.extern.log4j.Log4j2;
import service.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Log4j2
public class AccountingDao {
    private Connection dbConn;

    public AccountingDao(Connection dbConn) {
        this.dbConn = dbConn;
    }

    /**
     * Добавление данных о пользователе успешно прошедших аккаунтинг БД
     */
    public void addAccounting(String login, String dateStart, String dateEnd, String volume) throws DbException {
        log.debug("Пользователь {} успешно прошел аккаунтинг выполняется добавление в базу", login);
        try (PreparedStatement prsmt = dbConn.prepareStatement
                ("INSERT INTO ACCOUNTING (LOGIN,DATA_START,DATA_END,VOLUME) VALUES (?,?,?,?)")) {
            prsmt.setString(1, login);
            prsmt.setString(2, dateStart);
            prsmt.setString(3, dateEnd);
            prsmt.setString(4, volume);
            prsmt.executeUpdate();
        } catch (SQLException e) {
            log.error("В методе addAccounting произошла ошибка бд", e);
            throw new DbException("Произошла ошибка бд", e);
        }
    }
}