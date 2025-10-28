package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

        WebDriver driver;

        private By checkoutButton = By.id("checkout");

        public CartPage(WebDriver driver){
            this.driver = driver;
        }

        public void clickCheckout(){
            driver.findElement(checkoutButton).click();
        }
    }


