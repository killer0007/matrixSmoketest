package maintest;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

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
import actions.CaseOwnerInsuffClear;
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
	protected String uname = null;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/SPcasereg.html");
		reporter.config().setDocumentTitle("service provider case registration");
		reporter.config().setReportName("Gopinath");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@BeforeTest(alwaysRun = true)
	public void beforetest() throws FileNotFoundException, IOException {
		driver = BaseClass.getDriver();

		config = BaseClass.getlocator();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(config.getProperty("url"));
		ClientName = config.getProperty("clientname");
		ProjectName = config.getProperty("projectname");
		ContractName = config.getProperty("contractname");

	}

	@BeforeMethod(alwaysRun = true)
	public void setup(Method method) throws FileNotFoundException, IOException {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver, logger);
	}

	@Test(priority = 1, enabled = true, groups = { "smoketest","smoketest", "spcase registration", "insuff" })
	public void Login() throws Exception {
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));

	}

	@Test(priority = 2, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_001() {
		pages.Home().clickRegister();
		String title = pages.CaseRegistration().getTitle();
		if (title.equals("Case Registration")) {
			assertTrue(true);
		} else {
			assertTrue(title, false);
		}
	}

	@Test(priority = 3, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_002() {
		pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlClient", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 4, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_003() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlProject", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 5, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_004() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlContract", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 6, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_005() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtFirstName",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 7, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_006() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtLastName", "border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 8, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_007() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtFatherFirstName",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(result, false);
		}
	}

	@Test(priority = 9, enabled = true, groups = { "spcase registration" })
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

	@Test(priority = 10, enabled = true, groups = { "spcase registration" })
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
	@Test(priority = 11, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_010() throws Exception {
		// pages.Home().clickRegister();
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
	@Test(priority = 12, enabled = true, groups = { "spcase registration" })
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
	@Test(priority = 13, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_012() throws Exception {
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
	@Test(priority = 14, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_013() throws Exception {
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
	@Test(priority = 15, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_014() throws Exception {
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
	@Test(priority = 16, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_015() throws Exception {
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
	@Test(priority = 17, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_016() throws Exception {
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
	@Test(priority = 18, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_017() throws Exception {
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
	@Test(priority = 19, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_018() throws Exception {
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
	@Test(priority = 20, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_019() throws Exception {
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
	@Test(priority = 21, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_020() throws Exception {
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
	@Test(priority = 22, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_021() throws Exception {
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
	@Test(priority = 23, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_022() throws Exception {
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
	@Test(priority = 24, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_023() throws Exception {
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
		List<String> contract = pages.DbConnection().getcontractdetails(ContractName);
		int count = pages.CaseRegistration().getCheckCount();
		if (contract.size() == count) {
			assertTrue(true);
		} else {
			assertTrue(Integer.toString(count), false);
		}

	}

	// To check compenent displayed in registration screen
	@Test(priority = 25, enabled = true, groups = { "smoketest", "spcase registration" })
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

	// To check component list when fresher is Yes
	@Test(priority = 26, enabled = true, groups = { "smoketest", "spcase registration" })
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
		// List<String> contract =
		// pages.DbConnection().getcontractdetails(ContractName);
		pages.Home().homepage();
		if (contract.containsAll(components)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	// To check alert message for employment check when frehser is yes
	@Test(priority = 27, enabled = true, groups = { "smoketest", "spcase registration" })
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
		String msg = pages.Utill().confirmAlert();
		pages.Home().homepage();
		if (msg.equals("Please Uncheck Employment Checks !")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}

	}

	@Test(priority = 28, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_027() throws Exception {
		HashMap<String, String> data = pages.DbConnection().getLastCase(ProjectName);
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
		if (msg.equals("Case Already Exist!")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}

	}

	// To check insuff raised case showing in clear queue
	@Test(priority = 29, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_001() throws Exception {
		CaseOwnerInsuffClear cs = pages.CaseOwnerInsuffClear();
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
		pages.CaseRegistration().selectcheck("Permanent");
		pages.CaseRegistration().selectcheck("Current Address");
		pages.CaseRegistration().raiseInsuff("Permanent", "need address proof");
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().clickActions();
		cs.caseOwner();
		refno = pages.DbConnection().getLastrefno(ProjectName);
		cs.search(refno, "sp");
		String no = cs.getrefNo();
		if (refno.equals(no)) {
			assertTrue(true);
		} else {
			assertTrue(refno + " not found", false);
		}
	}

	// To check insuff cleared components moved to case registration
	@Test(priority = 30, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_002() throws Exception {
		CaseOwnerInsuffClear cs = pages.CaseOwnerInsuffClear();
		cs.openCase();
		cs.insuffClear("Permanent", "clear comments");
		pages.Utill().confirmAlert();
		pages.Home().workStage();
		pages.DataEntrySupervision().datanentrysupervision();
		pages.DataEntrySupervision().assign(refno, uname);
		pages.DcaseRegistration().caseRegistration();
		Thread.sleep(1500);
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	// To check already moved components are whether editable or not in case
	// registration
	@Test(priority = 31, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_003() throws Exception {
		assertTrue(pages.CaseRegistration().isSelected("Current Address"));
		assertTrue(!pages.CaseRegistration().isEnabled("Current Address"));
	}

	// To check is able to add additional component in case registration
	@Test(priority = 32, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_004() throws Exception {
		pages.CaseRegistration().selectcheck("Current/Latest Employment");
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		assertTrue(true);
	}

	// To check newly added check moved to team member queue
	@Test(priority = 33, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_005() throws Exception {
		SoftAssert sf = new SoftAssert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		sf.assertEquals(pages.CaseTracker().getCurrentStage(refno, "Current/Latest Employment"), "Data Entry Pending");
		sf.assertEquals(pages.CaseTracker().responsiblePerson("Current/Latest Employment"), uname);
		pages.CaseTracker().cancel();
		pages.DataEntry().datanentry();
		sf.assertEquals(pages.DataEntry().getSearchResult(refno), refno);
		pages.CaseRegistration().casereg();
		sf.assertAll();

	}

	@Test(priority = 34, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_006() throws Exception {
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
		pages.CaseRegistration().selectcheck("Permanent");
		pages.CaseRegistration().selectcheck("Current Address");
		pages.CaseRegistration().raiseInsuff("Permanent", "need address proof");
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DbConnection().getLastrefno(ProjectName);
		pages.Home().CaseTracker();
		assertEquals(pages.CaseTracker().getCurrentStage(refno, "Permanent"),
				"Insuff Raised - Case Registration Pending");
		pages.CaseTracker().cancel();

	}

	@Test(priority = 35, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_007() throws Exception {
		CaseOwnerInsuffClear cs = pages.CaseOwnerInsuffClear();
		pages.Home().clickActions();
		cs.caseOwner();
		cs.search(refno, "sp");
		String no = cs.getrefNo();
		if (refno.equals(no)) {
			cs.openCase();
			cs.insuffClear("Permanent", "clear comments");
			pages.Utill().confirmAlert();
			pages.Home().workStage();
			pages.Home().CaseTracker();
			String status = pages.CaseTracker().getCurrentStage(refno, "Permanent");
			pages.CaseTracker().cancel();
			assertEquals(status, "Case Registration Pending");
		} else {
			assertEquals(refno, no);
		}

	}

	@Test(priority = 36, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_008() throws Exception {

		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		assertTrue(pages.CaseRegistration().isSelected("Current Address"));
		assertTrue(!pages.CaseRegistration().isEnabled("Current Address"));
	}

	@Test(priority = 37, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_009() throws Exception {

		pages.CaseRegistration().selectcheck("Current/Latest Employment");
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		assertTrue(true);
	}

	@Test(priority = 38, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_010() throws Exception {
		SoftAssert sf = new SoftAssert();
		pages.Home().homepage();
		pages.Home().CaseTracker();
		sf.assertEquals(pages.CaseTracker().getCurrentStage(refno, "Current/Latest Employment"),
				"Data Entry Assignment Pending");
		sf.assertEquals(pages.CaseTracker().responsiblePerson("Current/Latest Employment"), "Team Leader");
		pages.CaseTracker().cancel();
		pages.DataEntrySupervision().datanentrysupervision();
		sf.assertEquals(pages.DataEntrySupervision().getSearchResult(refno), refno);
		pages.CaseRegistration().casereg();
		sf.assertAll();

	}

	// check insuff raise comments for address check
	@Test(priority = 39, enabled = true, groups = {"insuff", "smoketest" })
	public void TC_SPINF_011() throws Exception {
		String[] checks = { "Current Address", "UG1", "Current/Latest Employment", "Reference 1", "Aadhaar Card",
				"Current Address Criminal Check", "Current Address Court Check", "Credit Check 1", "Panel1",
				"Database" };
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
		for (int i = 0; i < checks.length; i++) {
			pages.CaseRegistration().selectcheck(checks[i].toString());
			pages.CaseRegistration().raiseInsuff(checks[i], "insuff " + checks[i]);
		}
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DbConnection().getLastrefno(ProjectName);
//			refno="HDFC000308";
		pages.Home().clickActions();
		pages.CaseOwnerInsuffClear().search(refno, "sp");
		pages.CaseOwnerInsuffClear().openCase();
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Current Address"), "insuff Current Address");

	}

	// check insuff raise comments for education check
	@Test(priority = 40, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_014() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("UG1"), "insuff UG1");
	}

	// check insuff raise comments for employment check
	@Test(priority = 41, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_017() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Current/Latest Employment"),
				"insuff Current/Latest Employment");
	}

	// check insuff raise comments for reference check
	@Test(priority = 42, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_020() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Reference 1"), "insuff Reference 1");
	}

	// check insuff raise comments for id check
	@Test(priority = 43, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_023() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Aadhaar Card"), "insuff Aadhaar Card");
	}

	// check insuff raise comments for criminal check
	@Test(priority = 44, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_026() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Current Address Criminal Check"),
				"insuff Current Address Criminal Check");
	}

	// check insuff raise comments for court check
	@Test(priority = 45, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_029() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Current Address Court Check"),
				"insuff Current Address Court Check");
	}

	// check insuff raise comments for credit check
	@Test(priority = 46, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_032() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Credit Check 1"), "insuff Credit Check 1");
	}

	// check insuff raise comments for drug check
	@Test(priority = 47, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_035() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Panel1"), "insuff Panel1");
	}

	// check insuff raise comments for db check
	@Test(priority = 48, enabled = true, groups = { "insuff", "smoketest" })
	public void TC_SPINF_038() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Database"), "insuff Database");
		pages.CaseOwnerInsuffClear().cancel();
	}

	// for insuff clear and check clear comments in address
	@Test(priority = 49, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_12() throws Exception {
		pages.Home().workStage();
		String[] checks = { "Current Address", "UG1", "Current/Latest Employment", "Reference 1", "Aadhaar Card",
				"Current Address Criminal Check", "Current Address Court Check", "Credit Check 1", "Panel1",
				"Database" };
//		refno = "HDFC000308";
		pages.Home().clickActions();
		pages.CaseOwnerInsuffClear().search(refno, "sp");
		pages.CaseOwnerInsuffClear().openCase();
		for (int i = 0; i < checks.length; i++) {
			String name = checks[i].toString();
			pages.CaseOwnerInsuffClear().clearComments(name, name + " clear");
		}
		pages.CaseOwnerInsuffClear().clear();
		pages.Utill().confirmAlert();
		pages.Home().workStage();
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().wait_until_loader_is_invisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		// assing
		pages.DataEntrySupervision().datanentrysupervision();
		pages.DataEntrySupervision().assign(refno, "demoempl");
		pages.DataEntry().datanentry();
		pages.DataEntry().search(refno);
		pages.DataEntry().selectcase(refno);
		pages.DeAddress().addresscheck();
		pages.DeAddress().history();
//		System.out.println(pages.DataEntry().getraisedBy());
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeAddress().getraisedBy(), uname);
		sf.assertEquals(pages.DeAddress().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeAddress().getraisedComments(), "insuff Current Address");
		sf.assertEquals(pages.DeAddress().getclearedBy(), uname);
		sf.assertEquals(pages.DeAddress().getclearedComments(), "Current Address clear");
		pages.DeAddress().close();
		sf.assertAll();
	}

	// education clear comments
	@Test(priority = 50, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_15() throws Exception {

		pages.DeEducation().educationcheck();
		pages.DeEducation().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeEducation().getraisedBy(), uname);
		sf.assertEquals(pages.DeEducation().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeEducation().getraisedComments(), "insuff UG1");
		sf.assertEquals(pages.DeEducation().getclearedBy(), uname);
		sf.assertEquals(pages.DeEducation().getclearedComments(), "UG1 clear");
		pages.DeEducation().close();
		sf.assertAll();
	}

	// employment clear comments
	@Test(priority = 51, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_18() throws Exception {
		pages.DeEmployment().employementcheck();
		pages.DeEmployment().history();
		SoftAssert sf = new SoftAssert();
		try {
		sf.assertEquals(pages.DeEmployment().getraisedBy(), uname);
		sf.assertEquals(pages.DeEmployment().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeEmployment().getraisedComments(), "insuff Current/Latest Employment");
		sf.assertEquals(pages.DeEmployment().getclearedBy(), uname);
		sf.assertEquals(pages.DeEmployment().getclearedComments(), "Current/Latest Employment clear");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			pages.DeEmployment().close();
			sf.assertAll();
		}
		
	}

	// reference clear comments
	@Test(priority = 52, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_21() throws Exception {
		pages.DeReference().referencecheck();
		pages.DeReference().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeReference().getraisedBy(), uname);
		sf.assertEquals(pages.DeReference().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeReference().getraisedComments(), "insuff Reference 1");
		sf.assertEquals(pages.DeReference().getclearedBy(), uname);
		sf.assertEquals(pages.DeReference().getclearedComments(), "Reference 1 clear");
		pages.DeReference().close();
		sf.assertAll();
	}

	// database clear comments
	@Test(priority = 53, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_38() throws Exception {
		pages.DeDatabase().databasecheck();
		pages.DeDatabase().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeDatabase().getraisedBy(), uname);
		sf.assertEquals(pages.DeDatabase().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeDatabase().getraisedComments(), "insuff Database");
		sf.assertEquals(pages.DeDatabase().getclearedBy(), uname);
		sf.assertEquals(pages.DeDatabase().getclearedComments(), "Database clear");
		pages.DeDatabase().close();
		sf.assertAll();
	}

	// criminal clear comments
	@Test(priority = 54, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_27() throws Exception {
		pages.DeCriminal().criminalcheck();
		pages.DeCriminal().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeCriminal().getraisedBy(), uname);
		sf.assertEquals(pages.DeCriminal().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeCriminal().getraisedComments(), "insuff Current Address Criminal Check");
		sf.assertEquals(pages.DeCriminal().getclearedBy(), uname);
		sf.assertEquals(pages.DeCriminal().getclearedComments(), "Current Address Criminal Check clear");
		pages.DeCriminal().close();
		sf.assertAll();
	}

	// credit clear comments
	@Test(priority = 55, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_33() throws Exception {
		pages.DeCredit().creditcheck();
		pages.DeCredit().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeCredit().getraisedBy(), uname);
		sf.assertEquals(pages.DeCredit().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeCredit().getraisedComments(), "insuff Credit Check 1");
		sf.assertEquals(pages.DeCredit().getclearedBy(), uname);
		sf.assertEquals(pages.DeCredit().getclearedComments(), "Credit Check 1 clear");
		pages.DeCredit().close();
		sf.assertAll();
	}

	// court clear comments
	@Test(priority = 56, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_30() throws Exception {
		pages.DeCourt().courtcheck();
		pages.DeCourt().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeCourt().getraisedBy(), uname);
		sf.assertEquals(pages.DeCourt().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeCourt().getraisedComments(), "insuff Current Address Court Check");
		sf.assertEquals(pages.DeCourt().getclearedBy(), uname);
		sf.assertEquals(pages.DeCourt().getclearedComments(), "Current Address Court Check clear");
		pages.DeCourt().close();
		sf.assertAll();
	}

	// id clear comments
	@Test(priority = 57, enabled = true, groups = { "smoketest", "insuff" })
	public void TC_SPINF_24() throws Exception {
		pages.DeId().idcheck();
		pages.DeId().history();
		SoftAssert sf = new SoftAssert();
		try {
		sf.assertEquals(pages.DeId().getraisedBy(), uname);
		sf.assertEquals(pages.DeId().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeId().getraisedComments(), "insuff Aadhaar Card");
		sf.assertEquals(pages.DeId().getclearedBy(), uname);
		sf.assertEquals(pages.DeId().getclearedComments(), "Aadhaar Card clear");
		pages.DeId().close();
		}
		catch (Exception e) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		finally {
			pages.Home().homepage();
			sf.assertAll();	
		}
		
	}

	@AfterMethod(alwaysRun = true)
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

	@AfterTest(alwaysRun = true)
	public void teardown() throws Exception {
		// pages.Home().Logout();
		// Thread.sleep(10000);
		// driver.close();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		extent.flush();
		// driver.quit();

	}
}
