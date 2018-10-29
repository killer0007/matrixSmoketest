package maintest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import dataEntryQC.*;
import environment.*;
@Listeners(environment.Listener.class)
public class FullFlow extends Design {

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
	 * 
	 * @throws Exception WebDriverException
	 */
	@Test(priority = 2, enabled = true, dependsOnMethods = "Login")
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
		String[] components = pages.CaseRegistration().getcomponents();
		for (int i = 0; i < components.length; i++) {
			pages.CaseRegistration().selectcheck(components[i].toString());
		}
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
	}

	/**
	 * data entry assign
	 * 
	 * @throws Exception WebDriverException
	 */
	@Test(priority = 3, enabled = true, dependsOnMethods = "caseregistration")
	public void dataEntryAssign() throws Exception {
		refno = pages.DbConnection().getLastrefno(ProjectName);
		DataEntrySupervision des = pages.DataEntrySupervision();
		des.datanentrysupervision();
		des.assigngetnext(refno);
	}

	@Test(priority = 4, enabled = true, dependsOnMethods = "dataEntryAssign")
	public void dataEntry() throws Exception {
		pages.DataEntry().datanentry();
		pages.Utill().click_element("//*[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
		HashMap<String, String> casedetails = pages.DbConnection().getLastCase(ProjectName);
		logger.log(Status.INFO, casedetails.toString());
		assertEquals(casedetails.get("firstname"), pages.CaseInformation().FirstName());
		assertEquals(casedetails.get("lastname"), pages.CaseInformation().LastName());
		pages.DeAddress().CurrentAddress();
		pages.DeAddress().sameascurrent("Permanent", "Current Address");
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
		pages.Utill().wait_until_loader_is_invisible(100);

	}

	@Test(priority = 5, enabled = true, dependsOnMethods = "dataEntry")
	public void dataEntryQCAssign() throws Exception {
		pages.DataEntryQCSupervision().datanentryqcsupervision();
		pages.DataEntryQCSupervision().assigngetnext(refno);
		pages.DataEntryQC().datanentryqc();
		pages.DataEntryQC().selectcase(refno);

	}

	@Test(priority = 6, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void AddressDEQC() throws Exception {
		Address add = new Address(logger);
		add.addresscheck();
		LinkedHashMap<String, String> actual = add.CurrentAddress();
		LinkedHashMap<String, String> expected = add.filedata("Current Address");
		add.addresscheck();
		LinkedHashMap<String, String> Peractual = add.PermanentAdress();
		LinkedHashMap<String, String> Perexpected = add.filedata("Permanent");
		pages.Utill().SwitchDefault();
		if (actual.equals(expected) && Peractual.equals(Perexpected)) {
			logger.log(Status.PASS, actual.toString());
			logger.log(Status.PASS, Peractual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			logger.log(Status.FAIL, Peractual.toString());
			assertTrue(false, actual.toString());
		}
	}

	@Test(priority = 7, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void EducationDEQC() throws Exception {
		Education edu = new Education(logger);
		edu.educationcheck();
		LinkedHashMap<String, String> actual = edu.twelveth();
		LinkedHashMap<String, String> expected = edu.filedata("12th");
		edu.educationcheck();
		LinkedHashMap<String, String> Peractual = edu.ugone();
		LinkedHashMap<String, String> Perexpected = edu.filedata("UG1");
		pages.Utill().SwitchDefault();
		if (actual.equals(expected) && Peractual.equals(Perexpected)) {
			logger.log(Status.PASS, actual.toString());
			logger.log(Status.PASS, Peractual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			logger.log(Status.FAIL, Peractual.toString());
			assertTrue(false, actual.toString());
		}
	}

	@Test(priority = 8, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void EmploymentDEQC() throws Exception {
		Employment emp = new Employment(logger);
		emp.employmentcheck();
		LinkedHashMap<String, String> actual = emp.CurrentEmp();
		LinkedHashMap<String, String> expected = emp.filedata("Current/Latest Employment");
		emp.employmentcheck();
		LinkedHashMap<String, String> Peractual = emp.PerviousEmp();
		LinkedHashMap<String, String> Perexpected = emp.filedata("Previous Employment");
		pages.Utill().SwitchDefault();
		if (actual.equals(expected) && Peractual.equals(Perexpected)) {
			logger.log(Status.PASS, actual.toString());
			logger.log(Status.PASS, Peractual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			logger.log(Status.FAIL, Peractual.toString());
			assertTrue(false, actual.toString());
		}
	}

	@Test(priority = 9, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void ReferenceDEQC() throws Exception {
		Reference ref = new Reference(logger);
		ref.referencecheck();
		LinkedHashMap<String, String> actual = ref.Referenceone();
		LinkedHashMap<String, String> expected = ref.filedata();
		pages.Utill().SwitchDefault();
		if (actual.equals(expected)) {
			logger.log(Status.PASS, actual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			assertTrue(false, actual.toString());
		}
	}

	@Test(priority = 10, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void DataBaseDEQC() throws Exception {
		Database db = new Database(logger);
		db.databasecheck();
		LinkedHashMap<String, String> actual = db.database();
		LinkedHashMap<String, String> expected = db.filedata();
		pages.Utill().SwitchDefault();
		if (actual.equals(expected)) {
			logger.log(Status.PASS, actual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			assertTrue(false, actual.toString());
		}
	}

	@Test(priority = 11, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void CriminalDEQC() throws Exception {
		Criminal criminal = new Criminal(logger);
		criminal.criminalcheck();
		LinkedHashMap<String, String> actual = criminal.CurrentAddress();
		LinkedHashMap<String, String> Perexpected = criminal.filedata("Permanent Criminal Check");
		criminal.criminalcheck();
		LinkedHashMap<String, String> Peractual = criminal.PermanentAdress();	
		LinkedHashMap<String, String> expected = criminal.filedata("Current Address Criminal Check");
		pages.Utill().SwitchDefault();
		if (actual.equals(expected) && Peractual.equals(Perexpected)) {
			logger.log(Status.PASS, actual.toString());
			logger.log(Status.PASS, Peractual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			logger.log(Status.FAIL, Peractual.toString());
			assertTrue(false, actual.toString());
		}
	}

	@Test(priority = 12, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void CreditDEQC() throws Exception {
		Credit credit = new Credit(logger);
		credit.creditcheck();
		LinkedHashMap<String, String> actual = credit.credit();
		LinkedHashMap<String, String> expected = credit.filedata();
		pages.Utill().SwitchDefault();
		if (actual.equals(expected)) {
			logger.log(Status.PASS, actual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			assertTrue(false, actual.toString());
		}
	}

	@Test(priority = 13, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void CourtDEQC() throws Exception {
		Court court = new Court(logger);
		court.courtcheck();
		LinkedHashMap<String, String> actual = court.CurrentAddress();
		LinkedHashMap<String, String> expected = court.filedata("Current Address Court Check");	
		court.courtcheck();
		LinkedHashMap<String, String> Peractual = court.PermanentAdress();
		LinkedHashMap<String, String> Perexpected = court.filedata("Permanent Court Check");
		pages.Utill().SwitchDefault();
		if (actual.equals(expected) && Peractual.equals(Perexpected)) {
			logger.log(Status.PASS, actual.toString());
			logger.log(Status.PASS, Peractual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			logger.log(Status.FAIL, Peractual.toString());
			assertTrue(false, actual.toString());
		}
	}
	@Test(priority = 14, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void IdDEQC() throws Exception {
		Id id = new Id(logger);
		id.idcheck();
		LinkedHashMap<String, String> actual = id.Aadharcard();
		LinkedHashMap<String, String> expected = id.filedata("Aadhaar Card");
		id.idcheck();
		LinkedHashMap<String, String> passactual = id.PassPort();
		LinkedHashMap<String, String> passexpected = id.filedata("Passport");
		pages.Utill().SwitchDefault();
		if (actual.equals(expected) && passactual.equals(passexpected)) {
			logger.log(Status.PASS, actual.toString());
			logger.log(Status.PASS, passactual.toString());
			assertTrue(true, actual.toString());
		} else {
			logger.log(Status.FAIL, actual.toString());
			logger.log(Status.FAIL, passactual.toString());
			assertTrue(false, actual.toString());
		}
	}
	@Test(priority = 15, enabled = true, dependsOnMethods = "IdDEQC")
	public void VerificationSupervisor() throws Exception {
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.VerificationSupervisor().verificationsupervisor();
		
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
		if (driver != null)
			driver.quit();
	}

	/**
	 * Generates the report
	 */
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		extent.flush();
	}

}
