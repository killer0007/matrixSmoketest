package maintest;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;
import pages.CaseRegistration;

public class SPcasereg extends Design {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String ClientName;
	protected String ProjectName;
	protected String CandidateName;
	protected String CandidateId;
	protected String lastname;

	@BeforeSuite
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/SPcasereg.html");
		reporter.config().setDocumentTitle("service provider case registration");
		reporter.config().setReportName("Gopinath");
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
		ClientName=config.getProperty("clientname");
		ProjectName=config.getProperty("projectname");
		

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
		// pages.Home().selectRole("Demo Role");
		pages.Home().clickRegister();
		// pages.CaseRegistration().selectClient("Demo Client1234");
		// pages.CaseRegistration().selectProject("demo client1234");
		//
		// pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAddComponent_input");
		//
		// AlokPurohit Client Project
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_001() {
		String title = pages.CaseRegistration().getTitle();
		if (title.equals("Case Registration")) {
			assertTrue(true);
		} else {
			assertTrue(title, false);
		}
	}

	@Test(priority = 3, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_002() {
		pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlClient", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 4, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_003() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlProject", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 5, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_004() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlContract", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 6, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_005() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtFirstName",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 7, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_006() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtLastName", "border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 8, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_007() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtFatherFirstName",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 9, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_008() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtFatherLastName",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 10, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_009() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtClientCandidateID",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}
	@Test(priority=11, enabled=true, dependsOnMethods="Login")
	public void TC_SPCR_010() throws Exception {
		CandidateName=pages.Utill().candidateName();
		CandidateId=Integer.toString(pages.Utill().candidateid());
		lastname=pages.Utill().candidateName();
		HashMap<String, String> data= new HashMap<String, String>();
		data.put("CandidateName", CandidateName);
		data.put("CandidateId", CandidateId);
		data.put("ClientName", ClientName);
		data.put("ProjectName", ProjectName);
		data.put("lastname", lastname);
	pages.CaseRegistration().registercase(data, false);	
//	driver.findElement(By.xpath("//td[text()='Permanent']/../td[2]//input")).click();
		List<String> contract =pages.DbConnection().getcontractdetails("demo automation");
		for (int i = 0; i < contract.size(); i++) {
			pages.CaseRegistration().selectcheck(contract.get(i).toString());
		}
		
//		CaseRegistration casereg =pages.CaseRegistration();
//		casereg.selectClient(ClientName);
//		casereg.selectProject(ProjectName);
//		casereg.FirstName(CandidateName);
//		casereg.LastName(lastname);
//		casereg.DOB("18/04/1995");
//		casereg.gender("male");
//		casereg.Email(CandidateName+"ggmail.com");
//		casereg.FatherFirstName("fname");
//		casereg.FatherLastName("lname");
//		casereg.LinkedIn(CandidateName+" linkedin");
//		casereg.maritalStatus("Single");
//		casereg.Nationality("Indian");
//		casereg.LandlineNumber(pages.Utill().mobileno());
//		casereg.MobileNumber(pages.Utill().mobileno());
//		casereg.EmergencyContactNumber(pages.Utill().mobileno());
//		casereg.EmergencyContactPerson(CandidateName);
//		casereg.CandidateID(CandidateId);
//		casereg.clickfresher(false);
//		casereg.addEditComponent();
//		
		
		
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
//		pages.Home().Logout();
		// Thread.sleep(10000);
//		driver.close();
	}

	@AfterSuite
	public void afterSuite() {

		extent.flush();
		// driver.quit();

	}
	// private String getvalue(String key) throws FileNotFoundException, IOException
	// {
	// Properties pr = new Properties();
	// pr.load(new FileInputStream(new File("./config.properties")));
	// return pr.getProperty(key);
	// }
}
