package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helpers.Wait.waitThenClick;

public class BasePage {
    protected final WebDriver driver;

    @FindBy(xpath = "//div[@class='mainHeading']")
    WebElement appLogo;

    @FindBy(xpath = "//button[@class='btn btn-lg tab']")
    WebElement btnAddCust;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[1]/button[3]") //переделать локатор
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

    @Step("Open Add Customer")
    public final AddCustPage openAddCust(){
        waitThenClick(driver, btnAddCust);
        return new AddCustPage(driver);
    }

//дописать класс customerList
    @Step("Open Customer List")
    public final CustPage openCustList(){
        waitThenClick(driver, btnCustList);
        return new CustPage(driver);
    }

    public void waitUntilOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(appLogo));
    }


}
