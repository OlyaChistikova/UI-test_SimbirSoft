package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v132.runtime.model.StackTrace;

public class AddCustAllert {

    private final WebDriver driver;


    public AddCustAllert(WebDriver driver) {
        this.driver = driver;

    }

    public void accept(){
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
    }

    public String getAlertText(){
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }
    public void waitForAlert(){
        try {
            driver.switchTo().alert();
        } catch (NoAlertPresentException e){
            System.out.println("No alert present");
        }
    }
}
