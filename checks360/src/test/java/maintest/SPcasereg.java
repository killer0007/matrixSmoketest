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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;

@Listeners(environment.Listener.class)
public class SPcasereg extends Design {
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
		ContractName = config.getProperty("contractname");

	}

	@BeforeMethod
	public void setup(Method method) throws FileNotFoundException, IOException {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver, logger);
	}

	@Test(priority = 1, enabled = true, groups = { "smoketest" })
	public void Login() throws Exception {
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "Login", groups = { "smoketest" })
	public void TC_SPCR_001() {
		pages.Home().clickRegister();
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

	// with all checks and fresher is no
	@Test(priority = 11, enabled = true, dependsOnMethods = "Login", groups = { "smoketest" })
	public void TC_SPCR_010() throws Exception {
		// pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		for (int i = 0; i < contract.size(); i++) {
			pages.CaseRegistration().selectcheck(contract.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
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

	// with all checks and fresher is yes
	@Test(priority = 12, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_011() throws Exception {
		refno = null;
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
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
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		System.out.println("case refference no is : " + refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
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

	// only address checks
	@Test(priority = 13, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_012() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		List<String> address = pages.Utill().getAddressChecks(contract);
		for (int i = 0; i < address.size(); i++) {
			pages.CaseRegistration().selectcheck(address.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (address.contains(name)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();

	}

	// only education checks
	@Test(priority = 14, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_013() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		List<String> education = pages.Utill().getEducationChecks(contract);
		for (int i = 0; i < education.size(); i++) {
			pages.CaseRegistration().selectcheck(education.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (education.contains(name)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// only employment
	@Test(priority = 15, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_014() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		List<String> emp = pages.Utill().getEducationChecks(contract);
		for (int i = 0; i < emp.size(); i++) {
			pages.CaseRegistration().selectcheck(emp.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (emp.contains(name)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// only reference
	@Test(priority = 16, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_015() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		List<String> ref = pages.Utill().getReferenceChecks(contract);
		for (int i = 0; i < ref.size(); i++) {
			pages.CaseRegistration().selectcheck(ref.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (ref.contains(name)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// only id
	@Test(priority = 17, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_016() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		List<String> id = pages.Utill().getIdChecks(contract);
		for (int i = 0; i < id.size(); i++) {
			pages.CaseRegistration().selectcheck(id.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (id.contains(name)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// only criminal
	@Test(priority = 18, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_017() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		List<String> criminal = pages.Utill().getCriminalChecks(contract);
		for (int i = 0; i < criminal.size(); i++) {
			pages.CaseRegistration().selectcheck(criminal.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (criminal.contains(name)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// only court
	@Test(priority = 19, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_018() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		List<String> court = pages.Utill().getCourtChecks(contract);
		for (int i = 0; i < court.size(); i++) {
			pages.CaseRegistration().selectcheck(court.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (court.contains(name)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// only drug
	@Test(priority = 20, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_019() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		List<String> drug = pages.Utill().getDrugChecks(contract);
		for (int i = 0; i < drug.size(); i++) {
			pages.CaseRegistration().selectcheck(drug.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (drug.contains(name)) {
				if (data.get(i).get("CurrentStage").equals("Verification Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// only db
	@Test(priority = 21, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_020() throws Exception {
		pages.Home().clickRegister();
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
		// List<String> contract =
		// pages.DbConnection().getcontractdetails(ContractName);
		String checkname = "Database";
		// List<String> drug = pages.Utill().getDrugChecks(contract);
		// for (int i = 0; i < drug.size(); i++) {
		pages.CaseRegistration().selectcheck(checkname);
		// }
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (name.equals(checkname)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// only credit
	@Test(priority = 22, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_021() throws Exception {
		pages.Home().clickRegister();
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
		// List<String> contract =
		// pages.DbConnection().getcontractdetails(ContractName);
		String checkname = "Credit Check 1";
		// List<String> drug = pages.Utill().getDrugChecks(contract);
		// for (int i = 0; i < drug.size(); i++) {
		pages.CaseRegistration().selectcheck(checkname);
		// }
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(CandidateName, lastname);
		logger.log(Status.INFO, refno);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		// System.out.println(data.size());
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (name.equals(checkname)) {
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else {
				if (data.get(i).get("CurrentStage").equals("Yet to start")) {
					sf.assertTrue(true, "success");
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	// To check cancel with updating all data
	@Test(priority = 23, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_022() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		for (int i = 0; i < 4; i++) {
			pages.CaseRegistration().selectcheck(contract.get(i).toString());
		}
		String before = pages.DbConnection().getLastrefno(ProjectName);
		pages.CaseRegistration().cancel();
		String after = pages.DbConnection().getLastrefno(ProjectName);
		if (before.equals(after)) {
			assertTrue(true);
		} else {
			assertTrue(after, false);
		}

	}

	// To check component count
	@Test(priority = 24, enabled = true, dependsOnMethods = "Login")
	public void TC_SPCR_023() throws Exception {
		pages.Home().clickRegister();
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		int count = pages.CaseRegistration().getCheckCount();
		if (contract.size() == count) {
			assertTrue(true);
		} else {
			assertTrue(Integer.toString(count), false);
		}

	}
	//To check compenent displayed in registration screen 
	@Test(priority = 25, enabled = true, dependsOnMethods = "TC_SPCR_023")
	public void TC_SPCR_024() throws Exception {
		List<String> components = pages.CaseRegistration().getDisplayedComponents();
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		pages.Home().homepage();
		if (contract.containsAll(components)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	//To check component list when fresher is Yes
	@Test(priority = 26, enabled = true)
	public void TC_SPCR_025() throws Exception {
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		contract.remove("Current/Latest Employment");
		contract.remove("Previous Employment");
		contract.remove("Previous Employment 2");
		contract.remove("Previous Employment 3");
		contract.remove("Previous Employment 4");
		List<String> components = pages.CaseRegistration().getDisplayedComponents();
	//	List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		pages.Home().homepage();
		if (contract.containsAll(components)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	//To check alert message for employment check when frehser is yes
	@Test(priority = 27, enabled = true)
	public void TC_SPCR_026() throws Exception {
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
		pages.CaseRegistration().registercase(datas, false);
		pages.CaseRegistration().selectcheck("Current/Latest Employment");
		pages.CaseRegistration().clickfresher(true);
		pages.CaseRegistration().save();
		String msg=pages.Utill().confirmAlert();
		pages.Home().homepage();
		if(msg.equals("Please Uncheck Employment Checks !")) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
		
		
	}
	@Test(priority = 28, enabled = true)
	public void TC_SPCR_027() throws Exception {
		HashMap<String, String> data =pages.DbConnection().getLastCase(ProjectName);
		pages.Home().clickRegister();
		CandidateName = data.get("firstname");
		CandidateId = Integer.toString(pages.Utill().candidateid());
		lastname = data.get("lastname");
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", CandidateName);
		datas.put("CandidateId", CandidateId);
		datas.put("ClientName", ClientName);
		datas.put("ProjectName", ProjectName);
		datas.put("lastname", lastname);
		datas.put("DateofBirth", data.get("DateofBirth"));
		pages.CaseRegistration().registercase(datas);
		String msg = pages.Utill().confirmAlert();
		pages.Home().homepage();
		if(msg.equals("Case Already Exist!")) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}

	}

	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			driver.get(config.getProperty("url") + "/Web/dashboard.aspx");
			pages.Utill().wait_until_loader_is_invisible(80);
		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() throws Exception {
		// pages.Home().Logout();
		// Thread.sleep(10000);
		// driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		// driver.quit();

	}
}
