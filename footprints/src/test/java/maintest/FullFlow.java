package maintest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
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

import dashboard.DataEntrySupervision;
import environment.*;

public class FullFlow extends Design{

	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String ClientName = null;
	protected String ContractName = null;
	protected String ProjectName = null;
	protected String CandidateName = null;
	protected String CandidateId = null;
	protected String lastname = null;
	protected String refno = null;
	protected String uname = null;

	/**
	 * Initializing extent report objects
	 */
	@BeforeSuite(alwaysRun = true)
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/FullFlow.html");
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
		driver = BaseClass.getDriver();
		config = BaseClass.getlocator();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(config.getProperty("url"));
		ClientName = config.getProperty("clientname");
		ProjectName = config.getProperty("projectname");
		ContractName = config.getProperty("contractname");
//		uname = config.getProperty("uname");

	}

	/**
	 * Initializing extent report object with test case logger
	 */
	@BeforeMethod(alwaysRun = true)
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
	/**
	 * case registration
	 * @throws Exception WebDriverException
	 */
	@Test(priority=2, enabled=true, dependsOnMethods="Login")
	public void caseregistration() throws Exception {
		pages.Home().clickRegister();
		CandidateName = pages.Utill().candidateName();
		CandidateId = Integer.toString(pages.Utill().candidateid());
		lastname = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", CandidateName);
		datas.put("CandidateId", CandidateId);
		datas.put("ClientName", ClientName);
		datas.put("ProjectName", ProjectName);
		datas.put("lastname", lastname);
		pages.CaseRegistration().registercase(datas, false);
		String [] components =pages.CaseRegistration().getcomponents();
		for (int i = 0; i < components.length; i++) {
			pages.CaseRegistration().selectcheck(components[i].toString());
		}
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
	}
	/**
	 * data entry assign 
	 * @throws Exception WebDriverException
	 */
	@Test(priority=3, enabled=true, dependsOnMethods="caseregistration")
	public void dataEntryAssign() throws Exception{
		refno = pages.DbConnection().getLastrefno(ProjectName);
		DataEntrySupervision des=pages.DataEntrySupervision();
		des.datanentrysupervision();
		des.assigngetnext(refno);
	}
	@Test(priority=4, enabled=true, dependsOnMethods="dataEntryAssign")
	public void dataEntry() throws Exception{
		pages.DataEntry().datanentry();
		pages.Utill().click_element("//*[text()='"+refno+"']");
		pages.Utill().wait_until_loader_is_invisible(100);
		HashMap<String, String> casedetails =pages.DbConnection().getLastCase(ProjectName);
		System.out.println(casedetails);
		assertEquals(casedetails.get("firstname"), pages.CaseInformation().FirstName());
		assertEquals(casedetails.get("lastname"), pages.CaseInformation().LastName());
		pages.DeAddress().CurrentAddress();
		pages.DeAddress().sameascurrent("Permanent","Current Address");
		pages.DeEducation().twelveth();
		pages.DeEducation().UGone();
		pages.DeEmployment().currentEmployment();
		pages.DeEmployment().perviousoneEmployment();
		pages.DeReference().referenceone();
		pages.DeDatabase().database();
		pages.DeCriminal().CurrentAddress("Address -Current Address");
		pages.DeCriminal().PermanentAddress("Address -Permanent");
		pages.DeCredit().Creditone();
		pages.DeCourt().CurrentAddress("Address - Current Address");
		pages.DeCourt().PermanentAddress("Address - Permanent");
		pages.DeId().Passport();
		pages.DeId().AadharCard();
		
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
//			driver.quit();
	}

	/**
	 * Generates the report
	 */
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		extent.flush();
	}

}
