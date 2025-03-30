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
import static helpers.Wait.waitUntilVisible;

public class AddCustPage extends BasePage{

    @FindBy(xpath = "//form")
    WebElement log;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement first_name_input;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement last_name_input;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    WebElement post_code_input;

    @FindBy(xpath = "//button[@class='btn btn-default']")
    WebElement submit;


    public AddCustPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



    @Step("Autorize with first name: {first_name}, last name: {last_name}, post code: {post_code}")
    public final AddCustAllert login(final String first_name, final String last_name, final String post_code){
        first_name_input.sendKeys(first_name);
        last_name_input.sendKeys(last_name);
        post_code_input.sendKeys(post_code);
        waitThenClick(driver, submit);
        return new AddCustAllert(driver);
    }

    public void waitUntilOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(log));
    }

}
