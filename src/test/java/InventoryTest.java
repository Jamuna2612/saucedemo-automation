


import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTest extends BaseTest {

    @Test
    public void addMultipleProductsAndSortTest() {
        test = extent.createTest("Add Multiple Products and Sorting Test");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        test.pass("Logged in successfully");

        InventoryPage inventoryPage = new InventoryPage(driver);

        // Add single product
       /* inventoryPage.addItemToCart(0);

        // Add single product by name
        inventoryPage.addItemToCartByName("Sauce Labs Onesie");*/

        // Add all products to cart
        inventoryPage.addAllProductsToCart();
        test.pass("Added all products to cart");

        inventoryPage.goToCart();
        test.pass("Navigated to cart page");

        // Navigate back to inventory to test sorting
        driver.navigate().back();

        // Test sorting A → Z
        inventoryPage.sortProducts("az");
        List<String> namesAZ = inventoryPage.getProductNames();
        Assert.assertTrue(namesAZ.get(0).compareTo(namesAZ.get(namesAZ.size() - 1)) <= 0);
        test.pass("Verified product sorting A → Z");

        // Test sorting Z → A
        inventoryPage.sortProducts("za");
        List<String> namesZA = inventoryPage.getProductNames();
        for (int i = 0; i < namesZA.size() - 1; i++) {
            String current = namesZA.get(i);
            String next = namesZA.get(i + 1);
            Assert.assertTrue(
                    current.compareTo(next) >= 0,
                    "List is not sorted in descending order at index " + i + ": " + current + " < " + next
            );
        }
        test.pass("Verified product sorting Z → A");

        // Test sorting price Low → High
        inventoryPage.sortProducts("lohi");
        List<Double> pricesLoHi = inventoryPage.getProductPrices();
        Assert.assertTrue(pricesLoHi.get(0) <= pricesLoHi.get(pricesLoHi.size() - 1));
        test.pass("Verified product sorting Price Low → High");

        // Test sorting price High → Low
        inventoryPage.sortProducts("hilo");
        List<Double> pricesHiLo = inventoryPage.getProductPrices();
        Assert.assertTrue(pricesHiLo.get(0) >= pricesHiLo.get(pricesHiLo.size() - 1));
        test.pass("Verified product sorting Price High → Low");


        // This will verify both A→Z and Z→A sorting with full-list comparison

    /*    // Step 2: Sort products A→Z
        inventoryPage.sortProducts("Name (A to Z)");
        List<String> names1AZ = inventoryPage.getProductNames();

        // Copy and sort locally for expected A→Z order
        List<String> expectedAZ = new ArrayList<>(namesAZ);
        Collections.sort(expectedAZ);

        try {
            Assert.assertEquals(namesAZ, expectedAZ, " Product names are not sorted A → Z correctly");
            test.pass("✅ Products sorted correctly A → Z");
        } catch (AssertionError e) {
            test.fail(" A → Z sorting failed");
            throw e; // so screenshot is captured in BaseTest
        }

        // Step 3: Sort products Z→A
        inventoryPage.sortProducts("Name (Z to A)");
        List<String> names1ZA = inventoryPage.getProductNames();

        // Copy and sort locally for expected Z→A order
        List<String> expectedZA = new ArrayList<>(namesZA);
        Collections.sort(expectedZA, Collections.reverseOrder());

        try {
            Assert.assertEquals(namesZA, expectedZA, "Product names are not sorted Z → A correctly");
            test.pass("✅ Products sorted correctly Z → A");
        } catch (AssertionError e) {
            test.fail(" Z → A sorting failed");
            throw e; // so screenshot is captured in BaseTest
        }*/
    }
}

