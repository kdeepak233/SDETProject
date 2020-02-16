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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import utils.ConstantData;
import utils.Log4j;
import utils.Reporting;

public class BasePage {
	private WebDriverWait wait;
	Reporting report = new Reporting();
	private static HashMap<String, String> storageData = new HashMap<String, String>();
	Log4j log = new Log4j();

	public void waitForElements() {
		wait = new WebDriverWait(ConstantData.drivers, 100);
	}

	// Click Method
	public void click(By elementLocation) {
		try {
			log.logger.info("Before clicking on element " + elementLocation);
			waitVisibility(elementLocation);
			WebElement ele = ConstantData.drivers.findElement(elementLocation);
			Actions builder = new Actions(getDriver());
			builder.moveToElement(ele).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(elementLocation));
			ele.click();
			log.logger.info("After clicking on element " + elementLocation);
		} catch (Exception e) {
			log.logger.error("Failed at" + elementLocation);
		}
	}

	public void click(WebElement element) {
		try {
			log.logger.info("Before clicking on element " + element);
			waitVisibility(element);
			Actions builder = new Actions(getDriver());
			builder.moveToElement(element).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			log.logger.info("After clicking on element " + element);
		} catch (Exception e) {
			log.logger.error("Failed at" + element);
		}
	}

	// Clear and Write Text
	public void clearAndWriteText(By elementLocation, String text) {
		try {
			log.logger.info("Before entering text " + elementLocation);
			waitVisibility(elementLocation);
			ConstantData.drivers.findElement(elementLocation).clear();
			ConstantData.drivers.findElement(elementLocation).sendKeys(text);
			log.logger.info("After entering text " + elementLocation);
		} catch (Exception e) {
			log.logger.error("Failed at" + elementLocation);
		}
	}

	// Read Text
	public String readText(By elementLocation) {
		String data = null;
		try {
			log.logger.info("Before reading text " + elementLocation);
			waitVisibility(elementLocation);
			data = ConstantData.drivers.findElement(elementLocation).getText();
			log.logger.info("After reading text from " + elementLocation + " and text is " + data);
		} catch (Exception e) {
			log.logger.error("Failed at" + elementLocation);
		}
		return data;
	}

	// Wait
	public void waitVisibility(By by) {
		try {
			log.logger.info("Before waiting for element " + by);
			waitForElements();
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			log.logger.info("After waiting for element " + by);
		} catch (Exception e) {
			log.logger.error("Failed at" + by);
			e.printStackTrace();
		}
	}

	public void waitVisibility(WebElement element) {
		try {
			log.logger.info("Before waiting for element " + element);
			waitForElements();
			wait.until(ExpectedConditions.visibilityOf(element));
			log.logger.info("After waiting for element " + element);
		} catch (Exception e) {
			log.logger.error("Failed at" + element);
			e.printStackTrace();
		}
	}

	// Write Text
	public void writeText(By elementLocation, String text) {
		try {
			log.logger.info("Before writing text " + elementLocation);
			waitVisibility(elementLocation);
			ConstantData.drivers.findElement(elementLocation).sendKeys(text);
			log.logger.info("After writing text " + elementLocation);
		} catch (Exception e) {
			log.logger.error("Failed at" + elementLocation);
			e.printStackTrace();
		}
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
				Reporting.test.log(Status.INFO, result,
						MediaEntityBuilder.createScreenCaptureFromBase64String(ss).build());
				log.logger.info("Log.Info: " + result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (data.equalsIgnoreCase("Skip")) {
			try {
				Reporting.test.log(Status.SKIP, result,
						MediaEntityBuilder.createScreenCaptureFromBase64String(ss).build());
				log.logger.fatal("Log.Skip: " + result);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Assert.assertFalse(true);
		} else if (data.equalsIgnoreCase("fail")) {
			try {
				Reporting.test.log(Status.FAIL, result,
						MediaEntityBuilder.createScreenCaptureFromBase64String(ss).build());
				log.logger.error("Log.Fail: " + result);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Assert.assertFalse(true);
		}
	}

	public int getRandomInteger(int size) {
		Random random = new Random();
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
		Random random = new Random();
		int m = (int) Math.pow(10, length - 1);
		return m + random.nextInt(9 * m);
	}

	public String getPropertyData(String key) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(
					new File(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\data.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties property = new Properties();
		try {
			property.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property.getProperty(key);
	}

	public String getRandomCaps(int length) {
		return RandomStringUtils.randomAlphabetic(length).toUpperCase();
	}

	public void waitForLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
						.equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(pageLoadCondition);
	}
}
