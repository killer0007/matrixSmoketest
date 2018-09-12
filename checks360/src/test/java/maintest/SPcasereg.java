package maintest;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;

public class SPcasereg extends Design {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String ClientName=null;
	protected String ProjectName=null;
	protected String CandidateName=null;
	protected String CandidateId=null;
	protected String lastname=null;
	protected String refno=null;

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
		pages.Home().clickRegister();
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
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlProject", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 5, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_004() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlContract", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 6, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_005() {
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
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtLastName", "border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 8, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_007() {
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
//with all checks and fresher is no
	@Test(priority = 11, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_010() throws Exception {
		CandidateName = pages.Utill().firstName();
		CandidateId = Integer.toString(pages.Utill().candidateid());
		lastname = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", CandidateName);
		datas.put("CandidateId", CandidateId);
		datas.put("ClientName", ClientName);
		datas.put("ProjectName", ProjectName);
		datas.put("lastname", lastname);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails("demo automation");
		for (int i = 0; i < contract.size(); i++) {
			pages.CaseRegistration().selectcheck(contract.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno =pages.DcaseRegistration().getrefno(CandidateName, lastname);
		pages.Utill().click_element("//span[text()='"+refno+"']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		System.out.println(data.size());
		SoftAssert sf= new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName");
			if (name.equals("Panel1") || name.equals("Medical Test")) {
				if (data.get(i).get("CurrentStage").equals("Verification Assignment Pending")) {
				sf.assertTrue(true, "success");
				} else {
				sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
			else {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
					} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
					}
			}
		}
		List<String> checkname=pages.CaseTracker().getcomponentname(data);
		pages.CaseTracker().cancel();
		contract.removeAll(checkname);
		sf.assertTrue(contract.size()==0, "success");
		sf.assertAll();
	}
	//with all checks and fresher is yes
		@Test(priority = 12, enabled = true, dependsOnMethods = "Login")
		public void TC_SPCR_011() throws Exception {
			pages.Home().clickRegister();
			CandidateName = pages.Utill().candidateName();
			CandidateId = Integer.toString(pages.Utill().candidateid());
			lastname = pages.Utill().candidateName();
			HashMap<String, String> datas = new HashMap<String, String>();
			datas.put("CandidateName", CandidateName);
			datas.put("CandidateId", CandidateId);
			datas.put("ClientName", ClientName);
			datas.put("ProjectName", ProjectName);
			datas.put("lastname", lastname);
			pages.CaseRegistration().registercase(datas, true);
			List<String> contract = pages.DbConnection().getcontractdetails("demo automation");
			contract.remove("Current/Latest Employment");
			contract.remove("Previous Employment");
			contract.remove("Previous Employment 2");
			contract.remove("Previous Employment 3");
			contract.remove("Previous Employment 4");

			for (int i = 0; i < contract.size(); i++) {
				pages.CaseRegistration().selectcheck(contract.get(i).toString());
			}
			pages.CaseRegistration().save();
			pages.Utill().confirmAlert();
			pages.Home().homepage();
			refno =pages.DcaseRegistration().getrefno(CandidateName, lastname);
			pages.Utill().click_element("//span[text()='"+refno+"']");
			pages.Utill().wait_until_loader_is_invisible(5);
			pages.CaseRegistration().addEditComponent();
			pages.Utill().wait_until_loader_is_invisible(10);
			pages.CaseRegistration().submit();
			pages.Utill().confirmAlert();
			pages.Home().homepage();
			pages.Home().CaseTracker();
			pages.CaseTracker().clickcase(refno);
			List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
			System.out.println(data.size());
			SoftAssert sf= new SoftAssert();
			for (int i = 0; i < data.size(); i++) {
				String name = data.get(i).get("ComponentName");
				if (name.equals("Panel1") || name.equals("Medical Test")) {
					if (data.get(i).get("CurrentStage").equals("Verification Assignment Pending")) {
					sf.assertTrue(true, "success");
					} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
					}
				}
				else if (name.contains("Employment")) {
					if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
					} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
					}
				}
				else {
					if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
						sf.assertTrue(true, "success");
						} else {
						sf.assertTrue(false, data.get(i).get("ComponentName"));
						}
				}
			}
			List<String> checkname=pages.CaseTracker().getcomponentname(data);
			contract.removeAll(checkname);
			
			sf.assertTrue(contract.size()==0, "success");
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
