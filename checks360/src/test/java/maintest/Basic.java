package maintest;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import actions.CaseOwnerInsuffClear;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;

@Listeners(environment.Listener.class)
public class Basic extends Design {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 100, Font.BOLD);

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
	public void beforetest() throws FileNotFoundException, IOException {
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
		pages = new Pages(driver, logger);

	}

	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
	}

	// To check insuff cleared components moved to case registration
	@Test(priority = 30, enabled = true)
	public void TC_SPINF_002() throws Exception {
		String[] checks = { "Current Address", "UG1", "Current/Latest Employment", "Reference 1", "Aadhaar Card",
				"Current Address Criminal Check", "Current Address Court Check", "Credit Check 1", "Panel1",
				"Database" };
		refno = "HDFC000308";
		pages.Home().clickActions();
		pages.CaseOwnerInsuffClear().search(refno, "sp");
		pages.CaseOwnerInsuffClear().openCase();
		for (int i = 0; i < checks.length; i++) {
			String name = checks[i].toString();
			pages.CaseOwnerInsuffClear().clearComments(name, name + " clear");
		}
		pages.CaseOwnerInsuffClear().clear();
		pages.Utill().confirmAlert();
		pages.Home().workStage();
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		//assing
		pages.DataEntrySupervision().datanentrysupervision();
		pages.DataEntrySupervision().assign(refno, "demoempl");
		pages.DataEntry().datanentry();
		pages.DataEntry().search(refno);
		pages.DataEntry().selectcase(refno);


	}

	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() throws Exception {
		// pages.loginpage().Logout();
//		Thread.sleep(10000);
//		driver.close();
	}

	@AfterSuite
	public void afterSuite() {

		extent.flush();
//		 driver.quit();

	}
//	private String getvalue(String key) throws FileNotFoundException, IOException {
//		Properties pr = new Properties();
//		pr.load(new FileInputStream(new File("./config.properties")));
//		return pr.getProperty(key);
//	}
}
