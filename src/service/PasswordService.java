package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class PasswordService {
    /**
     * Генерация хэша пароля
     */
    private String generateHashPassword(String str) throws NoSuchAlgorithmException {
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
        //Перевод в 16 формат
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));
        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }

    /**
     * Проверка совпадает ли переданный пароль с паролем пользователя
     */

    boolean isRightPass(String password, String userPassword,
                        String salt) throws NoSuchAlgorithmException {
        return generateHashPassword(generateHashPassword(password) + salt).equals(userPassword);
    }
}