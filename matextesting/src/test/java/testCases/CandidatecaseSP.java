package testCases;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.omg.Messaging.SyncScopeHelper;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import environment.DbConnection;
import environment.ReadEmail;
import environment.Utill;

public class CandidatecaseSP {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "DEMOTAF140";
	String loginid;

	@BeforeSuite
	public void beforeSuit() {
		// reporter = new ExtentHtmlReporter("./Reports/matex.html");
		reporter = new ExtentHtmlReporter("./Reports/CandidatecaseSP.html");
		reporter.config().setDocumentTitle("Matrix test report");
		reporter.config().setReportName("test report");

		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@BeforeTest
	public void start() throws IOException {
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
		ChromeOptions chromoption = new ChromeOptions();
		chromoption.setHeadless(true);
		driver = new ChromeDriver();
		Dimension d = new Dimension(1382, 744);
		driver.manage().window().setSize(d);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(getvalue("url"));
		
		// driver.get("http://192.168.2.16/MatexTesting");
	}

	@BeforeMethod
	public void setup(Method method) throws Exception {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver, logger);
		// pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
	}

	@Test(priority = 1, enabled = false)
	public void CandidateInitiation() throws Exception {
		int candidateid = pages.Utill().candidateid();
		pages.loginpage().Login("demotl");
		pages.CaseRegistration().navigateTo("CRT", "Candidate users");
		loginid = pages.CandidateInitiation().InitiateCandidate(candidateid);
	}

	@Test(priority = 2, enabled = false, dependsOnMethods = "CandidateInitiation")
	public void checkemail() throws Exception {
		String data = ReadEmail.read();
		assertTrue(data.contains(loginid));
	}

	@Test(priority = 3, enabled = true)
	public void CandidateDataEntry() throws Exception {
		pages.loginpage().Login("Vish5024");	
		pages.CandidateInitiation().Personal();
		
		
	}

	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip(result.getThrowable().getMessage());
		} else {

			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() {
		// pages.loginpage().Logout();
		// driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		// driver.quit();
		// SendAttachmentInEmail email = new SendAttachmentInEmail();
		// email.sendhtmlemail();

	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}
}
