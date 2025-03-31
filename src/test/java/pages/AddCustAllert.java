package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v132.runtime.model.StackTrace;

/**
 * Класс для управления алертами в веб-приложении.
 */
public class AddCustAllert {

    private final WebDriver driver;


    public AddCustAllert(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Принимает (закрывает) всплывающее сообщение (алерт).
     * Перед закрытием алерта ожидает его появления.
     */
    public void accept() {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
    }

    /**
     * Возвращает текст алерта.
     *
     * @return текст алерта.
     */
//посмотреть используется ли
    public String getAlertText() {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    /**
     * Ожидает появления алерта.
     *
     * Если алерт отсутствует, выводит сообщение в консоль.
     */
//тоже надо будет переделать
    public void waitForAlert() {
        try {
            driver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present");
        }
    }
}
