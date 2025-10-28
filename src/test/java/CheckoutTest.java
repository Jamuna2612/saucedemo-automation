

import org.example.pages.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckoutTest extends BaseTest {

    @Test
    public void completeCheckoutTest() {
        test = extent.createTest("Complete Checkout Test");

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        test.pass("Logged in with standard_user");

        // Step 2: Add product to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addAllProductsToCart();

        test.pass("Added product to cart");
        inventoryPage.goToCart();
        test.pass("Navigated to Cart page");

        // Step 3: Checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        test.pass("Clicked Checkout");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInfo("John", "Doe", "12345");
        test.pass("Entered checkout information");
        checkoutPage.clickContinue();
        test.pass("Clicked Continue on Checkout Page");
        checkoutPage.clickFinish();
        test.pass("Clicked Finish");

        // Step 4: Verify order completion
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        String message = completePage.getCompletionMessage();
        try {
            Assert.assertEquals(message, "Thank you for your order!");
            test.pass("Order completed successfully: " + message);
        } catch (AssertionError e) {
            test.fail("Order completion failed: " + message);
            throw e; // so screenshot is captured by BaseTest
        }
        completePage.homeView();
    }

}
