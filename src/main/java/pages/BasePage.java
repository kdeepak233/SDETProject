package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConstantData;

public class BasePage {
   public WebDriverWait wait;
 
    public void waitForElements() {
        wait = new WebDriverWait(ConstantData.drivers, 100);
    }
 
    //Click Method
    public void click(By elementLocation) {
        waitVisibility(elementLocation);
        ConstantData.drivers.findElement(elementLocation).click();
    }
 
    //Clear and Write Text
    public void clearAndWriteText(By elementLocation, String text) {
        waitVisibility(elementLocation);
        ConstantData.drivers.findElement(elementLocation).clear();
        ConstantData.drivers.findElement(elementLocation).sendKeys(text);
    }
 
    //Read Text
    public String readText(By elementLocation) {
        waitVisibility(elementLocation);
        return ConstantData.drivers.findElement(elementLocation).getText();
    }
 
    //Wait
    public void waitVisibility(By by){
    	waitForElements();
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    
    //Write Text
    public void writeText(By elementLocation, String text) {
        waitVisibility(elementLocation);
        ConstantData.drivers.findElement(elementLocation).sendKeys(text);
    }
}