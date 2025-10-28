package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {

    WebDriver driver;
    private WebDriverWait wait;

    private By addToCartButtons = By.cssSelector("button.btn_inventory");
    private By cartIcon = By.className("shopping_cart_link");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By sortDropdown = By.cssSelector(".product_sort_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));

    }

    // Add single product by index
    public void addItemToCart(int index) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (index >= 0 && index < buttons.size()) {
            buttons.get(index).click();
        } else {
            throw new IllegalArgumentException("Invalid product index: " + index);
        }
    }

    // Add single product by name
    public void addItemToCartByName(String productName) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        List<WebElement> names = driver.findElements(productNames);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(productName)) {
                buttons.get(i).click();
            }
        }
    }

    // Add all products to cart
    public void addAllProductsToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        for (WebElement btn : buttons) {
            btn.click();
        }
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    // Select sorting option: "az", "za", "lohi", "hilo"
    public void sortProducts(String sortOption) {
        WebElement dropdownElement = driver.findElement(By.cssSelector(".product_sort_container"));
        Select dropdown = new Select(dropdownElement);

        boolean optionSelected = false;

        try {
            // Try selecting by visible text first (e.g., "Name (A to Z)")
            dropdown.selectByVisibleText(sortOption);
            optionSelected = true;
            System.out.println("Selected by visible text: " + sortOption);
        } catch (NoSuchElementException e) {
            try {
                // Fallback: try selecting by value (e.g., "az", "za", "lohi", "hilo")
                dropdown.selectByValue(sortOption);
                optionSelected = true;
                System.out.println("Selected by value: " + sortOption);
            } catch (NoSuchElementException ex) {
                throw new RuntimeException("Invalid sort option: " + sortOption);
            }
        }

        if (optionSelected) {
            // Wait for the sort to take effect (list refresh)
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name"))
            );
        }
    }

    // Get product names
    public List<String> getProductNames() {
        List<WebElement> names = driver.findElements(productNames);
        return names.stream().map(WebElement::getText).toList();
    }

    // Get product prices
    public List<Double> getProductPrices() {
        List<WebElement> prices = driver.findElements(productPrices);
        return prices.stream()
                .map(p -> Double.parseDouble(p.getText().replace("$", "")))
                .toList();
    }
}
