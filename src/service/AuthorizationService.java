package service;

import domain.Roles;
import domain.UserRes;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class AuthorizationService {
    /**
     * Авторизация
     */
    public static void authorize(String role, String resource,
                                 ArrayList<UserRes> userRes) throws NoSuchAlgorithmException {
        if (!Roles.isCheckValidRole(role)) {
            System.exit(3);
        }

        if (!isFindResource(role, resource, userRes)) {
            System.exit(4);
        }
    }

    /**
     * Поиск дочернего ресурса
     */
    private static boolean isCheckChildPaths(String resourceUser,
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

    /**
     * Проверка ресурсов
     */
    private static boolean isFindResource(String role, String resource,
                                          ArrayList<UserRes> userRes) {
            for (UserRes userRe : userRes) {
                //Проверка на роль и поиск дочерних ресурсов
                if (role.equals(userRe.getRoleName())
                        && isCheckChildPaths(userRe.getPath(), resource)) {
                    return true;
                }
            }
        return false;
    }
}
