package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDao {
    private static final Logger logger = LogManager.getLogger(AuthenticationDao.class.getName());
    private Connection dbConn;

    public AuthenticationDao(Connection dbConn) {
        this.dbConn = dbConn;
    }

    /**
     * Поиск ID в БД
     */
    int findId(String login) {
        logger.debug("Выполняется поиск id пользователя - {} в базе", login);

        try (PreparedStatement pstmt = dbConn.prepareStatement("SELECT ID FROM USER WHERE LOGIN = ?")) {
            pstmt.setString(1, login);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                logger.debug("Запрос выполнен успешно");
                return res.getInt("ID");
            } else {
                logger.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                return 1;
            }
        } catch (SQLException e) {
            //throw new DbException("Произошла ошибка бд",e);
            return 1;
        }
    }

    /**
     * Поиск LOGIN в БД
     */
    public String findLogin(String login) throws DbException {
        logger.debug("Выполняется поиск перданного логина - {} в базе", login);

        try (PreparedStatement pstmt = dbConn.prepareStatement("SELECT LOGIN FROM USER WHERE LOGIN = ?")) {
            pstmt.setString(1, login);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                logger.debug("Запрос выполнен успешно");
                return res.getString("LOGIN");
            } else {
                logger.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                return null;
            }
        } catch (SQLException e) {
            logger.error("В методе findLogin произошла ошибка бд", e);
            throw new DbException("В методе findLogin произошла ошибка бд", e);

        }
    }


    /**
     * Поиск PASSWORD в БД
     */
    public String findPassword(String login) throws DbException {
        logger.debug("Выполняется поиск пароля пользователя- {} в базе", login);

        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT PASSWORD FROM USER WHERE LOGIN = ?")) {
            prsmt.setString(1, login);
            ResultSet res = prsmt.executeQuery();
            if (res.next()) {
                logger.debug("Запрос выполнен успешно.");
                return res.getString("PASSWORD");
            } else {
                logger.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                return null;
            }
        } catch (SQLException e) {
            logger.error(" В методе findPassword произошла ошибка бд", e);
            throw new DbException("В методе findPassword произошла ошибка бд", e);
        }
    }

    /**
     * Поиск SALT в БД
     */
    public String findSalt(String login) throws DbException {
        logger.debug("Выполняется поиск соли пользователя - {} в базе", login);
        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT SALT FROM USER WHERE LOGIN = ?")) {
            prsmt.setString(1, login);
            ResultSet res = prsmt.executeQuery();
            if (res.next()) {
                logger.debug("Запрос выполнен успешно.");
                return res.getString("SALT");
            } else {
                logger.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                return null;
            }
        } catch (SQLException e) {
            logger.error(" В методе findSalt произошла ошибка бд", e);
            throw new DbException("В методе findPassword произошла ошибка бд", e);
        }
    }
}
