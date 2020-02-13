package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;;

public class DriverDetails {

	public WebDriver driver;

	private void check() {
		if (System.getProperty("browser").isEmpty())
			System.setProperty("browser", "chrome");
	}

	public WebDriver setDriver() {
		check();
		if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\java\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (System.getProperty("browser").equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\java\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (System.getProperty("browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\java\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		ConstantData.drivers = driver;
		return driver;
	}

}
