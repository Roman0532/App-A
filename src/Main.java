import dao.AccountingDao;
import dao.AuthenticationDao;
import dao.AuthorizationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import service.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws NoSuchAlgorithmException, org.apache.commons.cli.ParseException, SQLException {

        logger.debug("Приложение App-A запущено");
        logger.debug("Выполнение миграций");

        Flyway flyway = new Flyway();

        flyway.setDataSource("jdbc:h2:./src/resources/db/migration/aaa", "sa", "");

        flyway.migrate();

        logger.debug("---Установка соеденения---");
        java.sql.Connection dbConn = ConnectionService.getDbConnection();

        if (dbConn != null) {
            logger.debug("Соеденение прошло успешно");
        } else {
            logger.debug("Соеденение не удалось");
        }

        //Передача аргументов парсеру
        ParseService cmd = new ParseService();
        UserData userData = cmd.parse(args);
        new AuthenticationDao(dbConn);
        new AuthorizationDao(dbConn);
        new AccountingDao(dbConn);
        int exitCode = 0;

        if (!userData.isAuthentication()) {
            logger.debug("Параметров не передано,выполняется вывод справки");
            ParseService.printHelp();
        }

        if (userData.isAuthentication()) {
            logger.debug("Передано 2 параметра, выполняется аутентификация");
            exitCode = AuthenticationService.authenticate(userData.getLogin(), userData.getPassword());
        }

        if (userData.isAuthorization()) {
            logger.debug("Передано 4 параметра, выполняется авторизация");
            if (exitCode == 0) {
                exitCode = AuthorizationService.authorize(userData.getLogin(), userData.getRole(), userData.getResource());
            }
        }

        if (userData.isAccounting()) {
            logger.debug("Передано 7 параметров, выполняется аккаунтинг");
            if (exitCode == 0)
                exitCode = AccountingService.accounting(userData.getDataStart(),
                        userData.getDataEnd(), userData.getVolume(), userData);
        }

        if (dbConn != null) {
            dbConn.close();
            logger.debug("Соеденение закрыто");
        }

        System.exit(exitCode);
    }
}