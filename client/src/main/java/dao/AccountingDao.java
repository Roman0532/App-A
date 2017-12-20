package dao;

import lombok.extern.log4j.Log4j2;
import service.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    /**
     * Поиск всех действий
     */
    public ArrayList<String> findAllActivity() throws DbException {

        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT * FROM ACCOUNTING")) {
            ArrayList<String> activity = new ArrayList<String>();
            log.debug("Выполняется поиск всех действий");
            try (ResultSet res = prsmt.executeQuery()) {
                while (res.next()) {
                    activity.add(res.getString("ID"));
                    activity.add(res.getString("LOGIN"));
                    activity.add(res.getString("ROLE"));
                    activity.add(res.getString("DATA_START"));
                    activity.add(res.getString("DATA_END"));
                    activity.add(res.getString("VOLUME"));
                    activity.add(res.getString("AUTHORITY_ID"));
                }
                return activity;
            }
        } catch (SQLException e) {
            log.error("В методе findAllActivity произошла ошибка бд ", e);
            throw new DbException("В методе findAllActivity произошла ошибка бд", e);
        }
    }

    /**
     * Поиск дествия по id
     */
    public ArrayList<String> findActivityById(int id) throws DbException {
        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT * FROM ACCOUNTING WHERE ID = ?")) {
            prsmt.setInt(1, id);
            ArrayList<String> activity = new ArrayList<String>();
            log.debug("Выполняется поиск действия по id");
            try (ResultSet res = prsmt.executeQuery()) {
                while (res.next()) {
                    activity.add(res.getString("ID"));
                    activity.add(res.getString("LOGIN"));
                    activity.add(res.getString("ROLE"));
                    activity.add(res.getString("DATA_START"));
                    activity.add(res.getString("DATA_END"));
                    activity.add(res.getString("VOLUME"));
                    activity.add(res.getString("AUTHORITY_ID"));
                }
                return activity;
            }
        } catch (SQLException e) {
            log.error("В методе findActivityById произошла ошибка бд ", e);
            throw new DbException("В методе findActivityById произошла ошибка бд", e);
        }
    }

    /**
     * Поиск роли по authorityId
     */
    public String findRoleByAuthorityId(int authorityId) throws DbException {
        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT ROLE FROM ACCOUNTING WHERE AUTHORITY_ID = ?")) {
            prsmt.setInt(1, authorityId);
            log.debug("Выполняется поиск роли");
            try (ResultSet res = prsmt.executeQuery()) {
                if (res.next()) {
                    return res.getString("ROLE");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            log.error("В методе findRoleByAuthorityId произошла ошибка бд ", e);
            throw new DbException("В методе findRoleByAuthorityId произошла ошибка бд", e);
        }

    }

    /**
     * Поиск дествия по роли
     */
    public ArrayList<String> findActivityByRole(String role) throws DbException {
        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT * FROM ACCOUNTING WHERE ROLE = ?")) {
            prsmt.setString(1, role);
            ArrayList<String> activity = new ArrayList<String>();
            log.debug("Выполняется поиск в userres");
            try (ResultSet res = prsmt.executeQuery()) {
                while (res.next()) {
                    activity.add(res.getString("ID"));
                    activity.add(res.getString("LOGIN"));
                    activity.add(res.getString("ROLE"));
                    activity.add(res.getString("DATA_START"));
                    activity.add(res.getString("DATA_END"));
                    activity.add(res.getString("VOLUME"));
                    activity.add(res.getString("AUTHORITY_ID"));
                }
                return activity;
            }
        } catch (SQLException e) {
            log.error("В методе findActionByRole произошла ошибка бд ", e);
            throw new DbException("В методе findActionByRole произошла ошибка бд", e);
        }
    }
}