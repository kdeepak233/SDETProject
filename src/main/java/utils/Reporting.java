package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Reporting {
	 
	private static ExtentReports extent;
	private static ExtentTest test;
	
	public String captureScreenshot(WebDriver driver) {
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotData=null;
		FileInputStream fileInputStream =null;
		try {
			fileInputStream=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] bytes=new byte[(int)file.length()];
		try {
			fileInputStream.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		screenshotData=new String(Base64.getEncoder().encode(bytes));
		return "data:image/png;base64,"+screenshotData;
	}
	
	static {
		extent =new ExtentReports(System.getProperty("user.dir")+"\\ExtentReport\\extent.html",true);
	    extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	public void getResult(ITestResult result)
    {
        if(result.getStatus()==ITestResult.SUCCESS)
        {
            test.log(LogStatus.PASS,result.getName()+" test case PASSED");
        }
        else if(result.getStatus()==ITestResult.SKIP)
        {
            test.log(LogStatus.SKIP,result.getName()+" test case skipped and the reason is :"+ result.getThrowable());
        }
        else if(result.getStatus()==ITestResult.FAILURE)
        {
            test.log(LogStatus.FAIL,result.getName()+" test case FAILED ");
        }
    }
}