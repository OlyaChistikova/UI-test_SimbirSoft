package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * Класс для управления алертами в веб-приложении.
 */
public class AddCustAllert extends BasePage{

    public AddCustAllert(WebDriver driver) {
        super(driver);
    }

    /**
     * Принимает (закрывает) всплывающее сообщение (алерт).
     * Перед закрытием алерта ожидает его появления.
     */
    @Step("Accepting alert")
    public void accept() {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /**
     * Возвращает текст алерта.
     *
     * @return текст алерта.
     */
    @Step("Getting alert text")
    public String getAlertText() {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    /**
     * Ожидает появления алерта.
     *
     * @return allert
     * @throws NoAlertPresentException если алерт отсутствует
     */
    @Step("Waiting for alert")
    public void waitForAlert() {
        try {
            driver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            throw new NoAlertPresentException("No allert present");
        }
    }
}
