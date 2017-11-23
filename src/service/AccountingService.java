package service;

import dao.AccountingDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class AccountingService {
    private static final Logger logger = LogManager.getLogger(AccountingService.class.getName());
    private AccountingDao accountingDao;

    public AccountingService(AccountingDao accountingDao) {
        this.accountingDao = accountingDao;
    }

    /**
     * Аккаунтинг
     */
    public int accounting(String dateStart, String dateEnd, String volume,
                          UserData userData){
        //Проверки валидности дат и обьема
        if (!isCheckDate(dateStart) || !isCheckDate(dateEnd)
                || !isCheckValue(volume)) {
            logger.error("Невалидно {} , {} или {}", volume, dateStart, dateEnd);
            return 5;
        } else {
            accountingDao.addAccounting(userData);
            logger.debug("Данные валидны и успешно добавлены в БД");
            return 0;
        }
    }

    /**
     * Проверка валидности обьема
     */
    private boolean isCheckValue(String volume) {
        return volume.matches("^-?\\d+$");
    }

    /**
     * Проверка валидности даты
     */
    private boolean isCheckDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            logger.error("Дата не может быть проверена", e);
            return false;
        }
    }
}