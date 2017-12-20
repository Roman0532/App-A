package dao;


import lombok.extern.log4j.Log4j2;
import service.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Log4j2
public class AuthorizationDao {
    private Connection dbConn;
    private AuthenticationDao authenticationDao;

    public AuthorizationDao(Connection dbConn, AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
        this.dbConn = dbConn;
    }

    /**
     * Поиск всех ролей
     */
    public ArrayList<String> findAllRoles() throws DbException {
        ArrayList<String> roles = new ArrayList<>();
        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT DISTINCT ROLE FROM USER_RES")) {
            log.debug("Выполняется поиск ролей");
            try (ResultSet res = prsmt.executeQuery()) {
                while (res.next()) {
                    roles.add(res.getString("ROLE"));
                }
                return roles;
            }
        } catch (SQLException e) {
            log.error("В методе findAllRoles произошла ошибка бд ", e);
            throw new DbException("В методе findAllRoles произошла ошибка бд", e);
        }
    }

    /**
     * Поиск роли по id
     */
    public String findRoleById(int id) throws DbException {
        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT ROLE FROM USER_RES WHERE ID = ?")) {
            prsmt.setInt(1, id);
            log.debug("Выполняется поиск роли");
            try (ResultSet res = prsmt.executeQuery()) {
                if (res.next()) {
                    return res.getString("ROLE");
                } else {
                    log.debug("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                    return null;
                }
            }
        } catch (SQLException e) {
            log.error("В методе findRoleById произошла ошибка бд ", e);
            throw new DbException("В методе findRoleById произошла ошибка бд", e);
        }
    }

    /**
     * Поиск всех ролей пользователя по userId
     */
    public ArrayList<String> findUserRole(int userId) throws DbException {
        ArrayList<String> userRoles = new ArrayList<>();
        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT DISTINCT ROLE FROM USER_RES WHERE USER_ID = ?")) {
            prsmt.setInt(1, userId);
            log.debug("Выполняется поиск роли");
            try (ResultSet res = prsmt.executeQuery()) {
                while (res.next()) {
                    userRoles.add(res.getString("ROLE"));
                }
                return userRoles;
            }
        } catch (SQLException e) {
            log.error("В методе findUserRole произошла ошибка бд ", e);
            throw new DbException("В методе findUserRole произошла ошибка бд", e);
        }
    }

    /**
     * Поиск PATH в БД
     */
    public String isFindRes(String login, String resource, String role) throws DbException {
        try (PreparedStatement prsmt = dbConn.prepareStatement
                ("SELECT PATH FROM USER_RES WHERE USER_ID = ? AND ROLE = ?  AND PATH||'.'= LEFT(?||'.',length(PATH)+1)")) {
            prsmt.setInt(1, authenticationDao.findId(login));
            prsmt.setString(2, role);
            prsmt.setString(3, resource);
            log.debug("Выполняется поиск переданного ресурса - {} пользователя {} в БД", resource, login);
            try (ResultSet res = prsmt.executeQuery()) {
                if (res.next()) {
                    return res.getString("PATH");
                } else {
                    log.debug("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                    return null;
                }
            }
        } catch (SQLException e) {
            log.error("В методе isFindRes произошла ошибка бд ", e);
            throw new DbException("В методе isFindRes произошла ошибка бд", e);
        }
    }
}