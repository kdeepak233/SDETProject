package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;;

public class DriverDetails {

	public WebDriver driver;

	private void check() {
		if (System.getProperty("browser").isEmpty())
			System.setProperty("browser", "chrome");
	}

	public WebDriver setDriver() {
		check();
		if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
		} else if (System.getProperty("browser").equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\iedriver.exe");
			driver = new InternetExplorerDriver();
		}
		// driver.manage().window().maximize();
		ConstantData.drivers = driver;
		return driver;
	}

}
