package service;

import domain.Roles;
import domain.User;
import domain.UserRes;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class AuthorizationService {
    /**
     * Авторизация
     */
    public static void authorize(String login, String role, String resource,
                                 ArrayList<User> users, ArrayList<UserRes> userRes) throws NoSuchAlgorithmException {
        boolean isCheckRes = false;

        if (!Roles.isCheckValidRole(role)) {
            System.exit(3);
        }

        for (User user : users) {
            for (UserRes userRe : userRes) {
                //Проверка на роль и поиск дочерних ресурсов
                if (isCheckData(login, user.getLogin(), user.getId(), userRe.getUserId(), role, userRe.getRoleName())
                        && isCheckChildPaths(userRe.getPath(), resource)) {
                    isCheckRes = true;
                    break;
                }
            }
        }
        if (!isCheckRes) {
            System.exit(4);
        }
    }

    /**
     * Проверка совпадения данных
     */

    private static boolean isCheckData(String login, String userLogin, Long userId, Long resourceId, String role, String userRole) {
        return login.equals(userLogin) && userId.equals(resourceId)
                && role.equals(userRole);
    }

    /**
     * Поиск дочернего ресурса
     */
    private static boolean isCheckChildPaths(String resourceUser, String suppliedResource) {
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
