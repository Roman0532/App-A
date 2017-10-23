import org.apache.commons.cli.ParseException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    /**
     * Генерация соли
     */
    private static String salt() {
        /*Получить 32битное значение */
        final Random RANDOM = new SecureRandom();
        byte[] s = new byte[32];
        RANDOM.nextBytes(s);
        /*Перевод в строку*/
        BigInteger bigInt = new BigInteger(1, s);
        return bigInt.toString(16);
    }

    /**
     * Хэш пароля
     */
    private static String hashPass(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(str.getBytes());

        byte[] digest;

        digest = messageDigest.digest();

        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }

    private static void checkChildPaths(String rU, String tR) {
        String[] resUser = rU.split("\\.");
        String[] resTransf = tR.split("\\.");

        if (resTransf.length < resUser.length) {
            System.exit(4);
        }

        for (int i = 0; i < resUser.length; i++) {
            if (!resUser[i].equals(resTransf[i])) {
                System.exit(4);
            }
        }
    }

    /**
     * Проверка обьема
     */
    private static boolean isCheckValue(String volume) {
        return volume.matches("^-?\\d+$");
    }

    /**
     * Проверка даты
     */
    private static boolean isCheckDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Аккаунтинг
     */
    private static void accounting(String ds, String de, String vol) throws NoSuchAlgorithmException {

        if (!isCheckDate(ds)) {
            System.exit(5);
        }

        if (!isCheckDate(de)) {
            System.exit(5);
        }

        if (!isCheckValue(vol)) {
            System.exit(5);
        }
    }

    /**
     * Авторизация
     */

    private static void authorization(String log, String rol, String res,
                                      ArrayList<User> users, ArrayList<UserRes> userRes) throws NoSuchAlgorithmException {
        for (UserRes userRe : userRes) {
            for (User user : users) {
                if ((user.getId().equals(userRe.getUser_id())) && ((log.equals(user.getLogin()))) && (!rol.equals(userRe.getRole()))) {
                    System.exit(3);

                }
            }
        }
        for (UserRes userRe : userRes) {
            for (User user : users) {
                if ((user.getId().equals(userRe.getUser_id())) && ((log.equals(user.getLogin()))) && (!res.equals(userRe.getPath()))) {
                    checkChildPaths(userRe.getPath(), res);

                }
            }
        }
    }


    private static boolean isRightPass(String pass,String userPass,String salt) throws NoSuchAlgorithmException {
        return hashPass(hashPass(pass + salt)).equals(hashPass(hashPass(userPass + salt)));
    }

    /**
     * Аутентификация
     */
    private static void authentication(String log, String pass, ArrayList<User> users) throws NoSuchAlgorithmException {
        boolean isCheck = false;
        /*Проходим по списку юзеров*/
        for (User user : users) {
            /*Проверка совпадают ли переданный логин и пароль с хранящимися в коллекции*/
            if ((log.equals(user.getLogin())) && isRightPass(pass,user.getPassword(),user.getSalt())){
                isCheck = true;
            }
             /*Проверка совпадает ли пароль с хранящимся в коллекции*/
            if ((log.equals(user.getLogin()))&& !isRightPass(pass,user.getPassword(),user.getSalt())){
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

    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException {
        Parse cmd = new Parse();
        UserData customDate = cmd.parse(args);
        /*Коллекция пользователей*/
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L, "Roman", "123", salt()));
        users.add(new User(2L, "Roman1", "000", salt()));
        users.add(new User(3L, "Roman2", "0000", salt()));
        /*Коллекция ресурсов*/
        ArrayList<UserRes> userRes = new ArrayList<>();
        userRes.add(new UserRes(1L, 1L, "a.b", Roles.READ.toString()));
        //userRes.add(new UserRes(4L, 1L, "A.B.C.D", Roles.READ.toString()));
        userRes.add(new UserRes(2L, 2L, "A.B.C.D", Roles.WRITE.toString()));
        userRes.add(new UserRes(3L, 3L, "a.b", Roles.EXECUTE.toString()));


        if (cmd.isAuthentication()) {
            authentication(customDate.getLogin(), customDate.getPassword(), users);
        }

        if (cmd.isAuthorization()) {
            authorization(customDate.getLogin(), customDate.getRole(), customDate.getResource(),
                    users, userRes);
        }

        if (cmd.isAccounting()) {
            accounting(customDate.getDataStart(), customDate.getDataEnd(), customDate.getVolume());
        }

        if (cmd.isHelp()) {
            cmd.help();
        }
    }
}