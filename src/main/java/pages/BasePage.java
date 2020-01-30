package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import utils.ConstantData;
import utils.Reporting;

public class BasePage {
	private WebDriverWait wait;
	Reporting report = new Reporting();
	private static HashMap<String, String> storageData = new HashMap<String, String>();

	public void waitForElements() {
		wait = new WebDriverWait(ConstantData.drivers, 100);
	}

	// Click Method
	public void click(By elementLocation) {
		waitVisibility(elementLocation);
		WebElement ele=ConstantData.drivers.findElement(elementLocation);
		Actions builder = new Actions(getDriver());
		builder.moveToElement(ele).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(elementLocation));
		ele.click();
	}

	public void click(WebElement element) {
		waitVisibility(element);
		Actions builder = new Actions(getDriver());
		builder.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	// Clear and Write Text
	public void clearAndWriteText(By elementLocation, String text) {
		waitVisibility(elementLocation);
		ConstantData.drivers.findElement(elementLocation).clear();
		ConstantData.drivers.findElement(elementLocation).sendKeys(text);
	}

	// Read Text
	public String readText(By elementLocation) {
		waitVisibility(elementLocation);
		return ConstantData.drivers.findElement(elementLocation).getText();
	}

	// Wait
	public void waitVisibility(By by) {
		try {
		waitForElements();
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void waitVisibility(WebElement element) {
		try {
		waitForElements();
		wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Write Text
	public void writeText(By elementLocation, String text) {
		waitVisibility(elementLocation);
		ConstantData.drivers.findElement(elementLocation).sendKeys(text);
	}

	// return WebDriver
	public WebDriver getDriver() {
		return ConstantData.drivers;
	}

	// record Dynamic Data
	public void setData(String key, String value) {
		storageData.put(key, value);
	}

	// return Dynamic Data
	public String getData(String key) {
		return storageData.get(key).toString();
	}

	public void getReport(String data, String result) {
		String ss = report.captureScreenshot(ConstantData.drivers);
		if (data.equalsIgnoreCase("Info")) {
			try {
				Reporting.test.log(Status.INFO, result,MediaEntityBuilder.createScreenCaptureFromBase64String(ss).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (data.equalsIgnoreCase("Skip")) {

			Reporting.test.log(Status.SKIP, result);
			Reporting.test.addScreenCaptureFromBase64String(ss);
			Assert.assertFalse(true);
		} else if (data.equalsIgnoreCase("fail")) {
			Reporting.test.log(Status.FAIL, result);
			Reporting.test.addScreenCaptureFromBase64String(ss);
			Assert.assertFalse(true);
		}
	}
	
	public int getRandomInteger(int size) {
		Random random=new Random();
		return random.nextInt(size);
	}
	
	public String getRandomString(int size) {
		return RandomStringUtils.randomAlphabetic(size);	
	}
	
	public String getText(By by) {
		waitVisibility(by);
		return ConstantData.drivers.findElement(by).getText();
	}
	
	public String getText(WebElement ele) {
		waitVisibility(ele);
		return ele.getText();
	}
	
	public void waitTillElementNotVisible(By by) {
		waitVisibility(by);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public int getRandomIntegerLength(int length) {
		Random random=new Random();
		int m = (int) Math.pow(10, length - 1);
	    return m + random.nextInt(9 * m);
	}
	
	public String getPropertyData(String key) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\data.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties property=new Properties();
		try {
			property.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property.getProperty(key);
	}
}
