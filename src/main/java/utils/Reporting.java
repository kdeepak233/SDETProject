package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Reporting {

	private static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;

	public String captureScreenshot(WebDriver driver) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotData = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] bytes = new byte[(int) file.length()];
		try {
			fileInputStream.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		screenshotData = new String(Base64.getEncoder().encode(bytes));
		return "data:image/png;base64," + screenshotData;
	}

	public ExtentReports createInstance() {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Extent Report\\extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

	public String captureFullScreenshot(WebDriver driver) {
		Screenshot sc = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			ImageIO.write(sc.getImage(), "jpg", new File(System.getProperty("user.dir") + "screenshot.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File file = new File(System.getProperty("user.dir") + "screenshot.jpg");
		String screenshotData = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] bytes = new byte[(int) file.length()];
		try {
			fileInputStream.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		screenshotData = new String(Base64.getEncoder().encode(bytes));
		return "data:image/png;base64," + screenshotData;
	}
}