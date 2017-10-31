package service;

import domain.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class AuthenticationService {

    /**
     * Аутентификация
     */
    public static void authenticate(String login, String password,
                                    ArrayList<User> users) throws NoSuchAlgorithmException {
        if (!isCheckLogin(login, users)) {
            System.exit(1);
        }

        if (isCheckPassword(login, password, users)) {
            System.exit(2);
        }
    }

    /**
     * Проверка существования логина
     */
    private static boolean isCheckLogin(String login, ArrayList<User> users) {
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка Пароля
     */
    private static boolean isCheckPassword(String login, String password, ArrayList<User> users) throws NoSuchAlgorithmException {
        for (User user : users) {
            //Проверка совпадает ли пароль с хранящимся в коллекции
            if ((login.equals(user.getLogin()))
                    && !PasswordService.isRightPass(password, user.getPassword(), user.getSalt())) {
                return true;
            }
        }
        return false;
    }
}
