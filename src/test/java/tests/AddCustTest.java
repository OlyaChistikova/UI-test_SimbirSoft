package tests;

import helpers.CustomerDataGenerator;
import helpers.PropertyProvider;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.AddCustAllert;
import pages.AddCustPage;
import pages.BasePage;


import static helpers.EndPoint.ADDCUST;

public class AddCustTest extends BaseTest{

    private AddCustPage addCustPage;
    private PropertyProvider propertyProvider;

    @DataProvider(name = "Valid login data")
    public Object[][] dtmethod(){
        return new Object[][]{
                {PropertyProvider.getInstance().getProperty("first.name.example"),
                PropertyProvider.getInstance().getProperty("last.name.example"),
                PropertyProvider.getInstance().getProperty("post.code.example")}
        };
    }

    @BeforeClass
    public final void setup(){
        basePage = new BasePage(driver);
        propertyProvider = PropertyProvider.getInstance();
    }

    @Test(priority = 1, description = "Opening Add Customer page")
    public void testOpenAddCust() {
        addCustPage = basePage.openAddCust();
        addCustPage.waitUntilOpen();
        Assert.assertEquals(driver.getCurrentUrl(), ADDCUST.getUrl(), "Current url doesn't match expected");
    }

    @Test(priority = 2, description = "Authorize with correct data", dataProvider = "Valid login data")
    public final void incorrectDataAuthTest(String firstname, String lastname, String postcode){
        AddCustAllert addCustAllert = addCustPage.login(firstname, lastname, postcode);
        addCustAllert.accept();
    }

    @Test(priority = 3, description = "Creating customer with generated data")
    public final void createCustomerWithGeneratedData(){
        String postcode = CustomerDataGenerator.generatePostCode();
        String firstname = CustomerDataGenerator.generateFirstNameFromPostCode(postcode);
        String lastname = propertyProvider.getProperty("last.name.random");
        AddCustAllert addCustAllert = addCustPage.login(firstname, lastname, postcode);
//настроить ассерты
        if(addCustAllert != null){
            addCustAllert.accept();
        } else {
            System.out.println("AddCusrAllert is not initialized");
        }


    }

    @AfterMethod
    public final void clearCookies(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
