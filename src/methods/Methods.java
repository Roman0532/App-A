package methods;

import classes.Roles;
import classes.User;
import classes.UserRes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Methods {

    /**
     * Коллекция пользователей
     */
    public static ArrayList<User> storageCollectionsUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L, "Roman", "123", Methods.generateSalt()));
        users.add(new User(2L, "Roman1", "000", Methods.generateSalt()));
        users.add(new User(3L, "Roman2", "0000", Methods.generateSalt()));

        return users;
    }

    /**
     * Коллекция пользователей их ресурсов и ролей
     */
    public static ArrayList<UserRes> storageCollectionsUserRes() {
        ArrayList<UserRes> userRes = new ArrayList<>();
        userRes.add(new UserRes(1L, 1L, "a.b", Roles.READ));
        userRes.add(new UserRes(2L, 1L, "A.B.C.D", Roles.READ));
        userRes.add(new UserRes(3L, 1L, "A.B.C.D", Roles.WRITE));
        userRes.add(new UserRes(4L, 2L, "A.B.C.D", Roles.WRITE));
        userRes.add(new UserRes(6L, 2L, "AB", Roles.EXECUTE));
        userRes.add(new UserRes(5L, 3L, "a.b", Roles.EXECUTE));

        return userRes;
    }

    /**
     * Аутентификация
     */
    public static void toAuthenticate(String log, String pass,
                                      ArrayList<User> users) throws NoSuchAlgorithmException {
        boolean isCheck = false;
        for (User user : users) {
             /*Проверка совпадает ли пароль с хранящимся в коллекции*/
            if ((log.equals(user.getLogin())) && !Methods.isRightPass(pass, user.getPassword(), user.getSalt())) {
                System.exit(2);
            }

            /*Проверка Существует ли логин*/
            if ((log.equals(user.getLogin()))) {
                isCheck = true;
            }
        }
        if (!isCheck) {
            System.exit(1);
        }
    }

    /**
     * Авторизация
     */
    public static void toAuthorize(String log, String rol, String res,
                                   ArrayList<User> users, ArrayList<UserRes> userRes) throws NoSuchAlgorithmException {
        checkRolesRes(log, rol, res, users, userRes);
    }

    /**
     * Аккаунтинг
     */
    public static void toAccounting(String ds, String de, String vol) throws NoSuchAlgorithmException {
        /*Проверки валидности даты*/
        if (!Methods.isCheckDate(ds)) {
            System.exit(5);
        }

        if (!Methods.isCheckDate(de)) {
            System.exit(5);
        }

        /*Проверка валидности объема*/
        if (!Methods.isCheckValue(vol)) {
            System.exit(5);
        }
    }

    /**
     * Проверка ролей и ресурсов
     */
    private static void checkRolesRes(String log, String rol, String res,
                                      ArrayList<User> users, ArrayList<UserRes> userRes) {
        if (!isValidRole(rol)) {
            System.exit(3);
        }
        boolean isCheckRes = false;
        for (User user : users) {
            for (UserRes userRe : userRes) {
                //Проверка на роль и поиск дочерних ресурсов
                if ((log.equals(user.getLogin())) && user.getId().equals(userRe.getUserId())
                        && rol.equals(userRe.getRole())
                        && Methods.isCheckChildPaths(userRe.getPath(), res)) {
                    isCheckRes = true;
                }
            }
        }
        if (!isCheckRes) {
            System.exit(4);
        }
    }

    /**
     * Генерация соли
     */
    private static String generateSalt() {
        //Получить 32 битное значение //
        final Random RANDOM = new SecureRandom();
        byte[] s = new byte[32];
        RANDOM.nextBytes(s);

        //Перевод в строку//
        BigInteger bigInt = new BigInteger(1, s);
        return bigInt.toString(16);
    }


    /**
     * Хэш пароля
     */
    private static String generateHashPassword(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        //Создание обьекта для использования алгоритма//
        messageDigest = MessageDigest.getInstance("MD5");
        //Сброс//
        messageDigest.reset();
        //Обновляет digest используя указанный байт//
        messageDigest.update(str.getBytes());

        byte[] digest;

        digest = messageDigest.digest();

        //Конвертируем байт в шестнадцатеричный формат
        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }

    /**
     * Проверка валидности роли
     */
    private static boolean isValidRole(String rol) {
        return rol.equals("READ") || rol.equals("WRITE") || rol.equals("EXECUTE");
    }

    /**
     * Поиск дочернего ресурса
     */
    private static boolean isCheckChildPaths(String rU, String tR) {
        //Разбитие строк на узлы//
        String[] resUser = rU.split("\\.");
        String[] resTransf = tR.split("\\.");

        //Если длина переданного ресурса меньше длины ресурса пользователя возвращать false/s/
        if (resTransf.length < resUser.length) {
            return false;
        }

        //sЕсли совпадений не найдено возвращать false//
        for (int i = 0; i < resUser.length; i++) {
            if (!resUser[i].equals(resTransf[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка совпадает ли переданный хэшированный пароль с паролем пользователя
     */
    private static boolean isRightPass(String pass, String userPass, String salt) throws NoSuchAlgorithmException {
        return generateHashPassword(generateHashPassword(pass + salt)).equals(generateHashPassword(generateHashPassword(userPass + salt)));
    }

    /**
     * Проверка валидности обьема
     */
    private static boolean isCheckValue(String volume) {
        return volume.matches("^-?\\d+$");
    }

    /**
     * Проверка валидности даты
     */
    private static boolean isCheckDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
