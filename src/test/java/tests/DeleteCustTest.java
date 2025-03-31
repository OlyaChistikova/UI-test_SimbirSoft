package tests;

import helpers.CustomerActions;

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
    private CustomerActions customerActions;

    /**
     * Метод, который инициализирует необходимые компоненты перед выполнением тестов.
     */
    @BeforeClass
    public void setup(){
        basePage = new BasePage(driver);
        customerActions = new CustomerActions(driver);
    }

    /**
     * Тест для открытия страницы клиентов.
     * Проверяет, что текущий URL соответствует ожидаемому.
     */
    @Test(priority = 1, description = "Opening Customer page")
    public void testOpenCust() {
        custPage = basePage.openCustList();
        custPage.waitUntilOpen();
        Assert.assertEquals(driver.getCurrentUrl(), CASTLIST.getUrl(), "Current url doesn't match expected");
    }
//разбить тест
    /**
     * Тест для удаления клиента с именем, длина которого ближе всего к средней длине имен клиентов.
     */
    @Test(priority = 2)
    public void deleteCustomerByAverageNameLength() {
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
            customerActions.deleteCustomerByName(closestName); // Удаляем клиента
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
    public void clearCookies(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
