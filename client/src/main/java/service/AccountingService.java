package service;

import dao.AccountingDao;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Log4j2
public class AccountingService {
    private AccountingDao accountingDao;

    public AccountingService(AccountingDao accountingDao) {
        this.accountingDao = accountingDao;
    }

    /**
     * Аккаунтинг
     */
    public int accounting(String login, String dateStart, String dateEnd, String volume) throws DbException {
        //Проверки валидности дат и обьема
        if (!isCheckDate(dateStart) || !isCheckDate(dateEnd)
                || !isCheckValue(volume)) {
            log.error("Невалидно {} , {} или {}", volume, dateStart, dateEnd);
            return 5;
        } else {
            accountingDao.addAccounting(login, dateStart, dateEnd, volume);
            log.debug("Данные валидны и успешно добавлены в БД");
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
            log.error("Дата не может быть проверена", e);
            return false;
        }
    }
}