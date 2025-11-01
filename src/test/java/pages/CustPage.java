package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс страницы списка клиентов.
 * Обеспечивает взаимодействие с элементами на странице списка клиентов.
 */
public class CustPage extends BasePage{

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']")
    WebElement table;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr/td[1]")
    private List<WebElement> firstNames; // Список первых имен клиентов

    public CustPage(final WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для получения списка первых имен клиентов.
     *
     * @return список строк с именами клиентов.
     */
    @Step("Getting list of first names from customer table")
    public List<String> getFirstNames() {
        List<String> names = new ArrayList<>();
        for (WebElement name : firstNames) { // Собираем все имена в список
            names.add(name.getText());
        }
        return names;
    }


    /**
     * Метод для удаления клиента по имени.
     *
     * @param name имя клиента, которого необходимо удалить.
     */
    @Step("Deleting customer with name: {name}")
    public void deleteCustomerByName(String name) {
        driver.findElement(By.xpath("//td[contains(text(), '" + name + "')]/following-sibling::td/button[contains(text(), 'Delete')]")).click();
    }

    /**
     * Метод ожидания загрузки страницы.
     * Ожидает, пока таблица клиентов станет видимой.
     */
    @Step("Waiting until the customer page is loaded")
    public CustPage waitUntilOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(table));
        return this;
    }
}
