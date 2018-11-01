package maintest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
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
		reporter = new ExtentHtmlReporter("./Reports/SPcasereg.html");
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
		driver.get(config.getProperty("url"));
		clientName = config.getProperty("clientname");
		projectName = config.getProperty("projectname");
		contractName = config.getProperty("contractname");
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
		pages = new Pages(driver,logger);
	}

	/**
	 * Login action
	 */
	@Test(priority = 1, enabled = true, groups = { "smoketest", "smoketest", "spcase registration", "insuff" })
	public void login() throws Exception {
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
	}

	/**
	 * To check title of page
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 2, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_001() throws Exception {
		pages.Home().clickRegister();
		String title = pages.CaseRegistration().getTitle();
		if (title.equals("Case Registration")) {
			assertTrue(true);
		} else {
			assertTrue(false, title);
		}
	}

	/**
	 * To check mandatory alert for client
	 */
	@Test(priority = 3, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_002() {
		pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlClient", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(false, result);
		}
	}

	/**
	 * To check mandatory alert for project
	 */
	@Test(priority = 4, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_003() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlProject", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(false, result);
		}
	}

	/**
	 * To check mandatory alert for contract
	 */
	@Test(priority = 5, enabled = true, groups = { "spcase registration" })
	public void TC_SCPR_004() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_ddlContract", "border");
		if (result.equals("1px solid rgb(255, 0, 0)")) {
			assertTrue(true);
		} else {
			assertTrue(false, result);
		}

	}

	/**
	 * To check mandatory alert for firstname
	 */
	@Test(priority = 6, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_005() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtFirstName",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(false, result);
		}
	}

	/**
	 * To check mandatory alert for lastname
	 */
	@Test(priority = 7, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_006() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtLastName", "border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(false, result);
		}
	}

	/**
	 * To check mandatory alert for fatherfname
	 */
	@Test(priority = 8, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_007() {
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtFatherFirstName",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(false, result);
		}
	}

	/**
	 * To check mandatory alert for fatherlname
	 */
	@Test(priority = 9, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_008() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtFatherLastName",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(false, result);
		}
	}

	/**
	 * To check mandatory alert for candidate ID
	 */
	@Test(priority = 10, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_009() {
		// pages.CaseRegistration().addEditComponent();
		String result = pages.CaseRegistration().getalertcolor("ctl00_ContentPlaceHolder1_txtClientCandidateID",
				"border-color");
		if (result.equals("rgb(213, 25, 35)")) {
			assertTrue(true);
		} else {
			assertTrue(false, result);
		}
	}

	/**
	 * To check save and submit function all checks(fresher)
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 11, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_010() throws Exception {
		// pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		for (int i = 0; i < contract.size(); i++) {
			pages.CaseRegistration().selectcheck(contract.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * To check save with all checks(Experienced)
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 12, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_011() throws Exception {
		refno = null;
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, true);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
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
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		System.out.println("case refference no is : " + refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only address checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 13, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_012() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		List<String> address = pages.Utill().getAddressChecks(contract);
		for (int i = 0; i < address.size(); i++) {
			pages.CaseRegistration().selectcheck(address.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only education checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 14, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_013() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		List<String> education = pages.Utill().getEducationChecks(contract);
		for (int i = 0; i < education.size(); i++) {
			pages.CaseRegistration().selectcheck(education.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only employement checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 15, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_014() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		List<String> emp = pages.Utill().getEducationChecks(contract);
		for (int i = 0; i < emp.size(); i++) {
			pages.CaseRegistration().selectcheck(emp.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only reference checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 16, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_015() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		List<String> ref = pages.Utill().getReferenceChecks(contract);
		for (int i = 0; i < ref.size(); i++) {
			pages.CaseRegistration().selectcheck(ref.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only id checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 17, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_016() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		List<String> id = pages.Utill().getIdChecks(contract);
		for (int i = 0; i < id.size(); i++) {
			pages.CaseRegistration().selectcheck(id.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only criminal checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 18, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_017() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		List<String> criminal = pages.Utill().getCriminalChecks(contract);
		for (int i = 0; i < criminal.size(); i++) {
			pages.CaseRegistration().selectcheck(criminal.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only court checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 19, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_018() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		List<String> court = pages.Utill().getCourtChecks(contract);
		for (int i = 0; i < court.size(); i++) {
			pages.CaseRegistration().selectcheck(court.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only drug checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 20, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_019() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		List<String> drug = pages.Utill().getDrugChecks(contract);
		for (int i = 0; i < drug.size(); i++) {
			pages.CaseRegistration().selectcheck(drug.get(i).toString());
		}
		pages.CaseRegistration().save();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only db checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 21, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_020() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
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
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * save and submit case with only credit checks
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 22, enabled = true, groups = { "spcase registration" })
	public void TC_SPCR_021() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
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
		refno = pages.DcaseRegistration().getrefno(candidateName, lastName);
		logger.log(Status.INFO, refno);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
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

	/**
	 * To check cancel with updating all data
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 23, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_022() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		for (int i = 0; i < 4; i++) {
			pages.CaseRegistration().selectcheck(contract.get(i).toString());
		}
		String before = pages.DbConnection().getLastrefno(projectName);
		pages.CaseRegistration().cancel();
		String after = pages.DbConnection().getLastrefno(projectName);
		if (before.equals(after)) {
			assertTrue(true);
		} else {
			assertTrue(false, after);
			
		}

	}

	/**
	 * To check component count
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 24, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_023() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		int count = pages.CaseRegistration().getCheckCount();
		if (contract.size() == count) {
			assertTrue(true);
		} else {
			assertTrue(false, Integer.toString(count));
		}

	}

	/**
	 * To check compenent displayed in registration screen
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 25, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_024() throws Exception {
		List<String> components = pages.CaseRegistration().getDisplayedComponents();
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
		pages.Home().homepage();
		if (contract.containsAll(components)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	/**
	 * To check component list when fresher is Yes
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 26, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_025() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, true);
		List<String> contract = pages.DbConnection().getcontractdetails(contractName);
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

	/**
	 * To check alert message for employment check when frehser is yes
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 27, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_026() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
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

	/**
	 * To check alert for already used casename
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 28, enabled = true, groups = { "smoketest", "spcase registration" })
	public void TC_SPCR_027() throws Exception {
		HashMap<String, String> data = pages.DbConnection().getLastCase(projectName);
		pages.Home().clickRegister();
		candidateName = data.get("firstname");
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = data.get("lastname");
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		datas.put("DateofBirth", pages.Utill().formatedob(data.get("DateofBirth")));
		pages.CaseRegistration().registercase(datas);
		String msg = pages.Utill().confirmAlert();
		pages.Home().homepage();
		if (msg.equals("Case Already Exist!")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}

	}

	/**
	 * To check insuff raised case showing in clear queue
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 29, enabled = true, groups = { "insuff" })
	public void TC_SPINF_001() throws Exception {
		CaseOwnerInsuffClear cs = pages.CaseOwnerInsuffClear();
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		pages.CaseRegistration().selectcheck("Permanent");
		pages.CaseRegistration().selectcheck("Current Address");
		pages.CaseRegistration().raiseInsuff("Permanent", "need address proof");
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		pages.Home().Actions();
		cs.caseOwner();
		refno = pages.DbConnection().getLastrefno(projectName);
		cs.search(refno, "sp");
		String no = cs.getrefNo();
		if (refno.equals(no)) {
			assertTrue(true);
		} else {
			assertTrue(false, refno + " not found");
		}
	}

	/**
	 * To check insuff cleared components moved to case registration
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 30, enabled = true, dependsOnMethods = "TC_SPINF_001", groups = { "insuff" })
	public void TC_SPINF_002() throws Exception {
		CaseOwnerInsuffClear cs = pages.CaseOwnerInsuffClear();
		cs.openCase();
		cs.insuffClear("Permanent", "clear comments");
		pages.Utill().confirmAlert();
		pages.Home().workStage();
		pages.DataEntrySupervision().datanentrysupervision();
//		pages.DataEntrySupervision().assign(refno, uname);
		pages.DataEntrySupervision().assigngetnext(refno);
		pages.DcaseRegistration().caseRegistration();
		Thread.sleep(1500);
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
	}

	/**
	 * To check already moved components are whether editable or not in case
	 * registration
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 31, enabled = true, dependsOnMethods = "TC_SPINF_001", groups = { "insuff" })
	public void TC_SPINF_003() throws Exception {
		assertTrue(pages.CaseRegistration().isSelected("Current Address"));
		assertTrue(!pages.CaseRegistration().isEnabled("Current Address"));
	}

	/**
	 * To check is able to add additional component in case registration
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 32, enabled = true, dependsOnMethods = "TC_SPINF_001", groups = { "insuff" })
	public void TC_SPINF_004() throws Exception {
		pages.CaseRegistration().selectcheck("Current/Latest Employment");
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		assertTrue(true);
	}

	/**
	 * To check newly added check moved to team member queue
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 33, dependsOnMethods = "TC_SPINF_001", groups = { "insuff" })
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

	/**
	 * To check case tracker after insuff raise
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 34, enabled = true, dependsOnMethods = "TC_SPINF_005", alwaysRun = true, groups = { "insuff" })
	public void TC_SPINF_006() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		pages.CaseRegistration().selectcheck("Permanent");
		pages.CaseRegistration().selectcheck("Current Address");
		pages.CaseRegistration().raiseInsuff("Permanent", "need address proof");
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DbConnection().getLastrefno(projectName);
		pages.Home().CaseTracker();
		assertEquals(pages.CaseTracker().getCurrentStage(refno, "Permanent"),
				"Insuff Raised - Case Registration Pending");
		pages.CaseTracker().cancel();

	}

	/**
	 * To check case tracker after insuff raise
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 35, enabled = true, dependsOnMethods = "TC_SPINF_006", groups = { "insuff" })
	public void TC_SPINF_007() throws Exception {
		CaseOwnerInsuffClear cs = pages.CaseOwnerInsuffClear();
		pages.Home().Actions();
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

	/**
	 * To check already moved components are whether editable or not in case
	 * registration
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 36, enabled = true, dependsOnMethods = "TC_SPINF_006", groups = { "insuff" })
	public void TC_SPINF_008() throws Exception {

		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
		assertTrue(pages.CaseRegistration().isSelected("Current Address"));
		assertTrue(!pages.CaseRegistration().isEnabled("Current Address"));
	}

	/**
	 * To check is able to add additional component in case registration
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 37, enabled = true, dependsOnMethods = "TC_SPINF_006", groups = { "insuff" })
	public void TC_SPINF_009() throws Exception {

		pages.CaseRegistration().selectcheck("Current/Latest Employment");
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		assertTrue(true);
	}

	/**
	 * To check newly added check moved to team leader queue
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 38, enabled = true, dependsOnMethods = "TC_SPINF_006", groups = { "insuff" })
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

	/**
	 * To check insuff raised comments showing in insuff clear page for address
	 * check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 39, enabled = true, dependsOnMethods = "TC_SPINF_010", alwaysRun = true, groups = { "insuff" })
	public void TC_SPINF_011() throws Exception {
		String[] checks = { "Current Address", "UG1", "Current/Latest Employment", "Reference 1", "Aadhaar Card",
				"Current Address Criminal Check", "Current Address Court Check", "Credit Check 1", "Panel1",
				"Database" };
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		for (int i = 0; i < checks.length; i++) {
			pages.CaseRegistration().selectcheck(checks[i].toString());
			pages.CaseRegistration().raiseInsuff(checks[i], "insuff " + checks[i]);
		}
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DbConnection().getLastrefno(projectName);
//			refno="HDFC000308";
		pages.Home().Actions();
		pages.CaseOwnerInsuffClear().search(refno, "sp");
		pages.CaseOwnerInsuffClear().openCase();
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Current Address"), "insuff Current Address");

	}

	/**
	 * To check insuff raised comments showing in insuff clear page for education
	 * check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 40, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_014() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("UG1"), "insuff UG1");
	}

	/**
	 * To check insuff raised comments showing in insuff clear page for employment
	 * check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 41, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_017() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Current/Latest Employment"),
				"insuff Current/Latest Employment");
	}

	/**
	 * To check insuff raised comments showing in insuff clear page for reference
	 * check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 42, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_020() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Reference 1"), "insuff Reference 1");
	}

	/**
	 * To check insuff raised comments showing in insuff clear page for id check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 43, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_023() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Aadhaar Card"), "insuff Aadhaar Card");
	}

	/**
	 * To check insuff raised comments showing in insuff clear page for criminal
	 * check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 44, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_026() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Current Address Criminal Check"),
				"insuff Current Address Criminal Check");
	}

	/**
	 * To check insuff raised comments showing in insuff clear page for court check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 45, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_029() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Current Address Court Check"),
				"insuff Current Address Court Check");
	}

	/**
	 * To check insuff raised comments showing in insuff clear page for credit check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 46, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_032() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Credit Check 1"), "insuff Credit Check 1");
	}

	/**
	 * To check insuff raised comments showing in insuff clear page for drug check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 47, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_035() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Panel1"), "insuff Panel1");
	}

	/**
	 * To check insuff raised comments showing in insuff clear page for db check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 48, enabled = true, dependsOnMethods = "TC_SPINF_011", groups = { "insuff" })
	public void TC_SPINF_038() throws Exception {
		assertEquals(pages.CaseOwnerInsuffClear().getComments("Database"), "insuff Database");
		pages.CaseOwnerInsuffClear().cancel();
	}

	/**
	 * To check insuff cleared comments showing in history for address check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 49, enabled = true, dependsOnMethods = "TC_SPINF_038", alwaysRun = true, groups = { "insuff" })
	public void TC_SPINF_012() throws Exception {
		pages.Home().workStage();
		String[] checks = { "Current Address", "UG1", "Current/Latest Employment", "Reference 1", "Aadhaar Card",
				"Current Address Criminal Check", "Current Address Court Check", "Credit Check 1", "Panel1",
				"Database" };
//		refno = "HDFC000308";
		pages.Home().Actions();
		pages.CaseOwnerInsuffClear().search(refno, "sp");
		pages.CaseOwnerInsuffClear().openCase();
		pages.CaseOwnerInsuffClear().uploadMultiplecom(checks);

		pages.Home().workStage();
		pages.Utill().click("//span[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(5);
		pages.CaseRegistration().addEditComponent();
		pages.Utill().waitUntilLoaderisInvisible(10);
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		// assing
		pages.DataEntrySupervision().datanentrysupervision();
//		pages.DataEntrySupervision().assign(refno, "demoempl");
		pages.DataEntrySupervision().assigngetnext(refno);
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
		String doc = pages.DeAddress().historyDocument();
//		sf.assertTrue((doc.equals("address.pdf")), doc);
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("addressinsuffdoc"));
		sf.assertEquals(doc, expected);
		pages.DeAddress().close();
		sf.assertAll();
	}

	/**
	 * To check insuff cleared comments showing in history for education check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 50, enabled = true, dependsOnMethods = "TC_SPINF_012", groups = { "insuff" })
	public void TC_SPINF_015() throws Exception {

		pages.DeEducation().educationcheck();
		pages.DeEducation().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeEducation().getraisedBy(), uname);
		sf.assertEquals(pages.DeEducation().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeEducation().getraisedComments(), "insuff UG1");
		sf.assertEquals(pages.DeEducation().getclearedBy(), uname);
		sf.assertEquals(pages.DeEducation().getclearedComments(), "UG1 clear");
		String doc = pages.DeEducation().historyDocument();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("eduinsuffdoc"));
//		sf.assertTrue((doc.equals("education.pdf")), doc);
		sf.assertEquals(doc, expected);
		pages.DeEducation().close();
		sf.assertAll();
	}

	/**
	 * To check insuff cleared comments showing in history for employment check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 51, enabled = true, dependsOnMethods = "TC_SPINF_012", groups = { "insuff" })
	public void TC_SPINF_018() throws Exception {
		pages.DeEmployment().employementcheck();
		pages.DeEmployment().history();
		SoftAssert sf = new SoftAssert();
		try {
			sf.assertEquals(pages.DeEmployment().getraisedBy(), uname);
			sf.assertEquals(pages.DeEmployment().getraisedStage(), "Case Registration Pending");
			sf.assertEquals(pages.DeEmployment().getraisedComments(), "insuff Current/Latest Employment");
			sf.assertEquals(pages.DeEmployment().getclearedBy(), uname);
			sf.assertEquals(pages.DeEmployment().getclearedComments(), "Current/Latest Employment clear");
			String doc = pages.DeEmployment().historyDocument();
			String expected = this.FilterFileName(BaseClass.getlocator().getProperty("empinsuffdoc"));
			sf.assertEquals(doc, expected);
//			sf.assertTrue((doc.equals("employment.pdf")), doc);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			pages.DeEmployment().close();
			sf.assertAll();
		}

	}

	/**
	 * To check insuff cleared comments showing in history for reference check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 52, enabled = true, dependsOnMethods = "TC_SPINF_012", groups = { "insuff" })
	public void TC_SPINF_021() throws Exception {
		pages.DeReference().referencecheck();
		pages.DeReference().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeReference().getraisedBy(), uname);
		sf.assertEquals(pages.DeReference().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeReference().getraisedComments(), "insuff Reference 1");
		sf.assertEquals(pages.DeReference().getclearedBy(), uname);
		sf.assertEquals(pages.DeReference().getclearedComments(), "Reference 1 clear");
		String doc = pages.DeReference().historyDocument();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("refinsuffdoc"));
//		sf.assertTrue((doc.equals("reference.pdf")), doc);
		sf.assertEquals(doc, expected);
		pages.DeReference().close();
		sf.assertAll();
	}

	/**
	 * To check insuff cleared comments showing in history for db check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 53, enabled = true, dependsOnMethods = "TC_SPINF_012", groups = { "insuff" })
	public void TC_SPINF_039() throws Exception {
		pages.DeDatabase().databasecheck();
		pages.DeDatabase().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeDatabase().getraisedBy(), uname);
		sf.assertEquals(pages.DeDatabase().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeDatabase().getraisedComments(), "insuff Database");
		sf.assertEquals(pages.DeDatabase().getclearedBy(), uname);
		sf.assertEquals(pages.DeDatabase().getclearedComments(), "Database clear");
		String doc = pages.DeDatabase().historyDocument();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("dbinsuffdoc"));
		sf.assertEquals(doc, expected);
//		sf.assertTrue((doc.equals("database.pdf")), doc);
		pages.DeDatabase().close();
		sf.assertAll();
	}

	/**
	 * To check insuff cleared comments showing in history for criminal check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 54, enabled = true, dependsOnMethods = "TC_SPINF_012", groups = { "insuff" })
	public void TC_SPINF_027() throws Exception {
		pages.DeCriminal().criminalcheck();
		pages.DeCriminal().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeCriminal().getraisedBy(), uname);
		sf.assertEquals(pages.DeCriminal().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeCriminal().getraisedComments(), "insuff Current Address Criminal Check");
		sf.assertEquals(pages.DeCriminal().getclearedBy(), uname);
		sf.assertEquals(pages.DeCriminal().getclearedComments(), "Current Address Criminal Check clear");
		String doc = pages.DeCriminal().historyDocument();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("criminalinsuffdoc"));
		sf.assertEquals(doc, expected);
//		sf.assertTrue((doc.equals("criminal.pdf")), doc);
		pages.DeCriminal().close();
		sf.assertAll();
	}

	/**
	 * To check insuff cleared comments showing in history for credit check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 55, enabled = true, dependsOnMethods = "TC_SPINF_012", groups = { "insuff" })
	public void TC_SPINF_033() throws Exception {
		pages.DeCredit().creditcheck();
		pages.DeCredit().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeCredit().getraisedBy(), uname);
		sf.assertEquals(pages.DeCredit().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeCredit().getraisedComments(), "insuff Credit Check 1");
		sf.assertEquals(pages.DeCredit().getclearedBy(), uname);
		sf.assertEquals(pages.DeCredit().getclearedComments(), "Credit Check 1 clear");
		String doc = pages.DeCredit().historyDocument();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("creditinsuffdoc"));
		sf.assertEquals(doc, expected);
//		sf.assertTrue((doc.equals("credit.pdf")), doc);
		pages.DeCredit().close();
		sf.assertAll();
	}

	/**
	 * To check insuff cleared comments showing in history for court check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 56, enabled = true, dependsOnMethods = "TC_SPINF_012", groups = { "insuff" })
	public void TC_SPINF_030() throws Exception {
		pages.DeCourt().courtcheck();
		pages.DeCourt().history();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(pages.DeCourt().getraisedBy(), uname);
		sf.assertEquals(pages.DeCourt().getraisedStage(), "Case Registration Pending");
		sf.assertEquals(pages.DeCourt().getraisedComments(), "insuff Current Address Court Check");
		sf.assertEquals(pages.DeCourt().getclearedBy(), uname);
		sf.assertEquals(pages.DeCourt().getclearedComments(), "Current Address Court Check clear");
		String doc = pages.DeCourt().historyDocument();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("courtinsuffdoc"));
		sf.assertEquals(doc, expected);
//		sf.assertTrue((doc.equals("court.pdf")), doc);
		pages.DeCourt().close();
		sf.assertAll();
	}

	/**
	 * To check insuff cleared comments showing in history for id check
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 57, enabled = true, dependsOnMethods = "TC_SPINF_012", groups = { "insuff" })
	public void TC_SPINF_024() throws Exception {
		pages.DeId().idcheck();
		pages.DeId().history();
		SoftAssert sf = new SoftAssert();
		try {
			sf.assertEquals(pages.DeId().getraisedBy(), uname);
			sf.assertEquals(pages.DeId().getraisedStage(), "Case Registration Pending");
			sf.assertEquals(pages.DeId().getraisedComments(), "insuff Aadhaar Card");
			sf.assertEquals(pages.DeId().getclearedBy(), uname);
			sf.assertEquals(pages.DeId().getclearedComments(), "Aadhaar Card clear");
			String doc = pages.DeId().historyDocument();
//			sf.assertTrue((doc.equals("id.pdf")), doc);
			String expected = this.FilterFileName(BaseClass.getlocator().getProperty("idinsuffdoc"));
			sf.assertEquals(doc, expected);
			pages.DeId().close();
		} catch (Exception e) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {
			pages.Utill().SwitchDefault();
			pages.Utill().click("imgHome");
			pages.Utill().waitUntilLoaderisInvisible(100);
			sf.assertAll();
		}

	}

	/**
	 * Check if not applicable cases showing in case details
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 58, enabled = true, alwaysRun = true, dependsOnMethods = "TC_SPINF_024", groups = {
			"not applicable" })
	public void TC_SPNAPP_001() throws Exception {
		String[] checks = { "Current Address", "UG1", "Current/Latest Employment", "Reference 1", "Aadhaar Card",
				"Current Address Criminal Check", "Current Address Court Check", "Credit Check 1", "Database" };
		String[] notapplicable = { "Permanent", "12th", "Previous Employment", "Reference 2", "PAN Card",
				"Permanent Criminal Check", "Permanent Court Check" };
		pages.CaseRegistration().casereg();
		pages.Utill().waitUntilLoaderisInvisible(100);
//		Thread.sleep(3000);
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		for (int i = 0; i < checks.length; i++) {
			pages.CaseRegistration().selectcheck(checks[i].toString());
		}
		for (int i = 0; i < notapplicable.length; i++) {
			pages.CaseRegistration().selectcheck(notapplicable[i].toString());
			pages.CaseRegistration().notApplicable(notapplicable[i].toString(), "not applicable");
			// not applicable
		}
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DbConnection().getLastrefno(projectName);
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (Arrays.asList(checks).contains(name)) {
				if (data.get(i).get("Status").equals("WIP")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("Status"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("CurrentStage"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("PersonResponsible").equals("Team Leader")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("PersonResponsible"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else if (Arrays.asList(notapplicable).contains(name)) {
				if (data.get(i).get("Status").equals("Not Required")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("Status"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("CurrentStage").equals("Not Required")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("CurrentStage"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("PersonResponsible").equals("-")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("PersonResponsible"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	/**
	 * Check status and current stage of cep raised case in case tracker
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 59, enabled = true, alwaysRun = true, dependsOnMethods = "TC_SPNAPP_001", groups = {
			"not applicable" })
	public void TC_SPCEP_001() throws Exception {
		String[] checks = { "Current Address", "UG1", "Previous Employment", "Reference 3" };
		String[] cep = { "Current/Latest Employment", "Previous Employment 2", "Reference 1", "Reference 2" };
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		for (int i = 0; i < checks.length; i++) {
			pages.CaseRegistration().selectcheck(checks[i].toString());
		}
		for (int i = 0; i < cep.length; i++) {
			pages.CaseRegistration().selectcheck(cep[i].toString());
			pages.CaseRegistration().cep(cep[i].toString(), "cep raised", "10/11/2018");
		}
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DbConnection().getLastrefno(projectName);
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if (Arrays.asList(checks).contains(name)) {
				if (data.get(i).get("Status").equals("WIP")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("Status"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("CurrentStage"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("PersonResponsible").equals("Team Leader")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("PersonResponsible"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			} else if (Arrays.asList(cep).contains(name)) {
				if (data.get(i).get("Status").equals("CEP Onhold")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("Status"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("CurrentStage").equals("CEP Raised - Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("CurrentStage"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("PersonResponsible").equals("Team Leader")) {
					sf.assertTrue(true, "success");
					// System.out.println(name+" : "+data.get(i).get("PersonResponsible"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}

		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}

	/**
	 * Check case showing in cep clear page
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 60, enabled = true, dependsOnMethods = "TC_SPCEP_001")
	public void TC_SPCEP_002() throws Exception {
		pages.Home().Actions();
		pages.CEP().CEPClear();
		pages.CEP().search(refno, "SP");
		String no = pages.CEP().getrefNo();
		assertEquals(no, refno);
	}

	/**
	 * Check CEP clear comments in data entry
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 61, enabled = true, dependsOnMethods = "TC_SPCEP_002")
	public void TC_SPCEP_003() throws Exception {
		Properties loc = BaseClass.getlocator();
		pages.CEP().upload(refno, "comments cep", "Relieving Letter", loc.getProperty("addressinsuffdoc"));
		pages.Home().workStage();

		pages.DataEntrySupervision().datanentrysupervision();
		pages.DataEntrySupervision().assigngetnext(refno);
		pages.DataEntry().datanentry();
		pages.DataEntry().search(refno);
		pages.DataEntry().selectcase(refno);
		pages.DeEmployment().employementcheck();
		pages.DeEmployment().history();
		pages.DeEmployment().Cep();
		String actual = pages.DeEmployment().CepgetclearedComments();
		assertEquals(actual, "comments cep");
	}

	/**
	 * check clear cep with document upload
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 62, enabled = true, dependsOnMethods = "TC_SPCEP_003")
	public void TC_SPCEP_004() throws Exception {
		String actual = pages.DeEmployment().CephistoryDocument();
		pages.DeEmployment().close();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("addressinsuffdoc"));
		assertEquals(actual, expected);
		pages.Utill().SwitchDefault();
		pages.Utill().click("imgHome");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * To check Upload case document
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 63, enabled = true, alwaysRun = true, dependsOnMethods = "TC_SPCEP_003")
	public void TC_SPDOC_001() throws Exception {
		pages.CaseRegistration().casereg();
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().candidateid());
		lastName = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		pages.CaseRegistration().addEditComponent();
		pages.CaseRegistration().uploadcaseDoc("Authorization Letter",
				BaseClass.getlocator().getProperty("addressinsuffdoc"));
		Thread.sleep(1000);
		String actual = pages.CaseRegistration().getuploadcaseDoc("Authorization Letter");
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("addressinsuffdoc"));
		assertEquals(actual, expected);
	}

	/**
	 * To check Upload component document
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 64, enabled = true, dependsOnMethods = "TC_SPDOC_001")
	public void TC_SPDOC_002() throws Exception {
		pages.CaseRegistration().selectcheck("Permanent");
		pages.CaseRegistration().documentupload("Permanent", BaseClass.getlocator().getProperty("addressinsuffdoc"),
				"Address Proof");
		String actual = pages.CaseRegistration().getDocumentName("Permanent", "Address Proof");
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("addressinsuffdoc"));
		assertEquals(actual, expected);
	}

	/**
	 * To check delete document in case documents
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 65, enabled = true, dependsOnMethods = "TC_SPDOC_002")
	public void TC_SPDOC_003() throws Exception {
		pages.CaseRegistration().uploadcaseDoc();
		pages.Utill().waitUntilLoaderisInvisible(100);
		if (pages.CaseRegistration().isDoctypeValid("Credit Form", 1)) {
			pages.Utill().sendKeys(
					"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='Credit Form']/../td[5]//div/ul/li/span/input[2]",
					BaseClass.getlocator().getProperty("creditinsuffdoc"));
		} else
			throw new NotFoundException("Credit Form");
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnAddCaseDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(50);
		pages.Utill().click(
				"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='Credit Form']/../td[6]//td[2]/input");
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(50);
		int count = driver.findElements(By.xpath(
				"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='Credit Form']/../td[5]//span"))
				.size();
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnCaseDocumentCancel");
		assertTrue(count > 1);
	}

	/**
	 * To check delete document in components documents
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 66, enabled = true, dependsOnMethods = "TC_SPDOC_003")
	public void TC_SPDOC_004() throws Exception {
		pages.Utill().click("//td[text()='Permanent']/../td[10]//input[1]");
		pages.Utill().waitUntilLoaderisInvisible(10);
		if (pages.CaseRegistration().isDoctypeValid("Others", 0)) {
			pages.Utill().sendKeys(
					"//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='Others']/../td[6]//input[2]",
					BaseClass.getlocator().getProperty("creditinsuffdoc"));
		} else
			throw new NotFoundException("Others");
		pages.CaseRegistration().addDocument();
		pages.CaseRegistration().deleteComponentdoc("Others");
		pages.CaseRegistration().docupClose();
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
	}

	/**
	 * To check case document showing in data entry
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 67, enabled = true, dependsOnMethods = "TC_SPDOC_004")
	public void TC_SPDOC_005() throws Exception {
		pages.DataEntrySupervision().datanentrysupervision();
		refno = pages.DbConnection().getLastrefno(projectName);
		pages.DataEntrySupervision().assigngetnext(refno);
		pages.DataEntry().datanentry();
		pages.DataEntry().search(refno);
		
		pages.DataEntry().selectcase(refno);
		pages.CaseInformation().edit();
		pages.CaseInformation().CaseDocument();
		String actual = pages.CaseInformation().getDocumentName("Authorization Letter");
		pages.CaseInformation().cancel();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("addressinsuffdoc"));
		assertEquals(actual, expected);
	}

	/**
	 * To check component document showing in data entry
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 68, enabled = true, dependsOnMethods = "TC_SPDOC_005")
	public void TC_SPDOC_006() throws Exception {
		pages.DeAddress().addresscheck();
		pages.DeAddress().document();
		String actual = pages.DeAddress().getDocumentName("Address Proof");
		pages.DeAddress().docclose();
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("addressinsuffdoc"));
		assertEquals(actual, expected);
	}

	/**
	 * To check document to be downloaded in case document
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 69, enabled = true, dependsOnMethods = "TC_SPDOC_006")
	public void TC_SPDOC_007() throws Exception {
		File file = new File(BaseClass.getlocator().getProperty("downloadFilepath"));
		pages.Utill().deleteFiles(file);
		pages.Utill().SwitchDefault();
		pages.CaseInformation().CaseDocument();
		pages.CaseInformation().documentDownload("Authorization Letter");
		pages.CaseInformation().cancel();
		String actual = pages.Utill().isFileExist(file);
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("addressinsuffdoc"));
		assertEquals(actual, expected);
	}

	/**
	 * To check document to be downloaded in component document
	 * 
	 * @throws Exception WebDriver Exception
	 */
	@Test(priority = 70, enabled = true, dependsOnMethods = "TC_SPDOC_007")
	public void TC_SPDOC_008() throws Exception {
		File file = new File(BaseClass.getlocator().getProperty("downloadFilepath"));
		pages.Utill().deleteFiles(file);
		pages.Utill().SwitchDefault();
		pages.DeAddress().addresscheck();
		pages.DeAddress().document();
		pages.DeAddress().downloaddoc("Address Proof");
		pages.DeAddress().docclose();
		String actual = pages.Utill().isFileExist(file);
		pages.Utill().SwitchDefault();
		pages.Utill().click("imgHome");
		String expected = this.FilterFileName(BaseClass.getlocator().getProperty("addressinsuffdoc"));
		assertEquals(actual, expected);
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
			String pagesource = driver.getPageSource();
			if (pagesource.contains("Images/message.png")) {
				logger.log(Status.WARNING, "Your last session was terminated");
				pages.Utill().click("ctl00_ContentPlaceHolder1_urls");
				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			} else if (pagesource.contains("ctl00_ContentPlaceHolder1_txtUserName")) {
				logger.log(Status.WARNING, "Your last session was closed by user");
				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			} else {
				logger.log(Status.WARNING, method.getName() + " navigating to home page due to error");
				driver.get(config.getProperty("url") + "/Web/dashboard.aspx");
				pages.Utill().waitUntilLoaderisInvisible(80);
				List<String> windows = new ArrayList<String>(driver.getWindowHandles());
				if (windows.size() > 1) {
					for (int i = 1; i < windows.size(); i++) {
						driver.switchTo().window(windows.get(i).toString());
						driver.close();
					}
					driver.switchTo().window(windows.get(0).toString());
				}
			}
			
		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	/**
	 * close the drivers
	 */
	@AfterTest(alwaysRun = true)
	public void teardown() throws Exception {
		if (driver != null)
			pages.Utill().closeAllBrowsers();
	}

	/**
	 * Generates the report
	 */
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		extent.flush();
	}

	/**
	 * Takes file path as input and return the file name in that path
	 * 
	 * @param FilePath directory
	 * @return filename name filtered from file path
	 */
	private String FilterFileName(String FilePath) {
		return FilePath.substring(FilePath.lastIndexOf("\\")).replace("\\", "");
	}
}
