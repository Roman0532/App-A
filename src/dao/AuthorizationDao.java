package dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AuthorizationService;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationDao {
    private static Connection dbConn;
    private static final Logger logger = LogManager.getLogger(AuthorizationDao.class);

    public AuthorizationDao(Connection dbConn) {
        AuthorizationDao.dbConn = dbConn;
    }

    private static Connection getDbConn() {
        return dbConn;
    }

    /**
     * Поиск PATH в БД
     */
    public static boolean isFindRes(String login, String resource, String role) throws NoSuchAlgorithmException, SQLException {
        PreparedStatement prsmt = null;
        try {
            prsmt = getDbConn().prepareStatement("SELECT PATH FROM USER_RES WHERE USER_ID = ? AND ROLE =?");
            prsmt.setInt(1, AuthenticationDao.findId(login));
            prsmt.setString(2, role);

            logger.debug("Выполняется поиск переданного ресурса - " + resource + " пользователя " + login + " в базе");

            ResultSet res = prsmt.executeQuery();
            while (res.next()) {
                if (AuthorizationService.isCheckChildPaths(res.getString("PATH"), resource)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Запрос не может быть выполнен " + e);
        } finally {
            if (prsmt != null) {
                prsmt.close();
            }
        }
        return false;
    }
}