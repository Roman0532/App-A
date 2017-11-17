package service;

import dao.AuthorizationDao;
import domain.Roles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthorizationService {
    private static final Logger logger = LogManager.getLogger(AuthorizationService.class);

    /**
     * Авторизация
     */
    public static int authorize(String login, String role, String resource)
            throws NoSuchAlgorithmException, SQLException {
        if (!Roles.isCheckValidRole(role)) {
            logger.error("Роль " + role + " не существует");
            return 3;
        }

        if (!AuthorizationDao.isFindRes(login, resource, role)) {
            logger.error("Пользователь " + login + " не имеет доступ к ресурсу " + resource);
            return 4;
        }
        return 0;
    }

    /**
     * Проверка дочерних ресурсов
     */
    public static boolean isCheckChildPaths(String resourceUser,
                                            String suppliedResource) {
        //Разбитие строк на узлы
        String[] resUser = resourceUser.split("\\.");
        String[] resSupplied = suppliedResource.split("\\.");

        //Если длина переданного ресурса меньше длины ресурса пользователя возвращать false
        if (resSupplied.length < resUser.length) {
            return false;
        }

        //Если совпадений не найдено возвращать false
        for (int i = 0; i < resUser.length; i++) {
            if (!resUser[i].equals(resSupplied[i])) {
                return false;
            }
        }
        return true;
    }
}
