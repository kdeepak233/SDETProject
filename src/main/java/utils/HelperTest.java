package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import pages.BasePage;
import services.Connection;

public class HelperTest extends DriverDetails {

	Reporting report = new Reporting();
	public ExtentReports ext = report.createInstance();
	public final static Logger LOGGER = Logger.getLogger(HelperTest.class);
	public static Properties dataProperties;
	BasePage basePage = new BasePage();
	public static RequestSpecBuilder requestSpecBuilder;
	RequestSpecification requestSpecification;
	String testCaseId;

	public void setTestSetId(String testsetId) {
		testCaseId = testsetId;
		Reporting.test = ext.createTest(testCaseId);
	}

	@BeforeMethod(alwaysRun = true)
	public void initializeData() {
		setDriver();
		ConstantData.drivers.get(getTestData("WebUrl"));
		requestSpecBuilder = Connection.intializeService(getTestData("RestUrl"));
	}

	@AfterMethod(alwaysRun = true)
	public void publishResults(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				Reporting.test.log(Status.FAIL, result.getName() + " Test case FAILED due to below issues:");
				Reporting.test.log(Status.FAIL, result.getThrowable());
				Reporting.test.log(Status.FAIL, "Exception occured at : ", MediaEntityBuilder
						.createScreenCaptureFromBase64String(report.captureScreenshot(ConstantData.drivers)).build());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				Reporting.test.log(Status.PASS, "Passed");
			} else if (result.getStatus() == ITestResult.SKIP) {
				Reporting.test.log(Status.SKIP, result.getThrowable());
			} else {
				Reporting.test.log(Status.ERROR, result.getThrowable());
			}
		} catch (Exception e) {
			Reporting.test.log(Status.FAIL, "Exception is : " + e.getMessage());
		} finally {
			Reporting.test = null;
			driver.quit();
		}
	}

	@BeforeSuite(alwaysRun = true)
	public void initializeDataProperty() {
		dataProperties = new Properties();
		String dataFileName = "data.properties";
		String dataPath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\" + dataFileName;
		try {
			FileInputStream dataFile = new FileInputStream(dataPath);
			dataProperties.load(dataFile);
		} catch (FileNotFoundException e) {
			Assert.assertTrue(false, e.getMessage());
		} catch (IOException e) {
			Assert.assertTrue(false, e.getMessage());
		}
	}

	@AfterSuite(alwaysRun = true)
	public void clearExtentTest() {
		try {
			ext.flush();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			copyData();
		}
	}

	private void copyData() {
		String destinationDir = "";
		try {
			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			destinationDir = "C:\\Result\\" + System.getProperty("includeGroups") + "/" + timeStamp;
			String ersourceDir = System.getProperty("user.dir") + "/Extent Report";
			File destDir = new File(destinationDir + "/Extent-reports");
			File srcDir = new File(ersourceDir);
			FileUtils.copyDirectory(srcDir, destDir);
			String logsourceDir = System.getProperty("user.dir") + "/log";
			srcDir = new File(logsourceDir);
			destDir = new File(destinationDir + "/logs");
			FileUtils.copyDirectory(srcDir, destDir);
			System.out.println("Results are copied to location " + destinationDir.replace('/', '\\'));
		} catch (Exception e) {
			LOGGER.error("Unable to copy data to " + destinationDir + " with exception " + e);
		}

	}

	public static String getTestData(String propertyName) {
		return dataProperties.getProperty(propertyName);
	}

	public ExtentTest createNode(ExtentTest extent, String name) {
		ExtentTest childExtentTest = extent.createNode(name);
		return childExtentTest;
	}

}
