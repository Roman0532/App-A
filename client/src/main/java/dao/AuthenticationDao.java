package dao;

import com.google.inject.Inject;
import domain.User;
import provider.ConnectionProvider;
import provider.DaoProvider;
import lombok.extern.log4j.Log4j2;
import service.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Log4j2
public class AuthenticationDao {
    private ConnectionProvider<Connection> dbConn;
    @Inject
    public AuthenticationDao(@DaoProvider ConnectionProvider<Connection> dbConn) {
        this.dbConn = dbConn;
    }

    /**
     * Поиск всех пользователей
     */
    public ArrayList<User> findAllUsers() throws DbException {
        log.debug("Выполняется поиск пользователей в базе данных");

        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement pstmt = dbConn.get().prepareStatement("SELECT * FROM USER")) {
            try (ResultSet res = pstmt.executeQuery()) {
                while (res.next()) {
                    log.debug("Запрос выполнен успешно");
                    users.add(new User(res.getLong("ID"), res.getString("LOGIN")));
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

        try (PreparedStatement pstmt = dbConn.get().prepareStatement("SELECT ID FROM USER WHERE LOGIN = ?")) {
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
    public User findLoginById(int id) throws DbException {
        log.debug("Выполняется поиск пользователя в базе данных");

        try (PreparedStatement pstmt = dbConn.get().prepareStatement("SELECT * FROM USER WHERE ID = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    log.debug("Запрос выполнен успешно");
                    return new User(res.getLong("ID"), res.getString("LOGIN"));
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

        try (PreparedStatement pstmt = dbConn.get().prepareStatement("SELECT LOGIN FROM USER WHERE LOGIN = ?")) {
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

        try (PreparedStatement prsmt = dbConn.get().prepareStatement("SELECT PASSWORD FROM USER WHERE LOGIN = ?")) {
            prsmt.setString(1, login);
            try (ResultSet res = prsmt.executeQuery()) {
                if (res.next()) {
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
        try (PreparedStatement prsmt = dbConn.get().prepareStatement("SELECT SALT FROM USER WHERE LOGIN = ?")) {
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
