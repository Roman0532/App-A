import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Roman Maximov on 11.10.2017
 */

public class Main {
    //Авторизация
    private static void isAutoriz(String log, String pass, String rol, String res, ArrayList<User> users, ArrayList<Resourse> resourses, ArrayList<UserRes> userRes, int argslenght) {
        boolean role = false;
        boolean childRes = false;
        int count = 0;
        boolean path = false;
        for (UserRes userRe : userRes) {
            for (User user : users) {
                if (user.getId().equals(userRe.getUser_id()) && pass.equals(user.getPassword()) && (log.equals(user.getLogin()))) {
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
                if (pass.equals(users.get(k).getPassword()) && (log.equals(users.get(k).getLogin()))) {
                    if (resourse.getId().equals(userRes.get(k).getRes_id())) {
                        if (res.equals(resourse.getPath())) {
                            path = true;
                            System.exit(0);

                        } else {
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
                                    childRes = true;
                                } else {
                                    childRes = false;
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
    //Аутентификация
    private static void isAuth(String log, String pass, ArrayList<User> users, int argslenght) {
        boolean login = false;
        if (argslenght == 2) {
            for (User user : users) {
                if ((log.equals(user.getLogin())) & (pass.equals(user.getPassword()))) {
                    System.exit(0);
                }
            }
        }
        for (User user : users) {
            if (log.equals(user.getLogin())) {
                if (!pass.equals(user.getPassword())) {
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
        //Коллекция пользователей
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User((long) 1, "Roman", "123"));
        users.add(new User((long) 2, "Roman1", "000"));
        users.add(new User((long) 3, "Roman2", "0000"));
        //Коллекция ресурсов
        ArrayList<Resourse> resourses = new ArrayList<Resourse>();
        resourses.add(new Resourse((long) 1, "a.b"));
        resourses.add(new Resourse((long) 2, "A.B.C.D"));
        //Коллекция связи пользователя и ресурса
        ArrayList<UserRes> userRes = new ArrayList<UserRes>();
        userRes.add(new UserRes((long) 1, (long) 1, (long) 1, Roles.READ.toString()));
        userRes.add(new UserRes((long) 2, (long) 2, (long) 2, Roles.WRITE.toString()));
        userRes.add(new UserRes((long) 3, (long) 3, (long) 1, Roles.EXECUTE.toString()));
        if (argslenght < 2) {
            System.out.println("Недостаточно параметров");
        }
        if (argslenght == 2) {
            isAuth(args[0], args[1], users, args.length);
        }
        if (args.length == 4) {
            isAuth(args[0], args[1], users, args.length);
            isAutoriz(args[0], args[1], args[2], args[3], users, resourses, userRes, argslenght);
        }
    }
}
