package maintest2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import client.Casereg;
import client.VerifyCandidateDetails;
import client.ViewCandidateFinalReport;
import dashboard.DataEntrySupervision;
import dashboard.ReportGeneration;
import dashboard.ReportValidation;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;
import verification.Address;
import verification.Court;
import verification.Credit;
import verification.Criminal;
import verification.Database;
import verification.Drug;
import verification.Education;
import verification.Employment;
import verification.Id;
import verification.Reference;
import verification.VerificationInitiate;

@Listeners(environment.Listener.class)
public class Basic {

	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String contractName = null;
	protected String clientName = null;
	protected String projectName = null;
	protected String candidateName = null;
	protected String candidateId = null;
	protected String lastName = null;
	protected String refno = null;
	protected String uname = null;
	private String [] component = {"Current Address","UG1","Current/Latest Employment","Voter ID","Reference 1",};
	private List<String> components=new ArrayList<>(Arrays.asList(component));
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
		driver = new BaseClass().getDriver();
		config = BaseClass.getlocator();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//		driver.get(config.getProperty("url")+"/clientLogin.aspx");
		driver.get(config.getProperty("url")+"/hdfc");
		contractName = config.getProperty("contractname");
		clientName = config.getProperty("clientname");
		projectName = config.getProperty("projectname");

	}

	@BeforeMethod
	public void setup(Method method) throws FileNotFoundException, IOException {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver,logger);

	}

	/**
	 * Login action
	 */
	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, result.getName());
		} else {
			logger.pass(result.getName() + " completed");
		}
	        extent.flush();
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
