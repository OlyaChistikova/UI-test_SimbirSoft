package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CustPage extends BasePage{

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']")
    WebElement table;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr/td[1]")
    private List<WebElement> firstNames; // Список первых имен клиентов

    public CustPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getFirstNames() {
        List<String> names = new ArrayList<>();
        for (WebElement name : firstNames) { // Собираем все имена в список
            names.add(name.getText());
        }
        return names;
    }

    public void waitUntilOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(table));
    }
}
