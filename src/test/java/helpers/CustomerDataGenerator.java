package helpers;

import java.util.Random;

/**
 * Класс-генератор данных для клиентов.
 * Предоставляет методы для генерации postcode и firstname.
 */
public class CustomerDataGenerator {

    /**
     * Генерирует postcode, состоящий из 10 цифр.
     *
     * @return строку, представляющую случайный почтовый индекс.
     */
    public static String generatePostCode(){
        Random random = new Random();
        StringBuilder postCode = new StringBuilder();

        for (int i = 0; i < 10; i++){
            postCode.append(random.nextInt(10));
        }
        return postCode.toString();
    }

    /**
     * Генерирует имя клиента на основе почтового индекса.
     * Каждая пара цифр из почтового индекса преобразуется в букву на основе её значения.
     *
     * @param postcode строка, представляющая почтовый индекс.
     * @return строку, представляющую сгенерированное имя клиента.
     */
    public static String generateFirstNameFromPostCode(String postcode){
        StringBuilder firstName = new StringBuilder();

        for (int i = 0; i < postcode.length(); i += 2) {
            String pair = postcode.substring(i, Math.min(i + 2, postcode.length()));
            int number = Integer.parseInt(pair);
            char letter = (char) ('a' + (number % 26));
            firstName.append(letter);
        }

        return firstName.toString();
    }
}
