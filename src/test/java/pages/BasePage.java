package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helpers.Waiters.waitThenClick;

/**
 * Базовый класс страницы для всех страниц приложения.
 * Обеспечивает общие методы и элементы, используемые на страницах.
 */
public class BasePage {
    protected final WebDriver driver;

    @FindBy(xpath = "//div[@class='mainHeading']")
    WebElement appLogo;

    @FindBy(xpath = "//button[@class='btn btn-lg tab']")
    WebElement btnAddCust;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    WebElement btnCustList;

    public BasePage(WebDriver driver){
        try{
            PageFactory.initElements(driver, this);
            this.driver = driver;
        }
        catch (IllegalStateException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для открытия страницы добавления клиента.
     *
     * @return объект AddCustPage, представляющий страницу добавления клиента.
     */
    @Step("Open Add Customer")
    public final AddCustPage openAddCust(){
        waitThenClick(driver, btnAddCust);
        return new AddCustPage(driver);
    }

    /**
     * Метод для открытия страницы списка клиентов.
     *
     * @return объект CustPage, представляющий страницу списка клиентов.
     */
    @Step("Open Customer List")
    public final CustPage openCustList(){
        waitThenClick(driver, btnCustList);
        return new CustPage(driver);
    }

    /**
     * Метод ожидания загрузки страницы.
     * Ожидает, пока логотип приложения станет видимым.
     */
    public void waitUntilOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(appLogo));
    }


}
