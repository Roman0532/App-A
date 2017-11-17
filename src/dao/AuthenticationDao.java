package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDao {
    private static final Logger logger = LogManager.getLogger(AuthenticationDao.class);
    private static Connection dbConn;

    public AuthenticationDao(Connection dbConn) {
        AuthenticationDao.dbConn = dbConn;
    }

    private static Connection getDbConn() {
        return dbConn;
    }

    /**
     * Поиск ID в БД
     */
    static int findId(String login) throws NoSuchAlgorithmException, SQLException {
        logger.debug("Выполняется поиск id пользователя - " + login + " в базе");

        PreparedStatement pstmt = null;
        try {
            pstmt = getDbConn().prepareStatement("SELECT ID FROM USER WHERE LOGIN = ?");
            pstmt.setString(1, login);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return res.getInt("ID");
            }
        } catch (SQLException e) {
            logger.debug("Не удалось выполнить запрос " + e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return 0;
    }

    /**
     * Поиск LOGIN в БД
     */
    public static String findLogin(String login) throws NoSuchAlgorithmException, SQLException {
        logger.debug("Выполняется поиск перданного логина - " + login + " в базе");

        PreparedStatement pstmt = null;
        try {
            pstmt = getDbConn().prepareStatement("SELECT LOGIN FROM USER WHERE LOGIN = ?");
            pstmt.setString(1, login);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return res.getString("LOGIN");
            }
        } catch (SQLException e) {
            logger.error("Запрос не может быть выполнен " + e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return null;
    }

    /**
     * Поиск PASSWORD в БД
     */
    public static String findPassword(String login) throws NoSuchAlgorithmException, SQLException {
        logger.debug("Выполняется поиск пароля пользователя- " + login + " в базе");

        PreparedStatement prsmt = null;
        try {
            prsmt = getDbConn().prepareStatement("SELECT PASSWORD FROM USER WHERE LOGIN = ?");
            prsmt.setString(1, login);
            ResultSet res = prsmt.executeQuery();
            if (res.next()) {
                return res.getString("PASSWORD");
            }
        } catch (SQLException e) {
            logger.error("Запрос не может быть выполнен " + e);
        } finally {
            if (prsmt != null) {
                prsmt.close();
            }
        }
        return null;
    }

    /**
     * Поиск SALT в БД
     */
    public static String findSalt(String login) throws NoSuchAlgorithmException, SQLException {
        logger.debug("Выполняется поиск соли пользователя - " + login + " в базе");

        PreparedStatement prsmt = null;
        try {
            prsmt = getDbConn().prepareStatement("SELECT SALT FROM USER WHERE LOGIN = ?");
            prsmt.setString(1, login);
            ResultSet res = prsmt.executeQuery();
            if (res.next()) {
                return res.getString("SALT");
            }
        } catch (SQLException e) {
            logger.error("Запрос не может быть выполнен " + e);
        } finally {
            if (prsmt != null) {
                prsmt.close();
            }
        }
        return null;
    }
}
