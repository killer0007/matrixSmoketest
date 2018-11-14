package maintest2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
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
		driver.get(config.getProperty("url")+"/clientLogin.aspx");
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
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("clientuname"), config.getProperty("clientpass"));
		//pages.Verification().verification();
	}

	
	@Test(priority = 2, enabled = true)
	public void casereg() throws Exception {
		Casereg casereg= new Casereg(driver, logger);
		casereg.casereg();
		casereg.Registercase();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		Map<String, String> datas= new HashMap<>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		datas.put("doctype", "Authorization Letter");
		datas.put("filename", config.getProperty("casedoc"));
		casereg.registercase(datas);
		String[] components = pages.CaseRegistration().getcomponents();
		for (int i = 0; i < components.length; i++) {
			casereg.selectcheck(components[i].toString());
		}
		casereg.Register();
		String info=pages.Utill().confirmAlert();
		String [] l = info.split(" ");
		refno=l[l.length-1];
		pages.Home().homepage();
		casereg.casereg();
		casereg.Search(refno);
		Map<String, String> casedata=casereg.getcasedetails();
		String actual=casedata.get("caserefno");
		assertEquals(actual, refno);
	}
	@Test(priority = 3, enabled = true)
	public void dataentry() throws Exception {
		pages.Home().Logout();
		driver.get(config.getProperty("url"));
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		DataEntrySupervision des = pages.DataEntrySupervision();
		des.datanentrysupervision();
		des.assign(refno, "demoempl");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
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
	
}
