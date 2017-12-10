package service;

import dao.AuthenticationDao;
import lombok.extern.log4j.Log4j2;

import java.security.NoSuchAlgorithmException;

@Log4j2
public class AuthenticationService {
    private AuthenticationDao authenticationDao;
    private PasswordService passwordService;

    public AuthenticationService(AuthenticationDao authenticationDao, PasswordService passwordService) {
        this.authenticationDao = authenticationDao;
        this.passwordService = passwordService;
    }

    /**
     * Аутентификация
     */
    public int authenticate(String login, String password)
            throws NoSuchAlgorithmException, DbException {
        if (authenticationDao.findLogin(login) == null) {
            log.error("Логин {} не найден", login);
            return 1;
        } else if (!passwordService.isRightPass(password,
                authenticationDao.findPassword(login), authenticationDao.findSalt(login))) {
            log.error("Пароль {} не верный для пользователя {}", password, login);
            return 2;
        } else {
            log.debug("Пароль {} верный для пользователя {}", password, login);
            return 0;
        }
    }
}
