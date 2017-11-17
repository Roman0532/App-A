package service;

import dao.AccountingDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountingService {
    private static final Logger logger = LogManager.getLogger(AccountingService.class);

    /**
     * Аккаунтинг
     */
    public static int accounting(String dateStart, String dateEnd, String volume,
                                 UserData userData) throws NoSuchAlgorithmException, SQLException {
        //Проверки валидности дат и обьема
        if (!isCheckDate(dateStart) || !isCheckDate(dateEnd)
                || !isCheckValue(volume)) {
            logger.error("Невалидно" + " " + volume + " " + dateStart + "или " + dateEnd);
            return 5;
        } else {
            AccountingDao.addAccounting(userData);
        }
        return 0;
    }

    /**
     * Проверка валидности обьема
     */
    private static boolean isCheckValue(String volume) {
        return volume.matches("^-?\\d+$");
    }

    /**
     * Проверка валидности даты
     */
    private static boolean isCheckDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            logger.error("Дата не может быть проверена" + e);
            return false;
        }
    }
}