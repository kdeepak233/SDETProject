package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
//import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Properties;


public class HelperTest extends DriverDetails{

	Reporting report=new Reporting();
	public ExtentReports ext=report.createInstance();
	public final static Logger LOGGER = Logger.getLogger(HelperTest.class);
    public static Properties dataProperties;
    //BasePage basePage = new BasePage();
    
    String testCaseId;
    
    public void setTestSetId(String testsetId) {
    	testCaseId =testsetId;
    	Reporting.test=ext.startTest(testCaseId);
	}
    
    @BeforeMethod(alwaysRun=true)
    public void initializeData() {
    	setDriver();
    } 

    @AfterMethod(alwaysRun = true)
    public void publishResults(ITestResult result) {
    	try {
            if (result.getStatus() == ITestResult.FAILURE) {
                Reporting.test.log(LogStatus.FAIL, result.getName() +" Test case FAILED due to below issues:");
                Reporting.test.log(LogStatus.FAIL,result.getThrowable());
                Reporting.test.log(LogStatus.FAIL, "Snapshot when exception occur: ",Reporting.test.addScreenCapture(report.captureScreenshot(ConstantData.drivers)));
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                Reporting.test.log(LogStatus.PASS,"Passed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                Reporting.test.log(LogStatus.SKIP,result.getThrowable());
            } else {
                Reporting.test.log(LogStatus.UNKNOWN,result.getThrowable());
            }
        } catch (Exception e) {
            Reporting.test.log(LogStatus.FAIL, "Exception in Tear down : " + e.getMessage());
        }
    	driver.quit();
    	Reporting.test=null;
    }

    @BeforeSuite(alwaysRun = true)
    public void initializeDataProperty() {
		/*
		 * dataProperties = new Properties(); String dataFileName = "data.properties";
		 * String dataPath = System.getProperty("user.dir") +
		 * "\\src\\test\\resources\\testData\\" + dataFileName; try { FileInputStream
		 * dataFile = new FileInputStream(dataPath); dataProperties.load(dataFile); }
		 * catch (FileNotFoundException e) { Assert.assertTrue(false, e.getMessage()); }
		 * catch (IOException e) { Assert.assertTrue(false, e.getMessage()); }
		 */
    }

    @AfterClass(alwaysRun = true)
    public void clearDynamicData() {
    }

    @AfterSuite(alwaysRun = true)
    public void clearExtentTest() {
        try {
            ext.flush();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        } finally {
            copyResults();
        }
    }

    public static String getTestData(String propertyName) {
        return dataProperties.getProperty(propertyName);
    }

    public void copyResults() {
		/*
		 * String destinationDir = ""; try { String timeStamp = new
		 * SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); destinationDir =
		 * "//qsvmnas03/QMOAutomationTestResults/Mosaic/Release18/CertCycle14/TestNG/" +
		 * System.getProperty("includedGroups") + "/" + timeStamp + "/extent-reports";
		 * File logFile = new File(System.getProperty("user.dir") + "/build/log.out");
		 * String sourceDir = System.getProperty("user.dir") + "/build/extent-reports";
		 * File destDir = new File(destinationDir); File srcDir = new File(sourceDir);
		 * FileUtils.copyFileToDirectory(logFile, destDir);
		 * FileUtils.copyDirectory(srcDir, destDir);
		 * System.out.println("Results are copied from location " +
		 * sourceDir.replace('/', '\\'));
		 * System.out.println("Results are copied to location " +
		 * destinationDir.replace('/', '\\')); } catch (Exception e) {
		 * Assert.assertTrue(false,
		 * "Exception occurred while moving results to shared drive : " +
		 * destinationDir); }
		 */
    }

    public void getReport(String data,String result)
    {
        if(data.equalsIgnoreCase("Info"))
        {
        	Reporting.test.log(LogStatus.INFO,Reporting.test.addScreenCapture(report.captureScreenshot(ConstantData.drivers)));
            Reporting.test.log(LogStatus.INFO,result);
        }
        else if(data.equalsIgnoreCase("Skip"))
        {
        	
        	Reporting.test.log(LogStatus.SKIP,result);
        	Reporting.test.log(LogStatus.SKIP,Reporting.test.addScreenCapture(report.captureScreenshot(ConstantData.drivers)));
            Assert.assertFalse(true);
        }
        else if(data.equalsIgnoreCase("fail"))
        {
        	Reporting.test.log(LogStatus.FAIL,result);
        	Reporting.test.log(LogStatus.FAIL,Reporting.test.addScreenCapture(report.captureScreenshot(ConstantData.drivers)));
            Assert.assertFalse(true);
        }
    }
    
}
