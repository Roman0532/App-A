package service;

import dao.AuthenticationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;

public class AuthenticationService {
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class.getName());
    private AuthenticationDao authenticationDao;
    private PasswordService passwordService = new PasswordService();

    public AuthenticationService(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    /**
     * Аутентификация
     */
    public int authenticate(String login, String password)
            throws NoSuchAlgorithmException{
        if (authenticationDao.findLogin(login) == null) {
            logger.error("Логин {} не найден", login);
            return 1;
        } else if (!passwordService.isRightPass(password,
                authenticationDao.findPassword(login), authenticationDao.findSalt(login))) {
            logger.error("Пароль {} не верный для пользователя {}", password, login);
            return 2;
        } else {
            logger.debug("Пароль {} верный для пользователя {}", password, login);
            return 0;
        }
    }
}
