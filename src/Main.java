import java.util.ArrayList;

/**
 * Created by Roman Maximov on 11.10.2017
 */

public class Main {
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
        boolean role = false;
        boolean login = false;
        //Коллекция пользователей
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User((long) 1, "Roman", "123"));
        users.add(new User((long) 2, "Roman1", "000"));
        users.add(new User((long) 3, "Roman2", "0000"));
        int argslenght = args.length;
        if (argslenght < 2) {
            System.out.println("Недостаточно параметров");
        }
        if (argslenght == 2) {
            isAuth(args[0], args[1], users, args.length);
        }
        //Коллекция ресурсов
        ArrayList<Resourse> resourses = new ArrayList<Resourse>();
        resourses.add(new Resourse((long) 1, "a.b"));
        resourses.add(new Resourse((long) 2, "AB"));
        //Коллекция связи пользователя и ресурса
        ArrayList<UserRes> userRes = new ArrayList<UserRes>();
        userRes.add(new UserRes((long) 1, (long) 1, (long) 1, Roles.READ.toString()));
        userRes.add(new UserRes((long) 2, (long) 2, (long) 2, Roles.WRITE.toString()));
        userRes.add(new UserRes((long) 3, (long) 3, (long) 1, Roles.EXECUTE.toString()));
        //Авторизация Проверка на запрашиваемую роль и проверка есть ли доступ к ресурсу( без дочерних )
        if (argslenght == 4) {
            isAuth(args[0], args[1], users, args.length);
            for (UserRes userRe : userRes) {
                for (User user : users) {
                    if (user.getId().equals(userRe.getUser_id()) && args[1].equals(user.getPassword()) && (args[0].equals(user.getLogin()))) {
                        if (args[2].equals(userRe.getRole())) {
                            role = true;
                        }
                    }
                }
            }
            if (!role) {
                System.exit(3);
            }
            boolean res = false;
            for (int k = 0; k < users.size(); k++) {
                for (Resourse resourse : resourses) {
                    if (args[1].equals(users.get(k).getPassword()) && (args[0].equals(users.get(k).getLogin()))) {
                        if (resourse.getId().equals(userRes.get(k).getRes_id())) {
                            if (args[3].equals(resourse.getPath())) {
                                res = true;
                                System.exit(0);
                            }
                        }
                    }

                }
            }


            if (!res) {
                System.exit(4);
            }
        }
    }
}
