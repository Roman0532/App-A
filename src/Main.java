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
    public static String Salt() {
        final Random RANDOM = new SecureRandom();
        byte[] s = new byte[32];
        RANDOM.nextBytes(s);
        BigInteger bigInt = new BigInteger(1, s);
        String salt = bigInt.toString(16);
        return salt;
    }

    //Хэш пароля
    public static String hashPass(String st) {
        MessageDigest messageDigest = null;
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
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

    //Проверка обьема
    private static boolean chekValue(String voluve) {
        return voluve.matches("^-?\\d+$");
    }
    //Проверка даты
    private static boolean chekDate(String date) {
        try {
            LocalDate d = LocalDate.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //Аккаунтинг
    private static void isAccount(String ds, String de, String vol, int argslenght) {
        boolean check;
        boolean check1;
        boolean check2;
        if (chekDate(ds)) {
            check = true;
        } else {
            check = false;
        }
        if (chekDate(de)) {
            check1 = true;
        } else {
            check1 = false;
        }
        if (chekValue(vol)) {
            check2 = true;
        } else {
            check2 = false;
        }

        if (!check) {
            System.exit(5);
        }
        if (!check1) {
            System.exit(5);
        }
        if (!check2) {
            System.exit(5);
        }
    }

    //Авторизация
    private static void isAutoriz(String log, String rol, String res, ArrayList<User> users, ArrayList<Resourse> resourses, ArrayList<UserRes> userRes, int argslenght) {
        boolean role = false;
        boolean childRes = false;
        int count = 0;
        boolean path = false;
        for (UserRes userRe : userRes) {
            for (User user : users) {
                if (user.getId().equals(userRe.getUser_id()) && (log.equals(user.getLogin()))) {
                    if (rol.equals(userRe.getRole())) {
                        role = true;
                    }
                }
            }
        }
        if (!role) {
            System.exit(3);
        }
        for (int k = 0; k < users.size(); k++) {
            for (Resourse resourse : resourses) {
                if ((log.equals(users.get(k).getLogin()))) {
                    if (resourse.getId().equals(userRes.get(k).getRes_id())) {
                        {
                            if (res.equals(resourse.getPath())) {
                                path = true;
                                if (argslenght == 4) {
                                    System.exit(0);
                                }
                            } else if (!path) {
                                StringTokenizer resUser = new StringTokenizer(resourse.getPath(), "\\.");
                                StringTokenizer transfRes = new StringTokenizer(res, "\\.");
                                String[] tRes = new String[transfRes.countTokens()];
                                String[] rUser = new String[resUser.countTokens()];
                                if (tRes.length < rUser.length) {
                                    System.exit(4);
                                }
                                for (int i = 0; i < rUser.length; i++) {
                                    rUser[i] = (String) resUser.nextToken();
                                }
                                for (int j = 0; j < tRes.length; j++) {
                                    tRes[j] = (String) transfRes.nextToken();
                                }
                                for (int i = 0; i < rUser.length; i++) {
                                    if (rUser[i].equals(tRes[i])) {
                                        path = true;
                                    } else {
                                        path = false;
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
                if (count >= 1) {
                    System.exit(4);
                }
            }
        }
    }

    //Аутентификация
    private static void isAuth(String log, String pass, ArrayList<User> users, int argslenght) {
        boolean login = false;

        if (argslenght == 2) {
            for (User user : users) {
                if ((log.equals(user.getLogin())) && (hashPass(hashPass(pass + user.getSalt())).equals(hashPass(hashPass(user.getPassword() + user.getSalt()))))) {
                    System.exit(0);
                }
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
                login = true;
            }
        }
        if (!login) {
            System.exit(1);
        }
    }

    public static void main(String[] args) {

        int argslenght = args.length;
        //System.out.println(hashPass("qwerty "+Salt()));
        //Коллекция пользователей
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User((long) 1, "Roman", "123", Salt()));
        users.add(new User((long) 2, "Roman1", "000", Salt()));
        users.add(new User((long) 3, "Roman2", "0000", Salt()));
        //Коллекция ресурсов
        ArrayList<Resourse> resourses = new ArrayList<Resourse>();
        resourses.add(new Resourse((long) 1, "a.b"));
        resourses.add(new Resourse((long) 2, "A.B.C.D"));
        //Коллекция связи пользователя и ресурса
        ArrayList<UserRes> userRes = new ArrayList<UserRes>();
        userRes.add(new UserRes((long) 1, (long) 1, (long) 1, Roles.READ.toString()));
        userRes.add(new UserRes((long) 2, (long) 2, (long) 2, Roles.WRITE.toString()));
        userRes.add(new UserRes((long) 3, (long) 3, (long) 1, Roles.EXECUTE.toString()));


        // System.out.println(users.get(0).getSalt());
//        if (argslenght < 2) {
//            System.out.println("Недостаточно параметров");
//        }

        //UserData userData = new UserData(args[0],args[1]);

        if (argslenght == 2) {
            isAuth(args[0], args[1], users, args.length);
        }
        if (args.length == 4) {
            isAuth(args[0], args[1], users, args.length);
            isAutoriz(args[0], args[2], args[3], users, resourses, userRes, argslenght);

        }
        if (args.length == 7) {
            isAuth(args[0], args[1], users, args.length);
            isAutoriz(args[0], args[2], args[3], users, resourses, userRes, argslenght);
            isAccount(args[4], args[5], args[6], args.length);
        }
    }
}

