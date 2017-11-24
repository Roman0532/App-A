package dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationDao {
    private static final Logger logger = LogManager.getLogger(AuthorizationDao.class.getName());
    private Connection dbConn;
    private AuthenticationDao authenticationDao;

    public AuthorizationDao(Connection dbConn, AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
        this.dbConn = dbConn;
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
            logger.debug("Выполняется поиск переданного ресурса - {} пользователя {} в БД", resource, login);
            try (ResultSet res = prsmt.executeQuery()) {
                if (res.next()) {
                    return res.getString("PATH");
                } else {
                    logger.debug("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("В методе isFindRes произошла ошибка бд ", e);
            throw new DbException("В методе isFindRes произошла ошибка бд", e);
        }
    }
}