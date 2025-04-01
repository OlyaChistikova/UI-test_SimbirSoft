package tests;

import helpers.CustomerDataGenerator;
import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
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
    @Story("Customer Management")
    @Description("Opening Add Customer page")
    public void openAddCustTest() {
        addCustPage = basePage.openAddCust().waitUntilOpen();
        Assert.assertEquals(driver.getCurrentUrl(), ADDCUST.getUrl(), "Current url doesn't match expected");
    }

    /**
     * Тест для добавления пользователя с корректными данными для входа из поставщика данных.
     *
     * @param firstname имя клиента.
     * @param lastname  фамилия клиента.
     * @param postcode  почтовый индекс клиента.
     */
    @Test(priority = 2, description = "Adding customer with correct data", dataProvider = "Valid login data")
    @Step("Добавление пользователя с именем: {firstname} и фамилией: {lastname}")
    @Description("Adding customer with correct data.")
    public final void correctDataAuthTest(String firstname, String lastname, String postcode){
        AddCustAllert addCustAllert = addCustPage.login(firstname, lastname, postcode);

        //Получаем текст алерта и проверяем его
        String allertText = addCustAllert.getAlertText();
        Assert.assertNotNull(allertText, "Alert text should not be null");
        Assert.assertEquals(allertText, "Customer has been successfully added with id: ", "Alert text does not match expected value");
        addCustAllert.accept();
    }

    /**
     * Тест для создания пользователя со сгенерированными данными.
     * Генерирует почтовый индекс, затем получает имя на основе почтового индекса
     * и использует заданную фамилию.
     */
    @Test(priority = 3, description = "Creating customer with generated data")
    @Story("Customer Management")
    @Step("Creating customer with generated data")
    public final void createCustomerWithGeneratedDataTest(){
        String postcode = CustomerDataGenerator.generatePostCode();
        String firstname = CustomerDataGenerator.generateFirstNameFromPostCode(postcode);
        String lastname = PropertyProvider.getProperty("last.name.random");
        AddCustAllert addCustAllert = addCustPage.login(firstname, lastname, postcode);

        //Получаем текст алерта и проверяем его
        String allertText = addCustAllert.getAlertText();
        Assert.assertNotNull(allertText, "Alert text should not be null");
        Assert.assertEquals(allertText, "Customer has been successfully added with id: ", "Alert text does not match expected value");
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
