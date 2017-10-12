import java.util.ArrayList;

/**
 * Created by Roman Maximov on 11.10.2017
 */

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User((long)1,"Roman","123"));
        users.add(new User((long)2,"Roman1","000"));

        ArrayList<Resourse> resourses = new ArrayList<Resourse>();
        resourses.add(new Resourse((long)1,"a.b"));
        resourses.add(new Resourse((long)1,"AB"));

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
                    if (args[1]!=(users.get(i).getPassword())) {
                        System.exit(2);
                    }
                }
            }
            for (int i = 0; i < users.size(); i++) {
                if ((args[0]!=(users.get(i).getLogin()))) {
                    System.exit(1);
                }

            }
        }
    }
}
