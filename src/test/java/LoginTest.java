


import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "success", ""},
                {"wrong_user", "wrong_pass", "error", "Username and password do not match"},
                {"", "secret_sauce", "error", "Username is required"},
                {"standard_user", "", "error", "Password is required"},
                {"", "", "error", "Username is required"},
                {"locked_out_user", "secret_sauce", "error", "Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult, String expectedMessage) {
        test = extent.createTest("Login Test - " + username + "/" + password);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expectedResult.equals("success")) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
            Assert.assertEquals(driver.getTitle(), "Swag Labs");
            test.pass("Valid login successful");
        } else {
            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(actualError.contains(expectedMessage));
            test.pass("Error message validated: " + actualError);
        }
    }
}
