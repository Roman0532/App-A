import dao.AccountingDao;
import dao.AuthenticationDao;
import dao.AuthorizationDao;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.cli.ParseException;
import service.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2

public class Main {
    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException, SQLException {

        int exitCode = 0;
        log.debug("Приложение App-A запущено");
        //Передача аргументов парсеру
        ParseService cmd = new ParseService();
        UserData userData = cmd.parse(args);

        ConnectionService connectionService = new ConnectionService();
        PasswordService passwordService = new PasswordService();
        log.debug("---Установка соеденения---");
        try (Connection dbConn = connectionService.get()) {
            log.debug("Выполнение миграций");
//            connectionService.dbMigration();
            log.debug("Соеденение прошло успешно");
            AuthenticationDao authenticationDao = new AuthenticationDao(dbConn);
            AuthorizationDao authorizationDao = new AuthorizationDao(dbConn, authenticationDao);
            AccountingDao accountingDao = new AccountingDao(dbConn);

            AuthenticationService authenticationService = new AuthenticationService(authenticationDao, passwordService);
            AuthorizationService authorizationService = new AuthorizationService(authorizationDao);
            AccountingService accountingService = new AccountingService(accountingDao);

            if (!userData.isAuthentication()) {
                log.debug("Параметров не передано,выполняется вывод справки");
                cmd.printHelp();
            }

            if (userData.isAuthentication()) {
                log.debug("Передано 2 параметра {} , выполняется аутентификация", userData);
                exitCode = authenticationService.authenticate(userData.getLogin(), userData.getPassword());
            }

            if (userData.isAuthorization()) {
                log.debug("Передано 4 параметра {}, выполняется авторизация", userData);
                if (exitCode == 0) {
                    exitCode = authorizationService.authorize(userData.getLogin(), userData.getRole(), userData.getResource());
                }
            }

            if (userData.isAccounting()) {
                log.debug("Передано 7 параметров {}, выполняется аккаунтинг", userData);
                if (exitCode == 0)
                    exitCode = accountingService.accounting(userData.getLogin(), userData.getDataStart(),
                            userData.getDataEnd(), userData.getVolume());
            }
        } catch (DbException e) {
            log.error("Ошибка БД", e);
            System.exit(255);
        }
        System.exit(exitCode);
    }
}
