package tests;

import helpers.CustomerDataGenerator;
import helpers.PropertyProvider;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddCustAllert;
import pages.AddCustPage;
import pages.BasePage;

import static helpers.EndPoint.ADDCUST;

/**
 * Тестовый класс для добавления клиента.
 */
public class AddCustTest extends BaseTest{

    private AddCustPage addCustPage;

    /**
     * Поставщик данных, который предоставляет корректные данные для входа в тесты.
     *
     * @return двумерный массив с примерами корректного firstname, lastname и postcode.
     */
    @DataProvider(name = "Valid login data")
    public Object[][] dtmethod(){
        return new Object[][]{
                {PropertyProvider.getProperty("first.name.example"),
                PropertyProvider.getProperty("last.name.example"),
                PropertyProvider.getProperty("post.code.example")}
        };
    }

    /**
     * Метод, который инициализирует компоненты перед выполнением тестового класса.
     */
    @BeforeClass
    public final void setup(){
        basePage = new BasePage(driver);
    }

    /**
     * Тест для открытия страницы добавления клиента.
     * Проверяет, что текущий URL совпадает с ожидаемым URL.
     */
    @Test(priority = 1, description = "Opening Add Customer page")
    public void testOpenAddCust() {
        addCustPage = basePage.openAddCust();
        addCustPage.waitUntilOpen();
        Assert.assertEquals(driver.getCurrentUrl(), ADDCUST.getUrl(), "Current url doesn't match expected");
    }

    /**
     * Тест для авторизации с корректными данными для входа с использованием данных из поставщика данных.
     *
     * @param firstname имя клиента.
     * @param lastname  фамилия клиента.
     * @param postcode  почтовый индекс клиента.
     */
    @Test(priority = 2, description = "Authorize with correct data", dataProvider = "Valid login data")
    public final void correctDataAuthTest(String firstname, String lastname, String postcode){
        AddCustAllert addCustAllert = addCustPage.login(firstname, lastname, postcode);
        Assert.assertNotNull(addCustAllert, "AddCustAllert sould not be null");
        addCustAllert.accept();
    }

    /**
     * Тест для создания клиента со сгенерированными данными.
     * Генерирует почтовый индекс, затем получает имя на основе почтового индекса
     * и использует заданную фамилию.
     */
    @Test(priority = 3, description = "Creating customer with generated data")
    public final void createCustomerWithGeneratedData(){
        String postcode = CustomerDataGenerator.generatePostCode();
        String firstname = CustomerDataGenerator.generateFirstNameFromPostCode(postcode);
        String lastname = PropertyProvider.getProperty("last.name.random");
        AddCustAllert addCustAllert = addCustPage.login(firstname, lastname, postcode);

        /**
         * Проверяем, что assertNotNull успешно инициализирован
         */
        Assert.assertNotNull(addCustAllert, "AddCustAllert sould not be null");
        addCustAllert.accept();
    }

    /**
     * Метод, выполняемый после каждого теста, который очищает все cookies и обновляет страницу.
     */
    @AfterMethod
    public final void clearCookies(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
