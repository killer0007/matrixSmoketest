package maintest;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
import verification.VerificationInitiate;

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
	private String [] component = {"Current Address","UG1","Current/Latest Employment","Voter ID","Reference 1",};
	private List<String> components=new ArrayList<>(Arrays.asList(component));
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
		pages.Home().Logout();
		
	}
	@Test(priority = 9, enabled = true, dependsOnMethods="ClientSubmit")
	public void DataEntryQC() throws Exception {
		driver.get(config.getProperty("url"));
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		pages.DataEntryQCSupervision().datanentryqcsupervision();
		pages.DataEntryQCSupervision().assign(refno, "demoempl");
		pages.DataEntryQC().datanentryqc();
		pages.DataEntryQC().selectcase(refno);
		pages.DeAddress().addresscheck();
		pages.DeAddress().submit();
		pages.DeEducation().educationcheck();
		pages.DeEducation().submit();
		pages.DeEmployment().employmentcheck();
		pages.DeEmployment().submit();
		pages.DeReference().referencecheck();
		pages.DeReference().submit();
		pages.DeId().idcheck();
		pages.DeId().submit();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> details=pages.CaseTracker().getcasedata();
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < details.size(); i++) {
			String name=details.get(i).get("ComponentName").toString().trim();
			if(components.contains(name)) {
				if(name.equals("Current Address")) 
					sf.assertEquals(details.get(i).get("CurrentStage").toString().trim(), "Candidate Flow Before Verification Initiation");
				else 
					sf.assertEquals(details.get(i).get("CurrentStage").toString().trim(), "Verification Assignment Pending");
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}
	@Test(priority = 9, enabled = true, dependsOnMethods="ClientSubmit")
	public void VerificationSupervisor() throws Exception {
		pages.VerificationSupervisor().verificationsupervisor();
		pages.VerificationSupervisor().assign(refno, "demoempl");
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> details=pages.CaseTracker().getcasedata();
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < details.size(); i++) {
			String name=details.get(i).get("ComponentName").toString().trim();
			if(components.contains(name)) {
				if(name.equals("Current Address")) 
					sf.assertEquals(details.get(i).get("CurrentStage").toString().trim(), "Candidate Flow Before Verification Initiation");
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}
	@Test(priority = 10, enabled = true, dependsOnMethods="VerificationSupervisor")
	public void VerificationIntiation() throws Exception {
		VerificationInitiate ver= new VerificationInitiate(driver,logger);
		Map<String, String> data=mode();
		pages.Verification().verification();
		pages.Home().Logout();
		pages.Login().userLogin("demov", "Paws@123");
		pages.Verification().verification();
		ver.Initiate(refno, "Current Address", data.get("Current Address"));
		pages.Home().Logout();
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		pages.Verification().verification();
		ver.Initiate(refno, "UG1", data.get("UG1"));
		ver.Initiate(refno, "Current/Latest Employment", data.get("Current/Latest Employment"));
		ver.Initiate(refno, "Reference 1", data.get("Reference 1"));
		ver.Initiate(refno, "Voter ID", data.get("Voter ID"));
		List<HashMap<String, String>> details=pages.CaseTracker().getcasedata();
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < details.size(); i++) {
			String name=details.get(i).get("ComponentName").toString().trim();
			if(components.contains(name)) {
				if(name.equals("Current Address")) 
					sf.assertEquals(details.get(i).get("CurrentStage").toString().trim(), "Candidate Flow After Verification Initiation");
			}
		}
		pages.CaseTracker().closeTab();
		sf.assertAll();
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

	private static Map<String, String> mode(){
		Map<String, String> map = new HashMap<>();
		map.put("Permanent", "In Person");
		map.put("Current Address", "In Person");
		map.put("12th", "Email (Preffered)");
		map.put("UG1", "Email (Preffered)");
		map.put("Current/Latest Employment", "Email");
		map.put("Previous Employment", "Email");
		map.put("Reference 1", "Phone");
		map.put("Current Address Criminal Check", "In Person");
		map.put("Permanent Criminal Check", "In Person");
		map.put("Current Address Court Check", "In Person");
		map.put("Permanent Court Check", "In Person");
		map.put("Database", "Online");
		map.put("Credit Check 1", "Online");
		map.put("Passport", "Online");
		map.put("Aadhaar Card", "Online");
		map.put("Panel1", "In Person");
		return map;
	}

}
