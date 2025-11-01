package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Класс для выполнения действий с клиентами, таких как вычисление средней длины имен,
 * нахождение имени, близкого к средней длине, и удаление клиентов.
 */
public class CustomerActions {

    /**
     * Метод для вычисления средней длины имен клиентов.
     *
     * @param names список имен клиентов.
     * @return объект OptionalDouble, содержащий среднюю длину имен, если список не пуст.
     */
    public OptionalDouble calculateAverageNameLength(List<String> names) {
        return names.stream()
                .mapToInt(String::length)
                .average();
    }

    /**
     * Метод для нахождения имени, длина которого ближе всего к заданной средней длине.
     *
     * Если в списке есть несколько имен с одинаковой длиной, метод вернет
     * первое найденное имя, длина которого соответствует ближайшей к средней.
     *
     * @param names список имен клиентов.
     * @param averageLength средняя длина имен, к которой будет производиться поиск.
     * @return имя клиента, длина которого ближе всего к средней, или null, если список имен пуст.
     */
    public String findClosestNameToAverageLength(List<String> names, double averageLength) {
        return names.stream()
                .min((name1, name2) -> {
                    double diff1 = Math.abs(name1.length() - averageLength);
                    double diff2 = Math.abs(name2.length() - averageLength);
                    return Double.compare(diff1, diff2);
                }).orElse(null);
    }

    /**
     * Проверка, был ли клиент успешно удален.
     *
     * @param actualFirstNames список актуальных имен клиентов после удаления.
     * @param name имя клиента для проверки на наличие в списке.
     * @return true, если клиент был успешно удален, иначе false.
     */
    public boolean isCustomerDeleted(List<String> actualFirstNames, String name) {
        return !actualFirstNames.contains(name);
    }
}