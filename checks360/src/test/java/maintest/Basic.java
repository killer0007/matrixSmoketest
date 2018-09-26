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
public class Basic  {

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
	public void TC_SPINF_012() throws Exception {
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		refno="HDFC000389";
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		contract.remove("Current/Latest Employment");
		contract.remove("Previous Employment");
		contract.remove("Previous Employment 2");
		contract.remove("Previous Employment 3");
		contract.remove("Previous Employment 4");


		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (name.equals("Panel1") || name.equals("Medical Test")) {
				if (data.get(i).get("CurrentStage").equals("Verification Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else if (name.contains("Employment")) {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		List<String> checkname = pages.CaseTracker().getcomponentname(data);
		pages.CaseTracker().cancel();
		contract.removeAll(checkname);

		sf.assertTrue(contract.size() == 0, "success");
		sf.assertAll();
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
