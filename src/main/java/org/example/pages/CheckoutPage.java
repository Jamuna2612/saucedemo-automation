package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

    public class CheckoutPage {

        WebDriver driver;

        private By firstNameInput = By.id("first-name");
        private By lastNameInput = By.id("last-name");
        private By postalCodeInput = By.id("postal-code");
        private By continueButton = By.id("continue");
        private By finishButton = By.id("finish");

        public CheckoutPage(WebDriver driver){
            this.driver = driver;
        }

        public void enterCheckoutInfo(String firstName, String lastName, String postalCode){
            driver.findElement(firstNameInput).sendKeys(firstName);
            driver.findElement(lastNameInput).sendKeys(lastName);
            driver.findElement(postalCodeInput).sendKeys(postalCode);
        }

        public void clickContinue(){
            driver.findElement(continueButton).click();
        }

        public void clickFinish(){
            driver.findElement(finishButton).click();
        }
    }

