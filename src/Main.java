import domain.Accouning;
import domain.Roles;
import domain.User;
import domain.UserRes;
import org.apache.commons.cli.ParseException;
import service.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {

    /**
     * Коллекция пользователей
     */
    private static ArrayList<User> storageCollectionsUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L, "Roman", "123", PasswordService.generateSalt()));
        users.add(new User(2L, "Roman1", "000", PasswordService.generateSalt()));
        users.add(new User(3L, "Roman2", "0000", PasswordService.generateSalt()));

        return users;
    }

    /**
     * Коллекция пользователей их ресурсов и ролей
     */
    private static ArrayList<UserRes> storageCollectionsUserRes() {
        ArrayList<UserRes> userRes = new ArrayList<>();
        userRes.add(new UserRes(1L, 1L, "a.b", Roles.READ));
        userRes.add(new UserRes(2L, 1L, "A.B.C.D", Roles.READ));
        userRes.add(new UserRes(3L, 1L, "A.B.C.D", Roles.WRITE));
        userRes.add(new UserRes(4L, 2L, "A.B.C.D", Roles.WRITE));
        userRes.add(new UserRes(6L, 2L, "AB", Roles.EXECUTE));
        userRes.add(new UserRes(5L, 3L, "a.b", Roles.EXECUTE));

        return userRes;
    }

    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException {
        ArrayList<Accouning> data = new ArrayList<>();
        ArrayList<User> collectionUsers = storageCollectionsUsers();
        ArrayList<UserRes> collectionUserRes = storageCollectionsUserRes();

        //Передача аргументов парсеру
        ParseService cmd = new ParseService();
        UserData userData = cmd.parse(args);

        if (!userData.isAuthentication()) {
            ParseService.printHelp();
        }

        if (userData.isAuthentication()) {
            AuthenticationService.authenticate(userData.getLogin(),
                    userData.getPassword(), collectionUsers);
        }

        if (userData.isAuthorization()) {
            AuthorizationService.authorize(userData.getLogin(), userData.getRole(),
                    userData.getResource(), collectionUsers, collectionUserRes);
        }
        if (userData.isAccounting()) {
            AccountingService.accounting(userData.getDataStart(),
                    userData.getDataEnd(), userData.getVolume(), userData, data);
        }

    }
}