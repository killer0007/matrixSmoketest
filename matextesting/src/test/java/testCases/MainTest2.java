package testCases;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import environment.SendAttachmentInEmail;
import environment.Utill;
import pages.temp;

public class MainTest2 {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "DEMOTAD057";

	@BeforeSuite
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/demo.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	

	@BeforeTest
	public void start() throws IOException {
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
//		 ChromeOptions chromoption = new ChromeOptions();
//		 chromoption.setHeadless(true);
		driver = new ChromeDriver();
//		 Dimension d = new Dimension(1382, 744);
//		 driver.manage().window().setSize(d);
		driver.manage().window().maximize();
		driver.get(getvalue("url"));
	}

	@BeforeMethod
	public void setup(Method method) throws Exception{
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		pages = new Pages(driver, logger);
		System.out.println("before method");
		//pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
	}

	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		pages.loginpage().Login("demogpt");
		//Assert.assertEquals(pages.Utill().find("ctl00_lblUsername").getText(), "Demotl");

	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "Login")
	public void caseregistration() throws Exception {
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_dashboard1", "Client Home Page");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_initiator4", "Initiator Page");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_rejector5", "ReinitatorPage");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_casetracker6", "Client case tracker");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_insuffclear7", "CRTInsuff Clear");
		pages.Utill().mouseover("ctl00_dataentry2");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton1", "Verification Entry");
		pages.Utill().mouseover("ctl00_dataentry2");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton2", "Incomplete Data Entry");
		pages.Utill().mouseover("ctl00_dataentry2");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton6", "CRT: Gen Subcheck dataentry");
		pages.Utill().mouseover("ctl00_dataentry2");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton7", "MIS Report Upload");
		
		pages.Utill().mouseover("ctl00_coeupdate3");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton3", "CoeMaster");
		pages.Utill().mouseover("ctl00_coeupdate3");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton5", "COE Check Setup");
		
		pages.Utill().mouseover("ctl00_candidatecreation8");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_candidatecreation", "Genpact Candidate Users");
		pages.Utill().mouseover("ctl00_candidatecreation8");
		pages.MatrixPortalNavigation().GetGenPactpage("'ctl00_LinkButton4", "Genpact Candidate Users Search");
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
	public void teardown() {
		// pages.loginpage().Logout();
		//driver.close();
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
