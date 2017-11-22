import dao.AccountingDao;
import dao.AuthenticationDao;
import dao.AuthorizationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws NoSuchAlgorithmException, org.apache.commons.cli.ParseException {
        int exitCode = 0;

        logger.debug("Приложение App-A запущено");

        //Передача аргументов парсеру
        ParseService cmd = new ParseService();
        UserData userData = cmd.parse(args);

        ConnectionService connectionService = new ConnectionService();

        logger.debug("Выполнение миграций");
        connectionService.DbMigration();

        try (Connection dbConn = connectionService.getDbConnection()) {
            logger.debug("---Установка соеденения---");
            if (dbConn != null) {
                logger.debug("Соеденение прошло успешно");

                AuthenticationDao authenticationDao = new AuthenticationDao(dbConn);
                AuthorizationDao authorizationDao = new AuthorizationDao(dbConn, authenticationDao);
                AccountingDao accountingDao = new AccountingDao(dbConn);

                AuthenticationService authenticationService = new AuthenticationService(authenticationDao);
                AuthorizationService authorizationService = new AuthorizationService(authorizationDao);
                AccountingService accountingService = new AccountingService(accountingDao);

                if (!userData.isAuthentication()) {
                    logger.debug("Параметров не передано,выполняется вывод справки");
                    cmd.printHelp();
                }

                if (userData.isAuthentication()) {
                    logger.debug("Передано 2 параметра {} {} , выполняется аутентификация",
                            userData.getLogin(), userData.getPassword());
                    exitCode = authenticationService.authenticate(userData.getLogin(), userData.getPassword());
                }

                if (userData.isAuthorization()) {
                    logger.debug("Передано 4 параметра {} {} {} {}, выполняется авторизация", userData.getLogin(),
                            userData.getPassword(), userData.getRole(), userData.getResource());
                    if (exitCode == 0) {
                        exitCode = authorizationService.authorize(userData.getLogin(), userData.getRole(), userData.getResource());
                    }
                }

                if (userData.isAccounting()) {
                    logger.debug("Передано 7 параметров {} {} {} {} {} {} {}, выполняется аккаунтинг", userData.getLogin(),
                            userData.getPassword(), userData.getRole(), userData.getResource(),
                            userData.getDataStart(), userData.getDataEnd(), userData.getVolume());
                    if (exitCode == 0)
                        exitCode = accountingService.accounting(userData.getDataStart(),
                                userData.getDataEnd(), userData.getVolume(), userData);
                }
            } else {
                logger.error("Соеденение не удалось");
                System.exit(255);
            }
        } catch (SQLException e) {
            logger.error("Произошка ошибка БД", e);
        }
        System.exit(exitCode);
    }
}
