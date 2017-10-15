import java.util.ArrayList;

/**
 * Created by Roman Maximov on 11.10.2017
 */

public class Main {
    public static void main(String[] args) {
        boolean login = false;
        boolean role = false;
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User((long)1,"Roman","123"));
        users.add(new User((long)2,"Roman1","000"));
        users.add(new User((long)3,"Roman2","0000"));

        ArrayList<Resourse> resourses = new ArrayList<Resourse>();
        resourses.add(new Resourse((long)1,"a.b"));
        resourses.add(new Resourse((long)2,"AB"));

        ArrayList<UserRes> userRes = new ArrayList<UserRes>();
        userRes.add(new UserRes((long)1,(long)1,(long)1,Roles.READ.toString()));
        userRes.add(new UserRes((long)2,(long)2,(long)2,Roles.WRITE.toString()));
        userRes.add(new UserRes((long)3,(long)3,(long)3,Roles.EXECUTE.toString()));

        if (args.length < 2) {
            System.out.println("Недостаточно параметров");
        }
         else if(args.length == 2) {
            for (int i = 0; i < users.size(); i++) {
                if ((args[0].equals(users.get(i).getLogin())) & (args[1].equals(users.get(i).getPassword()))) {
                    System.exit(0);
                }
            }
            for (int i = 0; i < users.size(); i++) {
                if (args[0].equals(users.get(i).getLogin())) {
                    if (!args[1].equals(users.get(i).getPassword())) {
                        System.exit(2);
                    }
                }
            }
            for (int i = 0; i < users.size(); i++) {
                if ((args[0].equals(users.get(i).getLogin()))) {
                       login = true;
                }
            }  if (!login){System.exit(1);}
        }
        //Авторизация Проверка на запрашиваемую роль
        if (args.length == 3){
            for (int i = 0; i <userRes.size(); i++) {
                for (int j = 0; j < users.size(); j++) {
                    if (users.get(j).getId().equals(userRes.get(i).getUser_id()) && args[1].equals(users.get(j).getPassword()) && (args[0].equals(users.get(j).getLogin()) ))
                    {
                        if(args[2].equals(userRes.get(i).getRole()))
                        {   role = true;
                            System.exit(5);
                        }
                    }
                }
            } if (!role) {System.exit(3);}
            for (int i = 0; i < users.size(); i++) {
                if ((args[0].equals(users.get(i).getLogin()))) {
                    login = true;
                }
            }  if (!login){System.exit(1);}
            for (int i = 0; i < users.size(); i++) {
                if (args[0].equals(users.get(i).getLogin())) {
                    if (!args[1].equals(users.get(i).getPassword())) {
                        System.exit(2);
                    }
                }
            }
        }
    }
}
