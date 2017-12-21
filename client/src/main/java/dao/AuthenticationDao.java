package dao;

import lombok.extern.log4j.Log4j2;
import service.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Log4j2
public class AuthenticationDao {
    private Connection dbConn;

    public AuthenticationDao(Connection dbConn) {
        this.dbConn = dbConn;
    }

    /**
     * Поиск всех пользователей
     */
    public ArrayList<String> findAllUsers() throws DbException {
        log.debug("Выполняется поиск пользователей в базе данных");

        ArrayList<String> users = new ArrayList<String>();
        try (PreparedStatement pstmt = dbConn.prepareStatement("SELECT LOGIN FROM USER")) {
            try (ResultSet res = pstmt.executeQuery()) {
                while (res.next()) {
                    log.debug("Запрос выполнен успешно");
                    users.add(res.getString("LOGIN"));
                }
                return users;
            }
        } catch (SQLException e) {
            log.error("В методе findAllUsers произошла ошибка бд", e);
            throw new DbException("Произошла ошибка бд", e);
        }
    }

    /**
     * Поиск ID в БД
     */
    int findId(String login) throws DbException {
        log.debug("Выполняется поиск id пользователя - {} в базе", login);

        try (PreparedStatement pstmt = dbConn.prepareStatement("SELECT ID FROM USER WHERE LOGIN = ?")) {
            pstmt.setString(1, login);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    log.debug("Запрос выполнен успешно");
                    return res.getInt("ID");
                } else {
                    log.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                    return 1;
                }
            }
        } catch (SQLException e) {
            log.error("В методе findId произошла ошибка бд", e);
            throw new DbException("Произошла ошибка бд", e);
        }
    }

    /**
     * Поиск пользователя по id
     */
    public String findLoginById(int id) throws DbException {
        log.debug("Выполняется поиск пользователя в базе данных");

        try (PreparedStatement pstmt = dbConn.prepareStatement("SELECT LOGIN FROM USER WHERE ID = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    log.debug("Запрос выполнен успешно");
                    return res.getString("LOGIN");
                } else {
                    log.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                    return null;
                }
            }
        } catch (SQLException e) {
            log.error("В методе findLoginById произошла ошибка бд", e);
            throw new DbException("Произошла ошибка бд", e);
        }
    }

    /**
     * Поиск LOGIN в БД
     */
    public String findLogin(String login) throws DbException {
        log.debug("Выполняется поиск перданного логина - {} в базе", login);

        try (PreparedStatement pstmt = dbConn.prepareStatement("SELECT LOGIN FROM USER WHERE LOGIN = ?")) {
            pstmt.setString(1, login);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    log.debug("Запрос выполнен успешно");
                    return res.getString("LOGIN");
                } else {
                    log.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                    return null;
                }
            }
        } catch (SQLException e) {
            log.error("В методе findLogin произошла ошибка бд", e);
            throw new DbException("В методе findLogin произошла ошибка бд", e);
        }
    }

    /**
     * Поиск PASSWORD в БД
     */
    public String findPassword(String login) throws DbException {
        log.debug("Выполняется поиск пароля пользователя- {} в базе", login);

        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT PASSWORD FROM USER WHERE LOGIN = ?")) {
            prsmt.setString(1, login);
            try (ResultSet res = prsmt.executeQuery()) {
                if (res.next()) {
                    //       logger.debug("Запрос выполнен успешно.");
                    return res.getString("PASSWORD");
                } else {
                    log.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                    return null;
                }
            }
        } catch (SQLException e) {
            log.error(" В методе findPassword произошла ошибка бд", e);
            throw new DbException("В методе findPassword произошла ошибка бд", e);
        }
    }

    /**
     * Поиск SALT в БД
     */
    public String findSalt(String login) throws DbException {
        log.debug("Выполняется поиск соли пользователя - {} в базе", login);
        try (PreparedStatement prsmt = dbConn.prepareStatement("SELECT SALT FROM USER WHERE LOGIN = ?")) {
            prsmt.setString(1, login);
            try (ResultSet res = prsmt.executeQuery()) {
                if (res.next()) {
                    log.debug("Запрос выполнен успешно.");
                    return res.getString("SALT");
                } else {
                    log.error("В бд отсутствуют записи, удовлетворяющие критерию поиска");
                    return null;
                }
            }
        } catch (SQLException e) {
            log.error(" В методе findSalt произошла ошибка бд", e);
            throw new DbException("В методе findPassword произошла ошибка бд", e);
        }
    }
}
