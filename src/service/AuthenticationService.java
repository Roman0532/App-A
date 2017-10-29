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
        boolean isCheck = false;
        for (User user : users) {
            //Проверка совпадает ли пароль с хранящимся в коллекции
            if ((login.equals(user.getLogin()))
                    && !PasswordService.isRightPass(password, user.getPassword(), user.getSalt())) {
                System.exit(2);
            }

            //Проверка Существует ли логин
            if ((login.equals(user.getLogin()))) {
                isCheck = true;
                break;
            }
        }
        if (!isCheck) {
            System.exit(1);
        }
    }
}
