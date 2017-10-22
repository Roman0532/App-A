import org.apache.commons.cli.ParseException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Roman Maximov on 11.10.2017
 */

public class Main {
    //Генерация соли
    private static String Salt() {
        final Random RANDOM = new SecureRandom();
        byte[] s = new byte[32];
        RANDOM.nextBytes(s);
        BigInteger bigInt = new BigInteger(1, s);
        return bigInt.toString(16);
    }

    //Хэш пароля
    private static String hashPass(String st) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));
        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }

    //Проверка обьема
    private static boolean checkValue(String volume) {
        return volume.matches("^-?\\d+$");
    }

    //Проверка даты
    private static boolean checkDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //Аккаунтинг
    private static void isAccount(String log, String pass, String rol, String res, ArrayList<User> users, ArrayList<Resource> resources, ArrayList<UserRes> userRes, String ds, String de, String vol) {

        isAutoriz(log, pass, rol, res, users, resources, userRes);

        if (!checkDate(ds)) {
            System.exit(5);
        }
        if (!checkDate(de)) {
            System.exit(5);
        }
        if (!checkValue(vol)) {
            System.exit(5);
        }
    }

    //Авторизация
    private static void isAutoriz(String log, String pass, String rol, String res, ArrayList<User> users, ArrayList<Resource> resours, ArrayList<UserRes> userRes) {
        isAuth(log, pass, users);
        for (UserRes userRe : userRes) {
            for (User user : users) {
                if (user.getId().equals(userRe.getUser_id()) && (log.equals(user.getLogin()))) {
                    if (!rol.equals(userRe.getRole())) {
                        System.exit(3);
                    }
                }
            }
        }
        for (int k = 0; k < users.size(); k++) {
            for (Resource resource : resours) {
                if ((log.equals(users.get(k).getLogin()))) {
                    if (resource.getId().equals(userRes.get(k).getRes_id())) {
                        {
                            if (!res.equals(resource.getPath())) {
                                {
                                    StringTokenizer resUser = new StringTokenizer(resource.getPath(), "\\.");
                                    StringTokenizer transfRes = new StringTokenizer(res, "\\.");
                                    String[] tRes = new String[transfRes.countTokens()];
                                    String[] rUser = new String[resUser.countTokens()];
                                    if (tRes.length < rUser.length) {
                                        System.exit(4);
                                    }
                                    for (int i = 0; i < rUser.length; i++) {
                                        rUser[i] = resUser.nextToken();
                                    }
                                    for (int j = 0; j < tRes.length; j++) {
                                        tRes[j] = transfRes.nextToken();
                                    }
                                    for (int i = 0; i < rUser.length; i++) {
                                        if (!rUser[i].equals(tRes[i])) {
                                            System.exit(4);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Аутентификация
    private static void isAuth(String log, String pass, ArrayList<User> users) {
        boolean checkLogin = false;
            for (User user : users) {
                if ((log.equals(user.getLogin())) && (hashPass(hashPass(pass + user.getSalt())).equals(hashPass(hashPass(user.getPassword() + user.getSalt()))))) {
                    break;
            }
        }
        for (User user : users) {
            if (log.equals(user.getLogin())) {
                if (!hashPass(hashPass(pass + user.getSalt())).equals(hashPass(hashPass(user.getPassword() + user.getSalt())))) {
                    System.exit(2);
                }
            }
        }
        for (User user : users) {
            if ((log.equals(user.getLogin()))) {
                checkLogin = true;
            }
        }
        if (!checkLogin) {
            System.exit(1);
        }
    }

    public static void main(String[] args) throws ParseException {
        //Коллекция пользователей
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L, "Roman", "123", Salt()));
        users.add(new User(2L, "Roman1", "000", Salt()));
        users.add(new User(3L, "Roman2", "0000", Salt()));
        //Коллекция ресурсов
        ArrayList<Resource> resource = new ArrayList<>();
        resource.add(new Resource(1L, "a.b"));
        resource.add(new Resource(2L, "A.B.C.D"));
        //Коллекция связи пользователя и ресурса
        ArrayList<UserRes> userRes = new ArrayList<>();
        userRes.add(new UserRes(1L, 1L, 1L, Roles.READ.toString()));
        userRes.add(new UserRes(2L, 2L, 2L, Roles.WRITE.toString()));
        userRes.add(new UserRes(3L, 3L, 1L, Roles.EXECUTE.toString()));

        Common cmd = new Common();
        CustomDate customDate = cmd.Parse(args);

        if (cmd.isAuthentication()){
          isAuth(customDate.getLogin(),customDate.getPassword(),users); }

        if (cmd.isAuthorization())
        {
          isAutoriz(customDate.getLogin(),customDate.getPassword(),customDate.getRole(),customDate.getResource(),users,resource,userRes);
        }

        if (cmd.isAccounting()){
            isAccount(customDate.getLogin(),customDate.getPassword(),customDate.getRole(),customDate.getResource(),users,resource,userRes,customDate.getDataStart(),customDate.getDataEnd(),customDate.getVolume());
       }
        if (cmd.isHelp()){
            cmd.help();
        }
    }
}