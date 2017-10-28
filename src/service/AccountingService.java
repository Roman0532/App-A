package service;

import domain.Accouning;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AccountingService {
    /**
     * Аккаунтинг
     */
    public static void accounting(String dateStart, String dateEnd, String volume, UserDataService userDataService) throws NoSuchAlgorithmException {
        //Проверки валидности дат и обьема
        if (!isCheckDate(dateStart) || !isCheckDate(dateEnd)
                || !isCheckValue(volume)) {
            System.exit(5);
        } else {
            Accouning accouning = new Accouning(userDataService.getLogin(),userDataService.getResource(),dateStart,dateEnd,volume);
            ArrayList<Accouning> Data = new ArrayList<>();
            Data.add(accouning);
            System.exit(0);
        }
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
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}