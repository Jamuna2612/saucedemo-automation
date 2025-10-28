package org.example.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {

    WebDriver driver;
    private By completeHeader = By.className("complete-header");
    private By homePage = By.xpath("//*[text()='Back Home']");

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
    }

    public String getCompletionMessage(){
        return driver.findElement(completeHeader).getText();
    }

    public void homeView(){
        driver.findElement(homePage).click();
    }
}
