package maintest2;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;

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
	@Test(priority = 1, enabled = true, groups = { "smoketest", "smoketest", "spcase registration", "insuff" })
	public void Login() throws Exception {
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
	}

	/**
	 * To check title of page
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 2, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_001() throws Exception {
		pages.Home().clickRegister();
		String title = pages.CaseRegistration().getTitle();
		if (title.equals("Case Registration")) {
			assertTrue(true);
		} else {
			assertTrue(title, false);
		}
	}

	/**
	 * To check mandatory alert for client
	 */
	@Test(priority = 3, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_002() {
		pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlClient", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	/**
	 * To check mandatory alert for project
	 */
	@Test(priority = 4, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_003() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlProject", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	/**
	 * To check mandatory alert for contract
	 */
	@Test(priority = 5, enabled = true, groups = { "spcase registration" })
	public void TC_SCPR_004() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlContract", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}

	}


	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
			String pagesource = driver.getPageSource();
			if (result.getThrowable().getMessage().contains("The following asserts failed")) {
				logger.log(Status.FAIL, result.getThrowable().getMessage());
			} else if (pagesource.contains("Images/message.png")) {
				logger.log(Status.WARNING, "Your last session was terminated");
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_urls");
				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			} else if (pagesource.contains("ctl00_ContentPlaceHolder1_txtUserName")) {
				logger.log(Status.WARNING, "Your last session was closed by user");
				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			} else {
				logger.log(Status.WARNING, method.getName() + " navigating to home page due to error");
				driver.get(config.getProperty("url") + "/Web/dashboard.aspx");
				pages.Utill().wait_until_loader_is_invisible(80);
			}

		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() throws Exception {
		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}
}
