package tests;

import helpers.PropertyProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import pages.AddCustPage;
import pages.BasePage;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;

    BasePage basePage;

    @BeforeClass
    public void init(final ITestContext context){
        String browserName = PropertyProvider.getInstance().getProperty("browser.name");
        int pageLoadTimeOut = Integer.parseInt(PropertyProvider.getInstance().getProperty("page.load.timeout"));
        WebDriverManager.getInstance(browserName).setup();

        switch (browserName) {
            case "chrome" -> driver = new ChromeDriver(new ChromeOptions()
                    .addArguments("--remote-allow-origins=*")
                    .addArguments("--start-maximized")
                    .addArguments("--disable-popup-blocking"));
            default -> throw new IllegalStateException("Unexpected value: "
                    + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(pageLoadTimeOut));

        context.setAttribute("driver", driver);
        String webUrl = PropertyProvider.getInstance().getProperty("web.url");
        driver.get(webUrl);
        basePage = new BasePage(driver);
    }

    @AfterClass
    public final void tearDown() {
        if (driver != null){
            driver.quit();
        }
    }
}