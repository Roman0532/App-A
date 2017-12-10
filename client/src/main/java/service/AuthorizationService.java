package service;

import dao.AuthorizationDao;
import domain.Roles;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuthorizationService {
    private AuthorizationDao authorizationDao;

    public AuthorizationService(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    /**
     * Авторизация
     */
    public int authorize(String login, String role, String resource) throws DbException {
        if (!Roles.isCheckValidRole(role)) {
            log.error("Роль {} не существует", role);
            return 3;
        }

        if (authorizationDao.isFindRes(login, resource, role) == null) {
            log.error("Пользователь {} не имеет доступ к ресурсу {}", login, resource);
            return 4;
        } else {
            log.error("Пользователь {} имеет доступ к ресурсу {}", login, resource);
            return 0;
        }
    }
}
