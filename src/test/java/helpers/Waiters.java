package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс-утилита для явных ожиданий с использованием WebDriver.
 * Предоставляет методы для ожидания, пока элемент станет видимым или кликабельным.
 */
public class Waiters {

    private static Waiters instance;

    /**
     * Получает экземпляр класса Waiters.
     * Если экземпляр еще не создан, создается новый.
     *
     * @param driver веб-драйвер, используемый для ожиданий.
     * @return единственный экземпляр Waiters.
     */
    private static Waiters getInstance(WebDriver driver){
        if (instance == null){
            instance = new Waiters(driver);
        }
        return instance;
    }

    private final WebDriverWait wait;

    /**
     * Конструктор класса Waiters.
     * Инициализирует WebDriverWait с заданным временем ожидания.
     *
     * @param driver веб-драйвер, используемый для ожиданий.
     */
    public Waiters(WebDriver driver){
        int timeout = Integer.parseInt(PropertyProvider.getProperty("explicit.timeout"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    /**
     * Метод ожидания, пока элемент не станет кликабельным, и затем выполняет клик по нему.
     *
     * @param driver веб-драйвер, используемый для ожидания.
     * @param webElement элемент, по которому требуется выполнить клик.
     */
    public static void waitThenClick(WebDriver driver, final WebElement webElement){
        getInstance(driver).wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }
}
