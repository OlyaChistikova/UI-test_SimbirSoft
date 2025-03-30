package tests;

import helpers.PropertyProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddCustPage;
import pages.BasePage;
import pages.CustPage;

import java.util.List;
import java.util.OptionalDouble;

import static helpers.EndPoint.CASTLIST;

public class DeleteCustTest extends BaseTest{

    protected CustPage custPage;
    protected PropertyProvider propertyProvider;

    @BeforeClass
    public void setup(){
        basePage = new BasePage(driver);
        propertyProvider = PropertyProvider.getInstance();
    }

    @Test(priority = 1, description = "Opening Customer page")
    public void testOpenCust() throws InterruptedException {
        custPage = basePage.openCustList();
        custPage.waitUntilOpen();
        Assert.assertEquals(driver.getCurrentUrl(), CASTLIST.getUrl(), "Current url doesn't match expected");
        Thread.sleep(5000);
    }


    @Test(priority = 2)
    public void deleteCustomerByAverageNameLength() {
        List<String> actualFirstNames = custPage.getFirstNames();

        OptionalDouble averageLengthOpt = actualFirstNames.stream()
                .mapToInt(String::length)
                .average(); // Вычисляем среднее

        if (!averageLengthOpt.isPresent()) {
            Assert.fail("No customer names found."); // Проверяем, есть ли имена
        }

        double averageLength = averageLengthOpt.getAsDouble(); // Получаем среднюю длину

        // Находим имя, длина которого ближе всего к средней
        String closestName = actualFirstNames.stream()
                .min((name1, name2) -> {
                    double diff1 = Math.abs(name1.length() - averageLength);
                    double diff2 = Math.abs(name2.length() - averageLength);
                    return Double.compare(diff1, diff2);
                }).orElse(null);
        if (closestName != null) {
            System.out.println("Deleting customer: " + closestName);
            // Удаляем клиента (предполагается, что элемент удаления доступен)
            driver.findElement(By.xpath("//td[contains(text(), '"
                    + closestName + "')]/following-sibling::td/button[contains(text(), 'Delete')]")).click();
            // Проверяем, что клиент был удален
            // Снова открываем список клиентов
            actualFirstNames = custPage.getFirstNames(); // Обновляем список имен
            Assert.assertFalse(actualFirstNames.contains(closestName), "Customer was not deleted successfully!");
        } else {
            Assert.fail("Could not find a customer to delete based on average length.");
        }
    }

    @AfterMethod
    public void clearCookies(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
