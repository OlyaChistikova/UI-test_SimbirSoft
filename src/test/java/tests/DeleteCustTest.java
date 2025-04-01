package tests;

import helpers.CustomerActions;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CustPage;

import java.util.List;
import java.util.OptionalDouble;

import static helpers.EndPoint.CASTLIST;

/**
 * Тест для удаления клиента.
 */
public class DeleteCustTest extends BaseTest{

    protected CustPage custPage;
    protected CustomerActions customerActions;

    /**
     * Метод, который инициализирует необходимые компоненты перед выполнением тестов.
     */
    @BeforeClass
    @Description("Initial setup for customer actions and base page.")
    public void setup(){
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
     * Тест для удаления клиента с именем, длина которого ближе всего к средней длине имен клиентов.
     */
    @Test(priority = 2)
    @Description("Deletes a customer whose name length is closest to the average name length of existing customers.")
    public void deleteCustomerByAverageNameLengthSuccessfulTest() {
        List<String> actualFirstNames = custPage.getFirstNames();

        /**
         Вычисляем среднюю длину имен
         */
        OptionalDouble averageLengthOpt = customerActions.calculateAverageNameLength(actualFirstNames);

        if (!averageLengthOpt.isPresent()) {
            Assert.fail("No customer names found."); // Проверяем, есть ли имена
        }

        double averageLength = averageLengthOpt.getAsDouble(); // Получаем среднюю длину

        // Находим имя, длина которого ближе всего к средней
        String closestName = customerActions.findClosestNameToAverageLength(actualFirstNames, averageLength);

        if (closestName != null) {
            custPage.deleteCustomerByName(closestName); // Удаляем клиента
            actualFirstNames = custPage.getFirstNames(); // Обновляем список имен

            Assert.assertTrue(customerActions.isCustomerDeleted(actualFirstNames, closestName), "Customer was not deleted successfully!");
        } else {
            Assert.fail("Could not find a customer to delete based on average length.");
        }
    }

    /**
     * Метод, выполняемый после каждого теста, который очищает cookies и обновляет страницу.
     */
    @AfterMethod
    @Description("This method clears the cookies and refreshes the page after each test.")
    public void clearCookies(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
