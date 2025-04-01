package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CustPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static helpers.EndPoint.CASTLIST;

/**
 * Тестовый класс для сортировки клиентов.
 */
public class SortCustTest extends BaseTest {

    protected CustPage custPage;

    /**
     * Метод настройки, который инициализирует необходимые компоненты перед выполнением тестов.
     */
    @BeforeClass
    @Description("Initial setup for the customer page before running tests.")
    public void setup() {
        basePage = new BasePage(driver);
    }

    /**
     * Тест для открытия страницы клиентов.
     * Проверяет, что текущий URL соответствует ожидаемому.
     */
    @Test(priority = 1, description = "Opening Customer page")
    @Description("Verifying that the current URL matches the expected customer page URL.")
    public void openCustTest() {
        custPage = basePage.openCustList().waitUntilOpen();
        Assert.assertEquals(driver.getCurrentUrl(), CASTLIST.getUrl(), "Current url doesn't match expected");
    }

    /**
     * Тест для сортировки пользователей по первым именам.
     * Сравнивает полученный список имен с отсортированным списком.
     */
    @Test(priority = 2, description = "Sort Customers")
    @Description("Retrieving the list of first names and verifying they are sorted correctly.")
    public void SortCustomersByFirstNameSuccessfulTest() {
        List<String> actualFirstNames = custPage.getFirstNames(); // Получаем список первых имен
        Assert.assertNotNull(actualFirstNames, "The list of first names should not be null!"); // Проверка на null
        Assert.assertFalse(actualFirstNames.isEmpty(), "The list of first names should not be empty!"); // Проверка на пустоту
        List<String> sortedFirstNames = new ArrayList<>(actualFirstNames); // Создаем копию для сортировки
        Collections.sort(sortedFirstNames); // Сортируем список
        // Сравниваем два списка
        Assert.assertEquals(actualFirstNames, sortedFirstNames, "First names are not sorted correctly!");
    }

    /**
     * Метод, выполняемый после каждого теста, который очищает cookies и обновляет страницу.
     */
    @AfterMethod
    @Description("This method clears the cookies and refreshes the page after each test.")
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
