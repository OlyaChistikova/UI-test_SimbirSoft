package tests;

import helpers.PropertyProvider;
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

public class SortCustTest extends BaseTest {

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

    @Test(priority = 2, description = "Sort Customers")
    public void testSortCustomersByFirstName() {
        List<String> actualFirstNames = custPage.getFirstNames(); // Получаем список первых имен
        List<String> sortedFirstNames = new ArrayList<>(actualFirstNames); // Создаем копию для сортировки
        Collections.sort(sortedFirstNames); // Сортируем список
//написать assert для списка
        for (String name : sortedFirstNames){
            System.out.println(name);
        }

        // Сравниваем два списка
        //Assert.assertEquals(actualFirstNames, sortedFirstNames, "First names are not sorted correctly!");
    }

    @AfterMethod
    public void clearCookies(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
