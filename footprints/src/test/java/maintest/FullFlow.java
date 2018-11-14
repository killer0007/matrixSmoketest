package maintest;

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
import java.util.StringTokenizer;
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
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import dashboard.DataEntrySupervision;
import dashboard.ReportGeneration;
import dashboard.ReportValidation;
import environment.*;
import verification.*;
@Listeners(environment.Listener.class)
public class FullFlow implements Design {

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
	private String pdfName=null;

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
	@Test(priority = 1, enabled = true)
	public void login() throws Exception {
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
	}

	/**
	 * case registration
	 * 
	 * @throws Exception WebDriverException
	 */
	@Test(priority = 2, enabled = true, dependsOnMethods = "login")
	public void caseregistration() throws Exception {
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().getcandidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
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
		refno = pages.DbConnection().getLastrefno(projectName);
		DataEntrySupervision des = pages.DataEntrySupervision();
		des.datanentrysupervision();
		//des.assigngetnext(refno);
		des.assign(refno, "demoempl");
	}

	@Test(priority = 4, enabled = true, dependsOnMethods = "dataEntryAssign")
	public void dataEntry() throws Exception {
		pages.DataEntry().datanentry();
		pages.Utill().click("//*[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		HashMap<String, String> casedetails = pages.DbConnection().getLastCase(projectName);
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
		pages.Utill().waitUntilLoaderisInvisible(100);

	}

	@Test(priority = 5, enabled = true, dependsOnMethods = "dataEntry")
	public void dataEntryQCAssign() throws Exception {
		pages.DataEntryQCSupervision().datanentryqcsupervision();
//		pages.DataEntryQCSupervision().assigngetnext(refno);
		pages.DataEntryQCSupervision().assign(refno, "demoempl");
		pages.DataEntryQC().datanentryqc();
		pages.DataEntryQC().selectcase(refno);

	}

	@Test(priority = 6, enabled = true, dependsOnMethods = "dataEntryQCAssign")
	public void AddressDEQC() throws Exception {
		dataEntryQC.Address add = new dataEntryQC.Address(driver,logger);
		add.addresscheck();
		Map<String, String> actual = add.getCurrentAddress();
		Map<String, String> expected = add.filedata("Current Address");
		add.addresscheck();
		Map<String, String> Peractual = add.PermanentAdress();
		Map<String, String> Perexpected = add.filedata("Permanent");
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
		dataEntryQC.Education edu = new dataEntryQC.Education(driver,logger);
		edu.educationcheck();
		Map<String, String> actual = edu.gettwelveth();
		Map<String, String> expected = edu.filedata("12th");
		edu.educationcheck();
		Map<String, String> Peractual = edu.getugone();
		Map<String, String> Perexpected = edu.filedata("UG1");
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
		dataEntryQC.Employment emp = new dataEntryQC.Employment(driver,logger);
		emp.employmentcheck();
		Map<String, String> actual = emp.CurrentEmp();
		Map<String, String> expected = emp.filedata("Current/Latest Employment");
		emp.employmentcheck();
		Map<String, String> Peractual = emp.PerviousEmp();
		Map<String, String> Perexpected = emp.filedata("Previous Employment");
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
		dataEntryQC.Reference ref = new dataEntryQC.Reference(driver,logger);
		ref.referencecheck();
		Map<String, String> actual = ref.Referenceone();
		Map<String, String> expected = ref.filedata();
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
		dataEntryQC.Database db = new dataEntryQC.Database(driver,logger);
		db.databasecheck();
		Map<String, String> actual = db.getdatabase();
		Map<String, String> expected = db.filedata();
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
		dataEntryQC.Criminal criminal = new dataEntryQC.Criminal(driver,logger);
		criminal.criminalcheck();
		Map<String, String> actual = criminal.CurrentAddress();
		Map<String, String> Perexpected = criminal.filedata("Permanent Criminal Check");
		criminal.criminalcheck();
		Map<String, String> Peractual = criminal.PermanentAdress();	
		Map<String, String> expected = criminal.filedata("Current Address Criminal Check");
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
		dataEntryQC.Credit credit = new dataEntryQC.Credit(driver,logger);
		credit.creditcheck();
		Map<String, String> actual = credit.credit();
		Map<String, String> expected = credit.filedata();
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
		dataEntryQC.Court court = new dataEntryQC.Court(driver,logger);
		court.courtcheck();
		Map<String, String> actual = court.CurrentAddress();
		Map<String, String> expected = court.filedata("Current Address Court Check");	
		court.courtcheck();
		Map<String, String> Peractual = court.PermanentAdress();
		Map<String, String> Perexpected = court.filedata("Permanent Court Check");
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
		dataEntryQC.Id id = new dataEntryQC.Id(driver,logger);
		id.idcheck();
		Map<String, String> actual = id.Aadharcard();
		Map<String, String> expected = id.filedata("Aadhaar Card");
		id.idcheck();
		Map<String, String> passactual = id.PassPort();
		Map<String, String> passexpected = id.filedata("Passport");
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
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.VerificationSupervisor().verificationsupervisor();
		
		pages.VerificationSupervisor().assign(refno, "demoempl");
		//pages.VerificationSupervisor().assigngetnext(refno, "ID", "Aadhaar Card");
	}
	@Test(priority = 16, enabled = true, dependsOnMethods = "VerificationSupervisor")
	public void VerificationIntiation() throws Exception {
		VerificationInitiate ver= new VerificationInitiate(driver,logger);
		Map<String, String> data=mode();
		pages.Verification().verification();
		pages.Home().Logout();
		pages.Login().userLogin("demov", "Paws@123");
		pages.Verification().verification();
		ver.Initiate(refno, "Current Address", data.get("Current Address"));
		ver.Initiate(refno, "Permanent", data.get("Permanent"));
		pages.Home().Logout();
		pages.Login().userLogin("demoempl", "Pass$$123");
		pages.Verification().verification();
		ver.Initiate(refno, "12th", data.get("12th"));
		ver.Initiate(refno, "UG1", data.get("UG1"));
		ver.Initiate(refno, "Current/Latest Employment", data.get("Current/Latest Employment"));
		ver.Initiate(refno, "Previous Employment", data.get("Previous Employment"));
		ver.Initiate(refno, "Reference 1", data.get("Reference 1"));
		ver.Initiate(refno, "Current Address Criminal Check", data.get("Current Address Criminal Check"));
		ver.Initiate(refno, "Permanent Criminal Check", data.get("Permanent Criminal Check"));
		ver.Initiate(refno, "Current Address Court Check", data.get("Current Address Court Check"));
		ver.Initiate(refno, "Permanent Court Check", data.get("Permanent Court Check"));
		ver.Initiate(refno, "Credit Check 1", data.get("Credit Check 1"));
		ver.Initiate(refno, "Passport", data.get("Passport"));
		ver.Initiate(refno, "Aadhaar Card", data.get("Aadhaar Card"));
		
	}
	@Test(priority = 17, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void AddressVerification() throws Exception {
		pages.Home().Logout();
		pages.Login().userLogin("demov", "Paws@123");
		pages.Verification().verification();
		pages.Verification().CurrentAddress(refno);	
		Address add = new Address(driver,logger);
		add.Verification();
		pages.Verification().Permanent(refno);	
		add.Verification();
		pages.Home().Logout();
		pages.Login().userLogin("demoempl", "Pass$$123");
		pages.Verification().verification();
		pages.Home().CaseTracker();
		String stage=pages.CaseTracker().getCurrentStage(refno, "Current Address");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}

	@Test(priority = 18, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void EducationVerification() throws Exception {
		Education edu = new Education(driver,logger);
		pages.Verification().verification();
		pages.Verification().twelveth(refno);
		edu.Verification();
		pages.Verification().UGone(refno);
		edu.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "12th");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}
	@Test(priority = 19, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void EmploymentVerification() throws Exception {
		Employment emp = new Employment(driver,logger);
		pages.Verification().verification();
		pages.Verification().CurrentEmployment(refno);
		emp.Verification();
		pages.Verification().PreviousEmployment(refno);
		emp.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Previous Employment");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}
	@Test(priority = 20, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void ReferenceVerification() throws Exception {
		Reference ref = new Reference(driver,logger);
		pages.Verification().verification();
		pages.Verification().Reference(refno);
		ref.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Reference 1");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}
	@Test(priority = 21, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void DatabaseVerification() throws Exception {
		Database db = new Database(driver,logger);
		pages.Verification().verification();
		pages.Verification().Database(refno);
		db.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Database");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}
	@Test(priority = 22, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void CriminalVerification() throws Exception {
		Criminal criminal = new Criminal(driver,logger);
		pages.Verification().verification();
		pages.Verification().CurrentAddressCriminalCheck(refno);
		criminal.Verification();
		pages.Verification().PermanentCriminalCheck(refno);
		criminal.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Current Address Criminal Check");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}
	@Test(priority = 23, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void CreditVerification() throws Exception {
		Credit cri = new Credit(driver,logger);
		pages.Verification().verification();
		pages.Verification().Credit(refno);
		cri.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Credit Check 1");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}
	@Test(priority = 24, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void CourtVerification() throws Exception {
		Court court = new Court(driver,logger);
		pages.Verification().verification();
		pages.Verification().CurrentAddressCourtCheck(refno);
		court.Verification();
		pages.Verification().PermanentCourtCheck(refno);
		court.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Current Address Court Check");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}
	@Test(priority = 25, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void IDVerification() throws Exception {
		Id id = new Id(driver,logger);
		pages.Verification().verification();
		pages.Verification().Passport(refno);
		id.Verification();
		pages.Verification().AadhaarCard(refno);
		id.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Passport");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");
	}

	@Test(priority = 26, enabled = true, dependsOnMethods = "VerificationIntiation")
	public void DrugVerification() throws Exception {
		Drug drug = new Drug(driver,logger);
		pages.Verification().verification();
		pages.Verification().Panel1(refno);
		drug.Verification();
		pages.Home().CaseTracker();
		String stage = pages.CaseTracker().getCurrentStage(refno, "Panel1");
		pages.CaseTracker().cancel();
		assertEquals(stage, "Report Generation Assignment Pending");

	}
	@Test(priority = 27, enabled = true, dependsOnMethods = "DrugVerification")
	public void ReportGenerationSupervision() throws Exception {
		pages.ReportGenerationSupervision().reportGenerationSupervision();
		//pages.ReportGenerationSupervision().assigngetnext(refno);
		pages.ReportGenerationSupervision().assign(refno, "demoempl");
		List<String> components= new ArrayList<String>(Arrays.asList(pages.CaseRegistration().getcomponents()));
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		SoftAssert sf = new SoftAssert();
		List<HashMap<String, String>> data =pages.CaseTracker().getcasedata();
		for (HashMap<String, String> d:data) {
			if(components.contains(d.get("ComponentName"))) {
				sf.assertEquals(d.get("CurrentStage"), "Report Generation Pending");
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}
	@Test(priority = 28, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void AddressReportGeneration() throws Exception {
		pages.ReportGeneration().reportGeneration();
		pages.ReportGeneration().Search(refno);
		pages.ReportGeneration().Select(refno);
		Address add = new Address(driver, logger);
		Map<String, String> actual=add.getCurrentAddress();
		add.ReportComments();
		add.UpdateReportComments();
		add.CloseReportComments();
		add.save();
		Map<String, String> expected=add.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=add.PermanentAdress();
		add.ReportComments();
		add.UpdateReportComments();
		add.CloseReportComments();
		add.save();
		Map<String, String> perexpected=add.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 29, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void EducationReportGeneration() throws Exception {
		Education edu = new Education(driver, logger);
		Map<String, String> actual=edu.gettwelveth();
		edu.ReportComments();
		edu.UpdateReportComments();
		edu.CloseReportComments();
		edu.save();
		Map<String, String> expected=edu.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=edu.ugone();
		edu.ReportComments();
		edu.UpdateReportComments();
		edu.CloseReportComments();
		edu.save();
		Map<String, String> perexpected=edu.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 30, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void EmploymentReportGeneration() throws Exception {
		Employment emp = new Employment(driver, logger);
		Map<String, String> actual=emp.CurrentEmp();
		emp.ReportComments();
		emp.UpdateReportComments();
		emp.CloseReportComments();
		emp.save();
		Map<String, String> expected=emp.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=emp.PerviousEmp();
		emp.ReportComments();
		emp.UpdateReportComments();
		emp.CloseReportComments();
		emp.save();
		Map<String, String> perexpected=emp.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 31, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void ReferenceReportGeneration() throws Exception {
		Reference ref = new Reference(driver, logger);
		Map<String, String> actual=ref.Referenceone();
		Map<String, String> expected=ref.filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 32, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void DatabaseReportGeneration() throws Exception {
		Database db = new Database(driver, logger);
		Map<String, String> actual=db.databasedata();
		Map<String, String> expected=db.filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 33, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void CriminalReportGeneration() throws Exception {
		Criminal criminal = new Criminal(driver, logger);
		Map<String, String> actual=criminal.CurrentAddress();
		criminal.ReportComments();
		criminal.UpdateReportComments();
		criminal.CloseReportComments();
		criminal.save();
		Map<String, String> expected=criminal.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=criminal.PermanentAdress();
		criminal.ReportComments();
		criminal.UpdateReportComments();
		criminal.CloseReportComments();
		criminal.save();
		Map<String, String> perexpected=criminal.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 34, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void CreditReportGeneration() throws Exception {
		Credit credit = new Credit(driver, logger);
		Map<String, String> actual=credit.credit();
		credit.ReportComments();
		credit.UpdateReportComments();
		credit.CloseReportComments();
		credit.save();
		Map<String, String> expected=credit.filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 35, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void CourtReportGeneration() throws Exception {
		Court court = new Court(driver, logger);
		Map<String, String> actual=court.CurrentAddress();
		court.ReportComments();
		court.UpdateReportComments();
		court.CloseReportComments();
		court.save();
		Map<String, String> expected=court.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=court.PermanentAdress();
		court.ReportComments();
		court.UpdateReportComments();
		court.CloseReportComments();
		court.save();
		Map<String, String> perexpected=court.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 36, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void DrugReportGeneration() throws Exception {
		Drug drug = new Drug(driver, logger);
		Map<String, String> actual=drug.drug();
		Map<String, String> expected=drug.filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 37, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void IdReportGeneration() throws Exception {
		Id id = new Id(driver, logger);
		Map<String, String> actual=id.PassPort();
		id.ReportComments();
		id.UpdateReportComments();
		id.CloseReportComments();
		id.save();
		Map<String, String> expected=id.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=id.Aadharcard();
		id.ReportComments();
		id.UpdateReportComments();
		id.CloseReportComments();
		id.save();
		Map<String, String> perexpected=id.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 38, enabled = true, dependsOnMethods = "IdReportGeneration")
	public void ReportGenerationSubmit() throws Exception {
		ReportGeneration reportgeneration=pages.ReportGeneration();
		pages.Utill().SwitchDefault();
		reportgeneration.GenerateReport();
		List<String> op=reportgeneration.getReportComponents();
		List<String> components = new ArrayList<>(Arrays.asList(pages.CaseRegistration().getcomponents()));
		Collections.sort(op);
		Collections.sort(components);
		assertEquals(op, components);
		reportgeneration.GenerateReportCheckbox();
		reportgeneration.ReportComments("completed");
		reportgeneration.ReportTemplate("New Standard Template");
		reportgeneration.CaseStatus("Clear");
//		reportgeneration.previewReport();
//		pages.Utill().switchWindow(1);
//		String source=driver.getPageSource();
//		boolean re=true;
//		if(!source.contains("report/ReportViewer.aspx")) {
//			logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(Utill.getScreenshot(driver)).build());
//			re=false;
//		}
//		pages.Utill().closeTab();
//		pages.Utill().switchWindow(0);
		reportgeneration.submit();
//		assertTrue(re);
	}
	
	@Test(priority = 38, enabled = true, dependsOnMethods = "ReportGenerationSubmit")
	public void ReportValidationSupervisor() throws Exception {
		pages.ReportValidationSupervision().reportValidationSupervision();
		pages.ReportValidationSupervision().assign(refno, "demoempl");
		List<String> components= new ArrayList<String>(Arrays.asList(pages.CaseRegistration().getcomponents()));
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		SoftAssert sf = new SoftAssert();
		List<HashMap<String, String>> data =pages.CaseTracker().getcasedata();
		for (HashMap<String, String> d:data) {
			if(components.contains(d.get("ComponentName"))) {
				sf.assertEquals(d.get("CurrentStage"), "Report Generation QC Pending");
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}
	@Test(priority = 40, enabled = true, dependsOnMethods = "ReportValidationSupervisor")
	public void ReportValidation() throws Exception {
		List<String> components= new ArrayList<String>(Arrays.asList(pages.CaseRegistration().getcomponents()));
		ReportValidation reportValidation=pages.ReportValidation();
		reportValidation.reportValidation();
		reportValidation.Search(refno);
		reportValidation.Select(refno);
		reportValidation.GenerateReport();
		reportValidation.PublishReport();
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		SoftAssert sf = new SoftAssert();
		List<HashMap<String, String>> data =pages.CaseTracker().getcasedata();
		for (HashMap<String, String> d:data) {
			if(components.contains(d.get("ComponentName"))) {
				sf.assertEquals(d.get("CurrentStage"), "Closed");
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}
	@Test(priority = 39, enabled = false, alwaysRun=true,dependsOnMethods = "ReportGenerationSubmit")
	public void readPdfIndex() throws Exception {
		if(pdfName!=null) {
			String path=config.getProperty("downloadFilepath")+"\\\\"+pdfName;
			PdfReader reader = new PdfReader(path);
			String text = PdfTextExtractor.getTextFromPage(reader, 3);
			System.out.println(text);
			reader.close();
			StringTokenizer token = new StringTokenizer(text,"\n");
			Map<String, String> out=pages.Utill().pageOne(token);
			System.out.println(out);
		}
		else
			assertEquals(null, "pdf file name");
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
			pages.Utill().GoTo("http://192.168.2.17:97/Web/dashboard.aspx");
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
			pages.Utill().closeAllBrowsers();
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
