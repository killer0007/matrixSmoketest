package userLogin;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import environment.Utill;

public class ExtentReportDemo2 {
	// Create global variable which will be used in all method
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;

	// This code will run before executing any testcase
	@BeforeSuite
	public void beforeSuit() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/learn_automation2.html");

		extent = new ExtentReports();
		
		extent.attachReporter(reporter);
	}

	@BeforeMethod
	public void setup(Method method) {

		logger = extent.createTest(method.getName());
		System.out.println("before method");
	}

	// Actual Test which will start the application and verify the title
	@Test(enabled = true)
	public void loginTest() throws IOException {
		// logger = extent.createTest("LoginTest");
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
		driver = new ChromeDriver();
		driver.get("http://www.google.com");
		System.out.println("title is " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Mukesh"));
		

	}

	@Test(enabled = true)
	public void testTwo() throws IOException {

		driver.get("http://www.google.com");

		System.out.println("title is " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Mukesh"));
		

	}
	@Test(enabled = true)
	public void testThree() throws IOException {

		driver.get("http://www.google.com");

		System.out.println("title is " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Mukesh"));

	}
	

	// This will run after testcase and it will capture screenshot and add in report
	@AfterMethod(enabled = true)
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);

			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		driver.quit();
	}

}