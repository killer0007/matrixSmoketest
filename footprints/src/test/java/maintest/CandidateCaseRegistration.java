package maintest;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import candidate.Register;
import client.VerifyCandidateDetails;
import environment.BaseClass;
import environment.DbConnection;
import environment.Pages;
import environment.Utill;

public class CandidateCaseRegistration implements Design {

	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String clientName = null;
	protected String contractName = null;
	protected String projectName = null;
	protected String candidateName = null;
	protected String candidateId = null;
	protected String lastName = null;
	protected String refno = null;
	protected String uname = null;

	/**
	 * Initializing extent report objects
	 */
	@BeforeSuite(alwaysRun = true)
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/CandidateCaseRegistration.html");
		reporter.config().setDocumentTitle("service provider case registration");
		reporter.config().setReportName("Gopinath");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	/**
	 * Starting web driver and Initializing variables
	 */
	@BeforeTest(alwaysRun = true)
	public void beforetest() throws Exception {
		driver = new BaseClass().getDriver();
		config = BaseClass.getlocator();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		projectName = config.getProperty("projectname");
		clientName = config.getProperty("clientname");
		contractName = config.getProperty("contractname");
		DbConnection db =new DbConnection();
		String code = db.getCandidateUrl(projectName);
		driver.get(config.getProperty("url")+"/"+code);
	}
	/**
	 * Initializing extent report object with test case logger
	 */
	@BeforeMethod(alwaysRun = true)
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
	public void CandidateInitiate() throws Exception {
		Register reg= new Register(driver, logger);
		reg.clickRegisterLink();
		candidateName=reg.candidateName();
		lastName=reg.lastName();
		reg.FirstName(candidateName);
		reg.LastName(lastName);
		reg.dob();
		reg.EmailID(reg.getemail());
		reg.Password("Pass@123");
		reg.clickRegisterbtn();
		String msg =reg.confirmAlert();
		assertEquals(msg, "Created Successfully.");
	}
	@Test(priority = 2, enabled = true, dependsOnMethods="CandidateInitiate")
	public void CandidatedataEntry() throws Exception {
		Register register=pages.Register();
		pages.Login().userLogin(pages.Utill().getemail()+"@ggmail.com", "Pass@123");
//		pages.Login().userLogin("gopiAAA3@ggmail.com", "Pass@123");
		register.unCheckAll();
		String [] component = {"Current Address","UG1","Current/Latest Employment","Voter ID","Reference 1",};
		register.selectCheck(component);
		register.click("btnApplyChanges_input");
		register.waitUntilLoaderisInvisible(100);
		SoftAssert sf = new SoftAssert();
		pages.Basic().basic();
		sf.assertEquals(pages.Basic().getStatusColor(), "rgba(0, 128, 0, 1)");
		sf.assertEquals(pages.Basic().getStatus(), "Provided");
		pages.Address().CurrentAddress();
		sf.assertEquals(pages.Address().getStatusColor(), "rgba(0, 128, 0, 1)");
		sf.assertEquals(pages.Address().getStatus(), "Provided");
		pages.Education().UGone();
		sf.assertEquals(pages.Education().getStatusColor(), "rgba(0, 128, 0, 1)");
		sf.assertEquals(pages.Education().getStatus(), "Provided");
		pages.Employment().currentEmployment();
		sf.assertEquals(pages.Employment().getStatusColor(), "rgba(0, 128, 0, 1)");
		sf.assertEquals(pages.Employment().getStatus(), "Provided");
		pages.Id().VoterID();
		sf.assertEquals(pages.Id().getStatusColor(), "rgba(0, 128, 0, 1)");
		sf.assertEquals(pages.Id().getStatus(), "Provided");
		pages.Reference().referenceone();
		sf.assertEquals(pages.Reference().getStatusColor(), "rgba(0, 128, 0, 1)");
		sf.assertEquals(pages.Reference().getStatus(), "Provided");
		pages.Basic().Declaration();
		sf.assertAll();
	}
	@Test(priority = 3, enabled = true, dependsOnMethods="CandidatedataEntry")
	public void Address() throws Exception {
		driver.get(config.getProperty("url")+"/clientLogin.aspx");
		pages.Login().userLogin(config.getProperty("clientuname"), config.getProperty("clientpass"));
		VerifyCandidateDetails details= new VerifyCandidateDetails(driver, logger);
		details.verifyvandidatedetails();
		refno=pages.DbConnection().getLastrefno(candidateName, lastName);
		details.OpenCase(refno);
		Map<String, String> actual =pages.Address().getCurrentAddress();
		Map<String, String> expected =pages.Address().filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 4, enabled = true, dependsOnMethods="CandidatedataEntry")
	public void Education() throws Exception {
		Map<String, String> actual =pages.Education().getugone();
		Map<String, String> expected =pages.Education().filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 5, enabled = true, dependsOnMethods="CandidatedataEntry")
	public void Employment() throws Exception {
		Map<String, String> actual =pages.Employment().CurrentEmp();
		Map<String, String> expected =pages.Employment().filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 6, enabled = true, dependsOnMethods="CandidatedataEntry")
	public void VoterId() throws Exception {
		Map<String, String> actual =pages.Id().VoterId();
		Map<String, String> expected =pages.Id().filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 7, enabled = true, dependsOnMethods="CandidatedataEntry")
	public void Reference() throws Exception {
		Map<String, String> actual=pages.Reference().Referenceone();
		Map<String, String> expected=pages.Reference().filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 8, enabled = true, dependsOnMethods="CandidatedataEntry")
	public void ClientSubmit() throws Exception {
		VerifyCandidateDetails details= new VerifyCandidateDetails(driver, logger);
		details.Home();
		details.QuickSubmit();
		details.FilterComponents();
		System.out.println(details.confirmAlert());
	}
	
	

	/**
	 * Takes test Result as input and Log the results into reports
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
			//pages.Utill().GoTo("http://192.168.2.17:97/Web/dashboard.aspx");
		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	/**
	 * close the drivers
	 */
	@AfterTest(alwaysRun = true)
	public void teardown() throws Exception {
		System.out.println("----------------------------------------------");
		System.out.println(refno);
//		if (driver != null)
//			pages.Utill().closeAllBrowsers();
	}

	/**
	 * Generates the report
	 */
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		extent.flush();
	}

	

}
