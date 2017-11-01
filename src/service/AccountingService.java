package service;

import domain.Accouning;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AccountingService {
    /**
     * Аккаунтинг
     */
    public static void accounting(String dateStart, String dateEnd, String volume,
                                  UserData userData, ArrayList<Accouning> data) throws NoSuchAlgorithmException {
        //Проверки валидности дат и обьема
        if (!isCheckDate(dateStart) || !isCheckDate(dateEnd)
                || !isCheckValue(volume)) {
            System.exit(5);
        } else {
            data.add(new Accouning(userData.getLogin(), userData.getResource(),
                    dateStart, dateEnd, volume));
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}