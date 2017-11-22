package service;

import dao.AuthorizationDao;
import domain.Roles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorizationService {
    private static final Logger logger = LogManager.getLogger(AuthorizationService.class.getName());
    private AuthorizationDao authorizationDao;

    public AuthorizationService(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    private AuthorizationDao getAuthorizationDao() {
        return authorizationDao;
    }

    /**
     * Авторизация
     */
    public int authorize(String login, String role, String resource) {
        if (!Roles.isCheckValidRole(role)) {
            logger.error("Роль {} не существует", role);
            return 3;
        }

        if (getAuthorizationDao().isFindRes(login, resource, role) == null) {
            logger.error("Пользователь {} не имеет доступ к ресурсу {}", login, resource);
            return 4;
        } else {
            return 0;
        }
    }
}
