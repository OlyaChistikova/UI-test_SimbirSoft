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

/**
 * Базовый класс тестов, инициализирует драйвер и общие настройки для тестов.
 */
public class BaseTest {

    WebDriver driver;
    BasePage basePage;

    /**
     * Метод инициализации, который запускается перед выполнением тестов.
     * Настраивает веб-драйвер и загружает целевой URL.
     *
     * @param context контекст теста, используемый для передачи драйвера между тестами.
     */
    @BeforeClass
    public void init(final ITestContext context){
        String browserName = PropertyProvider.getProperty("browser.name");
        int pageLoadTimeOut = Integer.parseInt(PropertyProvider.getProperty("page.load.timeout"));
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
        String webUrl = PropertyProvider.getProperty("web.url");
        driver.get(webUrl);
        basePage = new BasePage(driver);
    }

    /**
     * Метод, выполняемый после завершения всех тестов в классе.
     * Закрывает веб-драйвер, если он инициализирован.
     */
    @AfterClass
    public final void tearDown() {
        if (driver != null){
            driver.quit();
        }
    }
}