import classes.Parse;
import classes.User;
import classes.UserData;
import classes.UserRes;
import methods.Methods;
import org.apache.commons.cli.ParseException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException {
        ArrayList<User> collectionUsers = Methods.storageCollectionsUsers();
        ArrayList<UserRes> collectionUserRes = Methods.storageCollectionsUserRes();

        /*Передача аргументов парсеру*/
        Parse cmd = new Parse();
        UserData customDate = cmd.parse(args);

        if (cmd.isAuthentication()) {
            Methods.toAuthenticate(customDate.getLogin(), customDate.getPassword(), collectionUsers);
        }

        if (cmd.isAuthorization()) {
            Methods.toAuthorize(customDate.getLogin(), customDate.getRole(), customDate.getResource(),
                    collectionUsers, collectionUserRes);
        }

        if (cmd.isAccounting()) {
            Methods.toAccounting(customDate.getDataStart(), customDate.getDataEnd(), customDate.getVolume());
        }

        if (cmd.isHelp()) {
            cmd.help();
        }
    }
}