package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordService {
    /**
     * Генерация хэша пароля
     */
    private static String generateHashPassword(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        //Создание обьекта для использования алгоритма
        messageDigest = MessageDigest.getInstance("MD5");
        //Сброс
        messageDigest.reset();
        //Обновляет digest используя указанный байт
        messageDigest.update(str.getBytes());

        byte[] digest;

        digest = messageDigest.digest();

        /*Переводим представление знаковой величины в BigInteger
        -1 для отрицательных, 0 для 0, 1 для положительных)*/
        BigInteger bigInt = new BigInteger(1, digest);
        //Выбирается в какой формат перевести
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }

    /**
     * Хэш пароля + соль
     */
    private static String getHash(String password, String salt) throws NoSuchAlgorithmException {
        return generateHashPassword(generateHashPassword((password)) + salt);
    }

    /**
     * Генерация соли
     */
    public static String generateSalt() {
        //Получить 32 битное значение
        Random random = new SecureRandom();
        byte[] s = new byte[32];
        random.nextBytes(s);

        /*Переводим представление знаковой величины в BigInteger
        -1 для отрицательных, 0 для 0, 1 для положительных)*/
        BigInteger bigInt = new BigInteger(1, s);
        //16-формат
        return bigInt.toString(16);
    }

    /**
     * Проверка совпадает ли переданный хэшированный пароль с паролем пользователя
     */
    static boolean isRightPass(String password, String userPassword, String salt) throws NoSuchAlgorithmException {
        return PasswordService.getHash(password, salt).equals(PasswordService.getHash(userPassword, salt));
    }
}