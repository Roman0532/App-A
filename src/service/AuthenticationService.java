package service;

import dao.AuthenticationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthenticationService {
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

    /**
     * Аутентификация
     */
    public static int authenticate(String login, String password)
            throws NoSuchAlgorithmException, SQLException {
        if (AuthenticationDao.findLogin(login) == null) {
            logger.error("Логин " + login + " не найден");
            return 1;
        }

        if (isCheckPassword(login, password)) {
            logger.error("Пароль " + password + " не верный для пользователя " + login);
            return 2;
        }
        return 0;
    }

    /**
     * Проверка пароля
     */
    private static boolean isCheckPassword(String login, String password)
            throws NoSuchAlgorithmException, SQLException {
        String userPassword = AuthenticationDao.findPassword(login);
        String userSalt = AuthenticationDao.findSalt(login);
        return (AuthenticationDao.findLogin(login) != null && !PasswordService.isRightPass(password, userPassword, userSalt));
    }
}
