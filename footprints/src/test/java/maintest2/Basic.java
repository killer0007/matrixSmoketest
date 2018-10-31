package maintest2;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;
import verification.*;

@Listeners(environment.Listener.class)
public class Basic {

	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String ContractName = null;
	protected String ClientName = null;
	protected String ProjectName = null;
	protected String CandidateName = null;
	protected String CandidateId = null;
	protected String lastname = null;
	protected String refno = null;
	protected String uname = null;

	@BeforeSuite
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/matrixflow.html");
		reporter.config().setDocumentTitle("Matrix test report");
		reporter.config().setReportName("test report");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);

	}

	@BeforeTest
	public void beforetest() throws Exception {
		driver = BaseClass.getDriver();
		config = BaseClass.getlocator();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(config.getProperty("url"));
		ContractName = config.getProperty("contractname");
		ClientName = config.getProperty("clientname");
		ProjectName = config.getProperty("projectname");

	}

	@BeforeMethod
	public void setup(Method method) throws FileNotFoundException, IOException {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(logger);

	}

	/**
	 * Login action
	 */
	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
	}

	@Test(priority = 4, enabled = true)
	public void dataEntry() throws Exception {
	
		refno = "HDFC000644";
		Credit cri = new Credit(logger);
		pages.Verification().verification();
		pages.Verification().Credit(refno);
		cri.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Credit Check 1");
		pages.CaseTracker().cancel();
		System.out.println(stage);
//		assertEquals(stage, "Report Generation Assignment Pending");
		
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
//			String pagesource = driver.getPageSource();
//			if (result.getThrowable().getMessage().contains("The following asserts failed")) {
//				logger.log(Status.FAIL, result.getThrowable().getMessage());
//			} else if (pagesource.contains("Images/message.png")) {
//				logger.log(Status.WARNING, "Your last session was terminated");
//				pages.Utill().click_element("ctl00_ContentPlaceHolder1_urls");
//				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
//			} else if (pagesource.contains("ctl00_ContentPlaceHolder1_txtUserName")) {
//				logger.log(Status.WARNING, "Your last session was closed by user");
//				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
//			} else {
//				logger.log(Status.WARNING, method.getName() + " navigating to home page due to error");
//				driver.get(config.getProperty("url") + "/Web/dashboard.aspx");
//				pages.Utill().wait_until_loader_is_invisible(80);
//			}

		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() throws Exception {
//		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}
	public static Map<String, String> mode(){
		Map<String, String> map = new HashMap<>();
		map.put("Permanent", "In Person");
		map.put("Current Address", "In Person");
		map.put("12th", "Email (Preffered)");
		map.put("UG1", "Email (Preffered)");
		map.put("Current/Latest Employment", "Email");
		map.put("Previous Employment", "Email");
		map.put("Reference 1", "Phone");
		map.put("Current Address Criminal Check", "In Person");
		map.put("Permanent Criminal Check", "In Person");
		map.put("Current Address Court Check", "In Person");
		map.put("Permanent Court Check", "In Person");
		map.put("Database", "Online");
		map.put("Credit Check 1", "Online");
		map.put("Passport", "Online");
		map.put("Aadhaar Card", "Online");
		map.put("Panel1", "In Person");
		return map;
	}
}
